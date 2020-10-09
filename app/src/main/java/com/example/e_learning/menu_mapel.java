package com.example.e_learning;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.e_learning.model.BaseResponse;
import com.example.e_learning.model.model_materi.Response_materi;
import com.example.e_learning.model.model_materi.ResultItem_materi;
import com.example.e_learning.server.ApiRequest;
import com.example.e_learning.server.MyInterface;
import com.example.e_learning.server.Retroserver;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.krishna.fileloader.FileLoader;
import com.krishna.fileloader.listener.FileRequestListener;
import com.krishna.fileloader.pojo.FileResponse;
import com.krishna.fileloader.request.FileLoadRequest;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class menu_mapel extends AppCompatActivity implements OnLoadCompleteListener, OnPageErrorListener {
    private String pdfPath;
    private ListView mGridView;
    private Adapter_detail_laporan_saya adapter;
   
    TextView txt_nama_pelayanan;
    Dialog a1CDialog;
    ImageView image1;
    private static String imageStoragePath;
    String image = null;
    EditText data_pdf;
    String nama,id_regsis;
    Intent intent;
    EditText nama_materi,bab,smester;
    String nama_judul;
    public final static String TAG_NAMA = "nama";
    public final static String TAG_STATUS = "status";
    public final static String TAG_GURU = "id_guru";
    public final static String TAG_nik = "nis_ambil";
    public static final String my_shared_preferences = "my_shared_preferences";
    SharedPreferences sharedpreferences;
    public static final String session_status = "session_status";
    RequestBody coba;
    Uri fileUri;
    private static final String IMAGE_DIRECTORY = "/demonuts_upload_gallery";

    File file;
    public static final int FILE_PICKER_REQUEST_CODE = 1;
    String imageTempName;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
   // final PermissionManager permissionManager = new PermissionManager();
    private String path;
    Button btn_choose_image;
    ImageView imageView;
    String id_guru;
    String status;
    Bitmap bitmap, decoded;
    public final int REQUEST_CAMERA = 0;
    public final int SELECT_FILE = 1;
    private final int requestCode = 20;
    int bitmap_size = 100; // image quality 1 - 100;
    int max_resolution_image = 2000;
    ProgressDialog pd;
    String data_dokumen;
    Uri tempUri;
    Dialog dialog;
    Boolean session = false;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;

    ResultItem_materi thisDataModel;
    private ListView mRecycler;
    int position;
    private static final int PHOTO_REQUEST_CODE = 102;
    private List<ResultItem_materi> data = new ArrayList<>();
    SwipeRefreshLayout mSwipeRefreshLayout;
    private static final String TAG = menu_mapel.class.getSimpleName();

    @Override
    public void loadComplete(int nbPages) {

    }

    @Override
    public void onPageError(int page, Throwable t) {

    }

    class Adapter_detail_laporan_saya extends BaseAdapter {

        private List<ResultItem_materi> DataModels;
        private Context context;


        public Adapter_detail_laporan_saya(Context context, List<ResultItem_materi> DataModels) {
            this.context = context;
            this.DataModels = DataModels;
        }

        @Override
        public int getCount() {
            return DataModels.size();
        }

        @Override
        public Object getItem(int pos) {
            return DataModels.get(pos);
        }

        @Override
        public long getItemId(int pos) {
            return pos;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.model_mapel, viewGroup, false);
            }

            TextView nameTxt = view.findViewById(R.id.txt_nama);
             TextView bab = view.findViewById(R.id.txt__bab);
            ImageView baca = view.findViewById(R.id.img_baca);
            ImageView unduh = view.findViewById(R.id.btn_unduh);
            ImageView edit = view.findViewById(R.id.btn_edit);
            ImageView hapus = view.findViewById(R.id.btn_hapus);


            TextView status1 = view.findViewById(R.id.txt_status);






            ResultItem_materi thisDataModel = DataModels.get(position);

            nameTxt.setText(thisDataModel.getNama());
            bab.setText(thisDataModel.getBab());
          //  Toast.makeText(context, ""+status, Toast.LENGTH_SHORT).show();

            if (status.equals("guru")){
                edit.setVisibility(View.VISIBLE);
                unduh.setVisibility(View.GONE);
                hapus.setVisibility(View.VISIBLE);
                status1.setVisibility(View.GONE);

            }else{
                edit.setVisibility(View.GONE);

                if (thisDataModel.getStatus().equals("0")){
                    status1.setVisibility(View.GONE);
                }else {
                    status1.setVisibility(View.VISIBLE);
                }
            }

//            if (thisDataModel.getStatus().equals("0")){
//                status1.setVisibility(View.VISIBLE);
//            }else{
//                status1.setVisibility(View.GONE);
//            }




            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                  //  String lihat= thisDataModel.getUrl();
                    new Webview(context,thisDataModel.getUrl());


                }


            });

            hapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(menu_mapel.this);
                    alertDialogBuilder.setMessage("Apakah anda yakin ingin Hapus?");


                    alertDialogBuilder.setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {

                                        MyInterface api = Retroserver.getClient().create(MyInterface.class);

                                        Call<BaseResponse> sendbio = api.hapus(thisDataModel.getIdMateri());


                                        // //Toast.makeText(this, "image" + (i + 1), //Toast.LENGTH_SHORT);
                                        sendbio.enqueue(new Callback<BaseResponse>() {
                                            @Override
                                            public void onResponse(Call<BaseResponse> call, retrofit2.Response<BaseResponse> response) {

                                                String kode = response.body().getvalue();

                                                if (kode.equals("1")){
                                                    berhasil("Sukses Hapus Materi");
                                                    get_data();
                                                    Toast.makeText(context, "Behasil Hapus Data", Toast.LENGTH_SHORT).show();
                                                }else {
                                                    Toast.makeText(context, "gagal", Toast.LENGTH_SHORT).show();
                                                }


                                            }


                                            @Override
                                            public void onFailure(Call<BaseResponse> call, Throwable t) {

                                                Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
                                            }
                                        });
                                    }




                            });

                    alertDialogBuilder.setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {

                                }
                            });

                    //Showing the alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }



            });

            unduh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  new DownloadTask(context, "http://192.168.1.71/penjas/materi/"+thisDataModel.getUrl());


                }
            });



            baca.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {



                    Rect displayRectangle = new Rect();
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
                    LayoutInflater li = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                    view = li.inflate(R.layout.baca, null);

//
                    final PDFView pdfView= view.findViewById(R.id.pdfView);
                    Button close = view.findViewById(R.id.ib_close);
                    ProgressBar pdfViewProgressBar= view.findViewById(R.id.progressBar2);
                    view.setMinimumWidth((int)(displayRectangle.width() * 1f));
                    view.setMinimumHeight((int)(displayRectangle.height() * 1f));
                    mBuilder.setView(view);
                    Log.i("data_materi", "onClick: "+thisDataModel.getUrl());
                    AlertDialog mDialog = mBuilder.create();//
                    mDialog.show();
                    FileLoader.with(menu_mapel.this)
                            .load("http://192.168.1.71/penjas/materi/"+thisDataModel.getUrl(),false) //2nd parameter is optioal, pass true to force load from network
                            .fromDirectory("My_PDFs", FileLoader.DIR_INTERNAL)
                            .asFile(new FileRequestListener<File>() {
                                @Override
                                public void onLoad(FileLoadRequest request, FileResponse<File> response) {

                                    File pdfFile = response.getBody();
                                    pdfViewProgressBar.setVisibility(View.GONE);
                                    Log.i("data_pdf", "onLoad: "+pdfFile);
                                    try {
                                        pdfView.fromFile(pdfFile)
                                                .defaultPage(1)
                                                .enableAnnotationRendering(true)
                                                .onLoad(menu_mapel.this)
                                                .scrollHandle(new DefaultScrollHandle(menu_mapel.this))
                                                .spacing(10) // in dp
                                                .onError(t ->  pdfViewProgressBar.setVisibility(View.VISIBLE) )
                                                .onPageError((page, t) -> Toast.makeText(menu_mapel.this, "", Toast.LENGTH_SHORT).show()
                                                )
                                                .load();

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Log.i("data_error", "onLoad: "+e);
                                    }
                                }
                                @Override
                                public void onError(FileLoadRequest request, Throwable t) {
                                      pdfViewProgressBar.setVisibility(View.GONE);
                                    Toast.makeText(menu_mapel.this, t.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });

                    close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Dismiss the popup window
                            mDialog.dismiss();
                        }
                    });


                }
            });
            edit.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {



                   // Toast.makeText(menu_detail_laporan_pelayanan.this, "id" + thisDataModel.getIdSyarat(), Toast.LENGTH_SHORT).show();
                    dialog = new Dialog(context);
                    dialog.setTitle("Login");
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialog_edit_materi);
                    dialog.setCancelable(false);

                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                    lp.copyFrom(dialog.getWindow().getAttributes());
                    dialog.getWindow().setAttributes(lp);
                    dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    dialog.getWindow().setDimAmount(0.5f);


                    lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
                    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;





                    ImageView close = (ImageView) dialog.findViewById(R.id.btn_close);
                    Button ambil_data = (Button) dialog.findViewById(R.id.btn_pilih);
                    data_pdf= (EditText) dialog.findViewById(R.id.edit_data);
                    TextView judul = (TextView) dialog.findViewById(R.id.txt_judul);
                    judul.setText("Edit Materi");
                    data_pdf.setText(thisDataModel.getUrl());
                    nama_materi= (EditText) dialog.findViewById(R.id.edit_nama_mapel);
                   EditText  bab_edit= (EditText) dialog.findViewById(R.id.edit_bab);
                    smester= (EditText) dialog.findViewById(R.id.edit_sms);

                    nama_materi.setText(thisDataModel.getNama());
                    bab_edit.setText(thisDataModel.getBab());
                    smester.setText(thisDataModel.getSmester());


                    Button dialogButton = (Button) dialog.findViewById(R.id.btn_edit_no);

                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onClick(View v) {

                            if (checkInternet()) {

                                if (tempUri==null) {
                                    pesan("Anda Belum Amabil File");


                                }else {



                                    Log.i("data_aku", "onClick: "+file);
                                    //  MyInterface api = Retroserver.getClient().create(MyInterface.class);
                                    //  RequestBody id_syarat = createPartFromString(""+thisDataModel.getIdMateri());
//
                                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                                    RequestBody id_syarat = RequestBody.create(MediaType.parse("multipart/form-data"), thisDataModel.getIdMateri());
                                    RequestBody bab1 = RequestBody.create(MediaType.parse("multipart/form-data"), bab_edit.getText().toString());
                                    RequestBody sms = RequestBody.create(MediaType.parse("multipart/form-data"), smester.getText().toString());
                                    RequestBody nam = RequestBody.create(MediaType.parse("multipart/form-data"), nama_materi.getText().toString());
                                    MultipartBody.Part body =
                                            MultipartBody.Part.createFormData("materi", file.getName(), requestFile);
                                    ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
                                    data_pdf.setText(file.getName());
                                    Call<BaseResponse> sendbio = api.Edit_data_materi(
                                            id_syarat,
                                            nam,
                                            sms,
                                            bab1,
                                            body);


                                    // Toast.makeText(this, "image" + (i + 1), Toast.LENGTH_SHORT).show();
                                    sendbio.enqueue(new Callback<BaseResponse>() {
                                        @Override
                                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {

                                            String kode = response.body().getvalue();
                                            //   String value = response.body().getvalue();
                                            //  progress.dismiss();
                                            if (kode.equals("1")) {
                                                berhasil("Sukses Edeit Materi");
                                                dialog.dismiss();
                                                Toast.makeText(menu_mapel.this, "Behasil Edit", Toast.LENGTH_SHORT).show();
                                                tempUri=null;


                                                get_data();
                                            }

                                            if (kode.equals("0")) {
                                                Toast.makeText(menu_mapel.this, "Gagal", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<BaseResponse> call, Throwable t) {

                                            Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
                                        }
                                    });

                                }






                                }
                            }






                    });
                    ambil_data.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            new MaterialFilePicker()
                                    .withActivity(menu_mapel.this)
                                    .withRequestCode(FILE_PICKER_REQUEST_CODE)
                                    .withHiddenFiles(true)
                                    .withFilter(Pattern.compile(".*\\.pdf$"))
                                    .withTitle("Select PDF file")
                                    .start();
                        }



                    });


                    close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                }
            });




            return view;
        }


    }

