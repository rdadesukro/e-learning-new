package com.example.e_learning_penjas;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.e_learning_penjas.model.BaseResponse;
import com.example.e_learning_penjas.model.ResponsModel;
import com.example.e_learning_penjas.model.model_profil.Response_profil;
import com.example.e_learning_penjas.model.model_profil.ResultItem_profil;
import com.example.e_learning_penjas.model.model_siswa.Response_siswa;
import com.example.e_learning_penjas.model.model_siswa.ResultItem_siswa;
import com.example.e_learning_penjas.server.ApiRequest;
import com.example.e_learning_penjas.server.Pelayanan_service;
import com.example.e_learning_penjas.server.Retroserver;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.mobsandgeeks.saripaar.Validator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class menu_profil extends AppCompatActivity {
    public final static String TAG_nis = "nis_ambil";
    public final static String TAG_STATUS = "status";
    public final static String TAG_GURU = "id_guru";
    public final static String TAG_NAMA = "nama";
    public final static String TAG_KELAS = "kelas";
    public static final String my_shared_preferences = "my_shared_preferences";
    SharedPreferences sharedpreferences;
    public static final String session_status = "session_status";
    private static final int PHOTO_REQUEST_CODE = 102;
    public final int SELECT_FILE = 1;
    Boolean session = false;
    public static final String GALLERY_DIRECTORY_NAME = "Hello Camera";
    public static final int MEDIA_TYPE_VIDEO = 2;

    // Image and Video file extensions
    public static final String IMAGE_EXTENSION = "jpg";
    public static final String VIDEO_EXTENSION = "mp4";
    String status,nis,id_guru,nama,kelas;

    private List<ResultItem_profil> data = new ArrayList<>();

    @BindView(R.id.txt_nama)
    TextView txt_nama;

    @BindView(R.id.txt_nis)
    TextView txt_nis;

    public static final int MEDIA_TYPE_IMAGE = 1;
    EditText pass_lama,pass_baru;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    @BindView(R.id.txt_kls)
    TextView txt_kelas;

    @BindView(R.id.txt_tgl)
    TextView txt_tgl;

    private static String imageStoragePath;
    @BindView(R.id.img_profil)
    ImageView img_profil;

    BottomSheetDialog dialog;

    @BindView(R.id.progres_foto)
    ProgressBar progres_foto;


    Bitmap bitmap, decoded;
    String token;
    String ud;
    ProgressDialog pd;
    Validator validator;
    int bitmap_size = 40; // image quality 1 - 100;
    int max_resolution_image = 800;

    private String path;
    SweetAlertDialog pd_new;
    String image = null;

    String foto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_profil);
        ButterKnife.bind(this);
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        status = sharedpreferences.getString(TAG_STATUS, null);
        nis = sharedpreferences.getString(TAG_nis, null);
        id_guru = sharedpreferences.getString(TAG_GURU, null);
        nama = sharedpreferences.getString(TAG_NAMA, null);
        kelas = sharedpreferences.getString(TAG_KELAS, null);
        GET_profil();

        Log.i("nis", "onCreate: "+nis);
    }

    @OnClick(R.id.btn_edit)
    void btn_edit() {
        dialog = new BottomSheetDialog(this);

        dialog.setTitle("Login");
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit_password);
        dialog.setCancelable(false);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setDimAmount(0.5f);
        ButterKnife.bind(this);


        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;


        pass_lama = (EditText) dialog.findViewById(R.id.edit_pw_lama);
        pass_baru = (EditText) dialog.findViewById(R.id.edit_pw_baru);
        final EditText pass_baru2 = (EditText) dialog.findViewById(R.id.edit_konfirmasi);

        pass_lama.requestFocus();
        Button btn_edit = (Button) dialog.findViewById(R.id.btn_edit_pw);
        ImageView btn_close = (ImageView) dialog.findViewById(R.id.btn_close);

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pass_lama.getText().toString().equals("")) {
                    //  Toast.makeText(menu_profil_pejabat_pejabat.this, "Password lama tidak boleh kosong", Toast.LENGTH_SHORT);
                    Toast.makeText(menu_profil.this, "Password lama tidak boleh kosong", Toast.LENGTH_SHORT).show();

                  //  new GlideToast.makeToast(menu_profil.this, "Password lama tidak boleh kosong", GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.BOTTOM).show();
                    pass_lama.requestFocus();
                } else if (pass_baru.getText().toString().trim().equals("")) {
                    Toast.makeText(menu_profil.this, "Password baru tidak boleh kosong", Toast.LENGTH_SHORT).show();
                   // new GlideToast.makeToast(menu_profil.this, "Password baru tidak boleh kosong", GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.BOTTOM).show();

                    // Toast.makeText(menu_profil_pejabat_pejabat.this, "Password baru tidak boleh kosong", Toast.LENGTH_SHORT);
                    pass_baru.requestFocus();
                } else if (pass_baru2.getText().toString().trim().equals("")) {
                    Toast.makeText(menu_profil.this, "Password konfirmasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    //new GlideToast.makeToast(menu_profil.this, "Password konfirmasi tidak boleh kosong", GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.BOTTOM).show();

                    //Toast.makeText(menu_profil_pejabat_pejabat.this, "Password konfirmasi tidak boleh kosong", Toast.LENGTH_SHORT);
                    pass_baru2.requestFocus();
                } else if (!pass_baru.getText().toString().equals(pass_baru2.getText().toString())) {
                    Toast.makeText(menu_profil.this, "pastikan password baru dan konfirmasi password sama !", Toast.LENGTH_SHORT).show();
                   // new GlideToast.makeToast(menu_profil.this, "pastikan password baru dan konfirmasi password sama !", GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.BOTTOM).show();


                    // Toast.makeText(menu_profil_pejabat_pejabat.this, "pastikan password baru dan konfirmasi password sama !", Toast.LENGTH_SHORT);
                    pass_baru2.requestFocus();
                } else if (pass_baru.getText().toString().trim().length() < 6) {
                    Toast.makeText(menu_profil.this, "Minimal Password Baru 6 Karketr", Toast.LENGTH_SHORT).show();
                    //  Toast.makeText(menu_profil_pejabat_pejabat.this, "Minimal Password Baru 6 Karketr", Toast.LENGTH_SHORT);
                   // new GlideToast.makeToast(menu_profil.this, "Minimal Password Baru 6 Karketr", GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.BOTTOM).show();


                    pass_baru.requestFocus();
                } else {
                    updatepass();


                }


            }
        });

        dialog.show();
    }
    @OnClick(R.id.btn_add)
    void btn_add() {
        GET_profil();

        img_profil.setImageResource(0);
        Glide.with(menu_profil.this)
                .load("http://192.168.43.48/penjas/images/profil/"+foto)
                .apply(new RequestOptions()
                        .fitCenter()
                        .circleCrop()
                        .error(R.drawable.ic_document_box))
                .into(img_profil);
        final CharSequence[] items = {"Camera", "Galeri",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(menu_profil.this);
        builder.setTitle("Add Foto!");
        builder.setIcon(R.drawable.ic_certificate_box);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Camera")) {
                    if (CameraUtils.checkPermissions(getApplicationContext())) {
                        captureImage();
                    } else {
                        requestCameraPermission(MEDIA_TYPE_IMAGE);
                    }
                    Glide.with(menu_profil.this)
                            .load("http://192.168.43.48/penjas/images/profil/"+foto)
                            .apply(new RequestOptions()
                                    .fitCenter()
                                    .circleCrop()
                                    .error(R.drawable.ic_document_box))
                            .into(img_profil);

                } else if (items[item].equals("Galeri")) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    Glide.with(menu_profil.this)
                            .load("http://192.168.43.48/penjas/images/profil/"+foto)
                            .apply(new RequestOptions()
                                    .fitCenter()
                                    .circleCrop()
                                    .error(R.drawable.ic_document_box))
                            .into(img_profil);

                    dialog.dismiss();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File file = CameraUtils.getOutputMediaFile(MEDIA_TYPE_IMAGE);
        if (file != null) {
            imageStoragePath = file.getAbsolutePath();
        }

        Uri fileUri = CameraUtils.getOutputMediaFileUri(getApplicationContext(), file);
        foto(fileUri);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    void foto(Uri uri) {
        Glide.with(menu_profil.this)
                .load(uri)
                .apply(new RequestOptions()
                        .fitCenter()
                        .circleCrop()
                        .error(R.drawable.ic_document_box))
                .into(img_profil);
    }
    private void requestCameraPermission(final int type) {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)

                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {

                            if (type == MEDIA_TYPE_IMAGE) {
                                captureImage();
                            } else {
                            }

                        } else if (report.isAnyPermissionPermanentlyDenied()) {
                            showPermissionsAlert();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }
    private void showPermissionsAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissions required!")
                .setMessage("Camera needs few permissions to work properly. Grant them in settings.")
                .setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        CameraUtils.openSettings(menu_profil.this);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }
    @SuppressLint("MissingSuperCall")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                try {


                    BitmapFactory.Options bounds = new BitmapFactory.Options();
                    bounds.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(imageStoragePath, bounds);

                    BitmapFactory.Options opts = new BitmapFactory.Options();
                    Bitmap bm = BitmapFactory.decodeFile(imageStoragePath, opts);
                    ExifInterface exif = new ExifInterface(imageStoragePath);
                    String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
                    int orientation = orientString != null ? Integer.parseInt(orientString) :  ExifInterface.ORIENTATION_NORMAL;

                    int rotationAngle = 0;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;

                    Matrix matrix = new Matrix();
                    matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
                    Bitmap rotatedBitmap = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);



                    //  Bitmap bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath);
                    // bitmap = MediaStore.Images.Media.getBitmap(Tanggapan_admin.this.getContentResolver(), data.getData());
                    setToImageView(getResizedBitmap(rotatedBitmap, max_resolution_image));
                    img_profil.setImageBitmap(rotatedBitmap);
                    edit_foto();

                    //  CameraUtils.refreshGallery(getApplicationContext(), imageStoragePath);

                }
                catch (Exception e){
                    e.printStackTrace();
                }


            } else if (resultCode == RESULT_CANCELED) {

            }
            else {

            }
        }

        if (requestCode == SELECT_FILE && data != null && data.getData() != null) {
            try {


                // mengambil gambar dari Gallery
                bitmap = MediaStore.Images.Media.getBitmap(menu_profil.this.getContentResolver(), data.getData());
                setToImageView(getResizedBitmap(bitmap, max_resolution_image));
                getStringImage(decoded);
                edit_foto();
                Uri tempUri = getImageUri(getApplicationContext(), bitmap);
                foto(tempUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        //Log.i(TAG, "getStringImage: "+encodedImage);
        return encodedImage;
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    private void setToImageView(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));
        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        img_profil.setImageBitmap(decoded);

    }
    public void GET_profil() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.48/penjas/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiRequest service = retrofit.create(ApiRequest.class);

        Call<Response_profil> call = service.profil_siswa(nis);

        call.enqueue(new Callback<Response_profil>() {
            @Override
            public void onResponse(Call<Response_profil> call, Response<Response_profil> response) {

                try {
                    data = response.body().getResult();
                    Log.i("data", "onCreate: "+data);
                    if (data.size()==0){
                        //mShimmerViewContainer.startShimmerAnimation();
                    }else {
                      //  card_data.setVisibility(View.VISIBLE);


                        for (int i = 0; i < data.size(); i++) {


                            txt_nama.setText("" + data.get(i).getNama());
                            txt_nis.setText(""+data.get(i).getNis());
                            foto=data.get(i).getFoto();
                            txt_kelas.setText(""+data.get(i).getNamaKelas());
                            txt_tgl.setText(""+data.get(i).getTempatLahir()+", "+data.get(i).getTglLahir());
                            Glide.with(menu_profil.this)
                                    .load("http://192.168.43.48/penjas/images/profil/"+data.get(i).getFoto())
                                    .listener(new RequestListener<Drawable>() {
                                        @Override
                                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                            progres_foto.setVisibility(View.GONE);
                                            return false;
                                        }

                                        @Override
                                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                            progres_foto.setVisibility(View.GONE);
                                            return false;
                                        }
                                    })
                                    .circleCrop()
                                    .error(R.drawable.books)
                                    .into(img_profil);
                        }
                    }



                    //  txt_alamat.setText("Kecamatann "+kec+" Kelurahan "+kel+" "+" Alamat "+alamat+" Rt "+rt);


                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Response_profil> call, Throwable t) {
                t.printStackTrace();

            }
        });


    }
    void edit_foto() {
        image = getStringImage(decoded);
        // String id_user = SP_user.instance().get_SP_id_user("id_user");
        Pelayanan_service api = Retroserver.getClient().create(Pelayanan_service.class);
        Call<BaseResponse> sendbio = api.edit_foto(image, nis);


        sendbio.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                String value = response.body().getvalue();
                String message = response.body().getMessage();
                //   progress.dismiss();
                if (value.equals("1")) {
                    Toast.makeText(menu_profil.this, "Berhasil Edit Foto", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(menu_profil.this, "Gagal", Toast.LENGTH_SHORT).show();
                    //  Toast.makeText(menu_register_detail_warga_jambi.this, message, Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                // progress.dismiss();
                // Toast.makeText(menu_register_detail_warga_jambi.this, "Jaringan Error!" + t, Toast.LENGTH_SHORT);
            }
        });
    }
    void updatepass() {

        ApiRequest api2 = Retroserver.getClient().create(ApiRequest.class);
        Call<ResponsModel> update2 = api2.Update_pass_siswa(
                nis,
                pass_baru.getText().toString(),
                pass_lama.getText().toString());
        update2.enqueue(new Callback<ResponsModel>() {
            @Override
            public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                Log.d("Retro", "Response_tes");
                Toast.makeText(menu_profil.this, "kode" + response.body().getKode(), Toast.LENGTH_SHORT);
                Log.i("kodeeee", "onResponse: "+response.body().getKode());
                if (response.body().getKode().equals("0")) {
                    Log.i("kodeeee", "onResponse: "+response.body().getKode());
                    // berhail();
                    Toast.makeText(menu_profil.this, "Berhasil Ubah Password", Toast.LENGTH_SHORT).show();

                    dialog.dismiss();

                }
                if (response.body().getKode().equals("1")) {
                    Toast.makeText(menu_profil.this, "Password Lama Salah", Toast.LENGTH_SHORT).show();
                    // Toast.makeText(menu_profil_pejabat_pejabat.this, response.body().getPesan(), Toast.LENGTH_SHORT);
                    // edit_gagal("Passwod Lama Salah");
                }

            }

            @Override
            public void onFailure(Call<ResponsModel> call, Throwable t) {
                // Pd.hide();
                Log.i("Retro", "OnFailure" + t);
                //Toast.makeText(menu_profil_pejabat.this, "gagal update", Toast.LENGTH_SHORT);
            }
        });
    }
      public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            startActivity(new Intent(menu_profil.this, menu_utama.class));
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
