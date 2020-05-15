package com.example.e_learning_penjas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import com.bumptech.glide.request.target.Target;
import com.example.e_learning_penjas.model.model_profil.Response_profil;
import com.example.e_learning_penjas.model.model_profil.ResultItem_profil;
import com.example.e_learning_penjas.model.model_siswa.Response_siswa;
import com.example.e_learning_penjas.model.model_siswa.ResultItem_siswa;
import com.example.e_learning_penjas.server.ApiRequest;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

    Boolean session = false;

    String status,nis,id_guru,nama,kelas;

    private List<ResultItem_profil> data = new ArrayList<>();

    @BindView(R.id.txt_nama)
    TextView txt_nama;

    @BindView(R.id.txt_nis)
    TextView txt_nis;


    @BindView(R.id.txt_kls)
    TextView txt_kelas;

    @BindView(R.id.txt_tgl)
    TextView txt_tgl;


    @BindView(R.id.img_profil)
    ImageView img_profil;

    BottomSheetDialog dialog;

    @BindView(R.id.progres_foto)
    ProgressBar progres_foto;

    EditText pass_lama,pass_baru;
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

                  //  new GlideToast.makeToast(menu_profil_admin.this, "Password lama tidak boleh kosong", GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.BOTTOM).show();
                    pass_lama.requestFocus();
                } else if (pass_baru.getText().toString().trim().equals("")) {
                    Toast.makeText(menu_profil.this, "Password baru tidak boleh kosong", Toast.LENGTH_SHORT).show();
                   // new GlideToast.makeToast(menu_profil_admin.this, "Password baru tidak boleh kosong", GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.BOTTOM).show();

                    // Toast.makeText(menu_profil_pejabat_pejabat.this, "Password baru tidak boleh kosong", Toast.LENGTH_SHORT);
                    pass_baru.requestFocus();
                } else if (pass_baru2.getText().toString().trim().equals("")) {
                    Toast.makeText(menu_profil.this, "Password konfirmasi tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    //new GlideToast.makeToast(menu_profil_admin.this, "Password konfirmasi tidak boleh kosong", GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.BOTTOM).show();

                    //Toast.makeText(menu_profil_pejabat_pejabat.this, "Password konfirmasi tidak boleh kosong", Toast.LENGTH_SHORT);
                    pass_baru2.requestFocus();
                } else if (!pass_baru.getText().toString().equals(pass_baru2.getText().toString())) {
                    Toast.makeText(menu_profil.this, "pastikan password baru dan konfirmasi password sama !", Toast.LENGTH_SHORT).show();
                   // new GlideToast.makeToast(menu_profil_admin.this, "pastikan password baru dan konfirmasi password sama !", GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.BOTTOM).show();


                    // Toast.makeText(menu_profil_pejabat_pejabat.this, "pastikan password baru dan konfirmasi password sama !", Toast.LENGTH_SHORT);
                    pass_baru2.requestFocus();
                } else if (pass_baru.getText().toString().trim().length() < 6) {
                    Toast.makeText(menu_profil.this, "Minimal Password Baru 6 Karketr", Toast.LENGTH_SHORT).show();
                    //  Toast.makeText(menu_profil_pejabat_pejabat.this, "Minimal Password Baru 6 Karketr", Toast.LENGTH_SHORT);
                   // new GlideToast.makeToast(menu_profil_admin.this, "Minimal Password Baru 6 Karketr", GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.BOTTOM).show();


                    pass_baru.requestFocus();
                } else {
                   // updatepass();


                }


            }
        });

        dialog.show();
    }
    public void GET_profil() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.71/penjas/")
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
                                    .load("http://192.168.1.71/penjas/images/profil/"+data.get(i).getFoto())
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

}