//    @NonNull
//    private RequestBody createPartFromString(String descriptionString) {
//        return RequestBody.create(MediaType.parse(FileUtils.MIME_TYPE_TEXT), descriptionString);
//    }

    public boolean checkInternet(){
        boolean connectStatus = true;
        ConnectivityManager ConnectionManager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()==true ) {
            connectStatus = true;
            // card_tidak_ada.setVisibility(View.VISIBLE);
        }
        else {
            connectStatus = false;
            // Display message in dialog box if you have internet connection
//            SweetAlertDialog pDialog = newFileUtils SweetAlertDialog(menu_detail_laporan_pelayanan.this, SweetAlertDialog.WARNING_TYPE);
//            pDialog.setTitleText("Oppsss....!!1");
//            pDialog.setContentText("Anda Membutukan Koneksi Untuk Menyimpan Berkas!");
//            pDialog.setCancelable(false);
//            pDialog.setConfirmText("OK");
//            pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                @Override
//                public void onClick(SweetAlertDialog sweetAlertDialog) {
//                    sweetAlertDialog.dismissWithAnimation();
//                }
//            });
//            pDialog.setCanceledOnTouchOutside(false);
//            pDialog.show();
        }
        return connectStatus;
    }







    public static String getFileName(Uri uri) {
        if (uri == null) return null;
        String fileName = null;
        String path = uri.getPath();
        int cut = path.lastIndexOf('/');
        if (cut != -1) {
            fileName = path.substring(cut + 1);
        }
        return fileName;
    }





    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

                if (requestCode == FILE_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
                    String path = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
                     file = new File(path);
                    Uri uri = Uri.fromFile(new File(file.getAbsolutePath()));
                    Log.i("data_akkk", "onActivityResult: "+file);
                    displayFromFile(file);
                    data_pdf.setVisibility(View.VISIBLE);
//                    image1.setVisibility(View.GONE);
                    data_pdf.setText(""+file);
                    try {

                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
                        // image1.setImageBitmap(bitmap);
                        //   setToImageView(getResizedBitmap(bitmap, max_resolution_image));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (path != null) {
                        Log.d("Path: ", path);
                        pdfPath = path;
                      //  Toast.makeText(this, "Picked file: " + file +" uri" + uri, Toast.LENGTH_LONG).show();
                    }
                }





    }


     @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void displayFromFile(File file1) {


        tempUri = Uri.fromFile(new File(file1.getAbsolutePath()));
        //file =  FileUtils.getFile(this, tempUri);


    }
    public Uri getImageUri(Context inContext, Bitmap inImage, String imageName) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, imageName, null);
        return Uri.parse(path);
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







    private void populateGridView(List<ResultItem_materi> DataModelList) {
        mGridView = findViewById(R.id.rv);
        adapter = new Adapter_detail_laporan_saya(this,DataModelList);
        mGridView.setAdapter(adapter);
    }



    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_mapel);
        ButterKnife.bind(this);

        Bundle b = getIntent().getExtras();
        // cek_berita();
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        id_guru = sharedpreferences.getString(TAG_GURU, null);
        status = sharedpreferences.getString(TAG_STATUS, null);
        pd = new ProgressDialog(this);
        if (status.equals("guru")){
            floatingActionButton.setVisibility(View.VISIBLE);
        }else {
            floatingActionButton.setVisibility(View.GONE);
        }

        //Toast.makeText(this, "id_regis "+id_regsis, Toast.LENGTH_SHORT).show();
//        txt_nama_pelayanan.setText(nama);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swifeRefresh);
        mRecycler = (ListView) findViewById(R.id.rv);

        mSwipeRefreshLayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                get_data();

            }
        });



        get_data();


    }


    public void get_data(){

        ApiRequest api = Retroserver.getClient().create(ApiRequest.class);

        Call<Response_materi> call = api.Get_data_materi(id_guru);

        call.enqueue(new Callback<Response_materi>() {

            @Override
            public void onResponse(Call<Response_materi> call, Response<Response_materi>response) {
                // myProgressBar.setVisibility(View.GONE);

                populateGridView(response.body().getResult());
                if (response.body().getResult().size()==0){
                    Toast.makeText(menu_mapel.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();

                }else {

                }
                mSwipeRefreshLayout.setRefreshing(false);



                Log.i("Pesan", "onResponse: "+response.body().getResult());
            }
            @Override
            public void onFailure(Call<Response_materi> call, Throwable throwable) {

                // myProgressBar.setVisibility(View.GONE);
                Toast.makeText(menu_mapel.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }






    @Override
    protected void onStart() {
        super.onStart();
        get_data();


    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            startActivity(new Intent(menu_mapel.this, menu_utama.class));
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    public  void  onResume() {

        super.onResume();
        get_data();

        // cek_berita();
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        id_guru = sharedpreferences.getString(TAG_GURU, null);
        status = sharedpreferences.getString(TAG_STATUS, null);
        Log.i("id_guru", "onResume: "+id_guru + " " + status);
    }

    @OnClick(R.id.floatingActionButton)
    public void lihat() {
        // Toast.makeText(menu_detail_laporan_pelayanan.this, "id" + thisDataModel.getIdSyarat(), Toast.LENGTH_SHORT).show();
        dialog = new Dialog(this);
        dialog.setTitle("Login");
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_tambah_materi);
        dialog.setCancelable(false);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setDimAmount(0.5f);


        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;





        ImageView close = (ImageView) dialog.findViewById(R.id.btn_close);
        TextView judul = (TextView) dialog.findViewById(R.id.txt_judul);
        Button ambil_data = (Button) dialog.findViewById(R.id.btn_pilih);
        data_pdf= (EditText) dialog.findViewById(R.id.edit_data);
        nama_materi= (EditText) dialog.findViewById(R.id.edit_nama_mapel);
        bab= (EditText) dialog.findViewById(R.id.edit_bab);
        smester= (EditText) dialog.findViewById(R.id.edit_sms);


        judul.setText("Tambah Materi");

        Button dialogButton = (Button) dialog.findViewById(R.id.btn_edit_no);

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                if (checkInternet()) {
                    if (tempUri==null) {
                        pesan("Anda Belum Amabil File");


//


                    }else if (nama_materi.getText().toString().trim().equals("") ){
                        pesan("Pastikan Input Semua Data");

                    }else if (bab.getText().toString().trim().equals("")){
                        pesan("Pastikan Input Semua Data");
                    }else if (smester.getText().toString().trim().equals("")){
                        pesan("Pastikan Input Semua Data");
                    } else {



                        Log.i("data_aku", "onClick: "+file);
                        //  MyInterface api = Retroserver.getClient().create(MyInterface.class);
                        //  RequestBody id_syarat = createPartFromString(""+thisDataModel.getIdMateri());
//
                        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                        RequestBody bab1 = RequestBody.create(MediaType.parse("multipart/form-data"), bab.getText().toString());
                        RequestBody sms = RequestBody.create(MediaType.parse("multipart/form-data"), smester.getText().toString());
                        RequestBody nam = RequestBody.create(MediaType.parse("multipart/form-data"), nama_materi.getText().toString());
                        RequestBody guru = RequestBody.create(MediaType.parse("multipart/form-data"), id_guru);

                        MultipartBody.Part body =
                                MultipartBody.Part.createFormData("materi", file.getName(), requestFile);
                        ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
                        data_pdf.setText(file.getName());
                        Call<BaseResponse> sendbio = api.tambah_materi(
                                nam,
                                sms,
                                bab1,
                                guru,
                                body);


                        // Toast.makeText(this, "image" + (i + 1), Toast.LENGTH_SHORT).show();
                        sendbio.enqueue(new Callback<BaseResponse>() {
                            @Override
                            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {

                                String kode = response.body().getvalue();
                                //   String value = response.body().getvalue();
                                //  progress.dismiss();
                                if (kode.equals("1")) {
                                    tempUri=null;
                                    berhasil("Sukses Tambah Materi");
                                    Toast.makeText(menu_mapel.this, "Berhasil Simpan Data", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();

                                    get_data();
                                }

                                if (kode.equals("0")) {
                                    Toast.makeText(menu_mapel.this, "Gagal", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<BaseResponse> call, Throwable t) {

                                Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
                            }
                        });





                    }
                }

            }
        });
        ambil_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new MaterialFilePicker()
                        .withActivity(menu_mapel.this)
                        .withRequestCode(FILE_PICKER_REQUEST_CODE)
                        .withHiddenFiles(true)
                        .withFilter(Pattern.compile(".*\\.pdf$"))
                        .withTitle("Select PDF file")
                        .start();
            }



        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    void pesan(String peasan){
        SweetAlertDialog pDialog = new SweetAlertDialog(menu_mapel.this, SweetAlertDialog.ERROR_TYPE);
        pDialog.setTitle("Oops...");
        pDialog.setContentText(peasan);
        pDialog.setCancelable(false);
        pDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                pDialog.dismissWithAnimation();
            }
        });
        pd.setCanceledOnTouchOutside(false);
        pDialog.show();
    }

    void berhasil(String peasan){
        SweetAlertDialog pDialog = new SweetAlertDialog(menu_mapel.this, SweetAlertDialog.SUCCESS_TYPE);
        pDialog.setTitle("Berhasil");
        pDialog.setContentText(peasan);
        pDialog.setCancelable(false);
        pDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                pDialog.dismissWithAnimation();
            }
        });
        pd.setCanceledOnTouchOutside(false);
        pDialog.show();
    }

}

