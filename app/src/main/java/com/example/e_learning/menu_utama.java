package com.example.e_learning;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.e_learning.model.model_profil.Response_profil;
import com.example.e_learning.model.model_profil.ResultItem_profil;
import com.example.e_learning.server.ApiRequest;

import java.util.ArrayList;
import java.util.List;

public class menu_utama extends AppCompatActivity {


    @BindView(R.id.txt_nama)
    TextView txt_nama;

    @BindView(R.id.txt_nip)
    TextView txt_nip;

    @BindView(R.id.cardView6)
    CardView cardView6;
    private List<ResultItem_profil> data = new ArrayList<>();
    @BindView(R.id.txt_status)
    TextView txt_status;


    @BindView(R.id.card_siswa)
    CardView card_siswa;


    @BindView(R.id.imageView4)
    ImageView foto_profil;

    String id_guru,nama,nis,kelas,status;
    public final static String TAG_nis = "nis_ambil";
    public final static String TAG_STATUS = "status";
    public final static String TAG_GURU = "id_guru";
    public final static String TAG_NAMA = "nama";
    public final static String TAG_KELAS = "kelas";
    public static final String my_shared_preferences = "my_shared_preferences";
    SharedPreferences sharedpreferences;
    public static final String session_status = "session_status";

    Boolean session = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_utama);
        ButterKnife.bind(this);



        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        status = sharedpreferences.getString(TAG_STATUS, null);
        nis = sharedpreferences.getString(TAG_nis, null);
        id_guru = sharedpreferences.getString(TAG_GURU, null);
        nama = sharedpreferences.getString(TAG_NAMA, null);
        kelas = sharedpreferences.getString(TAG_KELAS, null);

        if (status.equals("siswa")){
            card_siswa.setVisibility(View.GONE);
            txt_status.setText("SISWA");

        }

        if (status.equals("guru")){
           // card_siswa.setVisibility(View.GONE);
            txt_status.setText("GURU");

        }


//        Glide.with(menu_utama.this)
//                .load("http://192.168.1.71/penjas/images/profil/ade.jpg")
//                .apply(new RequestOptions()
//                        .fitCenter()
//                        .circleCrop()
//                        .error(R.drawable.profil))
//                .into(foto_profil);

        GET_profil();


    }


    @OnClick(R.id.imageView4)
    public void PROFIL() {
        if (status.equals("siswa")){
            Intent intent = new Intent(menu_utama.this, menu_profil.class);
            startActivity(intent);

        }else {
            Intent intent = new Intent(menu_utama.this, menu_profil_guru.class);
            startActivity(intent);
        }

    }

    @OnClick(R.id.cardView6)
    public void lihat() {
        // TODO Auto-generated method stub
        Intent intent = new Intent(menu_utama.this, menu_mapel.class);
        startActivity(intent);
    }

    @OnClick(R.id.card_siswa)
    public void card_siswa() {
        // TODO Auto-generated method stub
        Intent intent = new Intent(menu_utama.this, menu_siswa.class);
        startActivity(intent);
    }
    @OnClick(R.id.cardView8)
    public void nilai() {
        if (status.equals("siswa")){
            Intent intent = new Intent(menu_utama.this, menu_nilai.class);
            startActivity(intent);

        }else {
            Intent intent = new Intent(menu_utama.this, menu_nilai_guru.class);
            startActivity(intent);
        }
    }

    @OnClick(R.id.card_quiz)
    public void card_quiz() {
        Intent intent = new Intent(menu_utama.this, menu_quiz.class);
        startActivity(intent);

    }
    @OnClick(R.id.card_ttg)
    public void card_ttg() {
        Intent intent = new Intent(menu_utama.this, menu_tenttang.class);
        startActivity(intent);

    }
    @OnClick(R.id.card_btn)
    public void card_btn() {
        Intent intent = new Intent(menu_utama.this, menu_bantuan.class);
        startActivity(intent);

    }
    @OnClick(R.id.card_klr)
    public void card_klr() {
        // TODO Auto-generated method stub
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah anda yakin ingin keluar ?");


        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                                               SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putBoolean(menu_utama.session_status, false);
                        editor.putString(TAG_nis, null);
                        editor.putString(TAG_NAMA, null);
                        editor.commit();

                        Intent intent = new Intent(menu_utama.this, menu_login.class);
                        finish();
                        startActivity(intent);
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

    public  void  onResume() {

        super.onResume();
        // cek_berita();
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        id_guru = sharedpreferences.getString(TAG_GURU, null);
        status = sharedpreferences.getString(TAG_STATUS, null);
        nis = sharedpreferences.getString(TAG_nis,null);
        nama = sharedpreferences.getString(TAG_NAMA, null);
       // Toast.makeText(this, ""+status+"   "+nis, Toast.LENGTH_SHORT).show();
        if (status.equals("siswa")){
            card_siswa.setVisibility(View.GONE);
            txt_nip.setText("NIK: "+nis);
        }else {
            txt_nip.setText("NIP: "+nis);
        }

        txt_nama.setText(nama);
        Log.i("id_guru", "onResume: "+id_guru + " " + id_guru);
    }

    public void GET_profil() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.71/penjas/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiRequest service = retrofit.create(ApiRequest.class);

        Call<Response_profil> call;

        if (status.equals("siswa")){
            call = service.profil_siswa(nis);
        }else {
            call = service.profil_guru(nis);
        }

//        Call<Response_profil>
//        Call<Response_profil>
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



                            Glide.with(menu_utama.this)
                                    .load("http://192.168.1.71/penjas/images/profil/"+data.get(i).getFoto())
                                    .listener(new RequestListener<Drawable>() {
                                        @Override
                                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                            //progres_foto.setVisibility(View.GONE);
                                            return false;
                                        }

                                        @Override
                                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                          //  progres_foto.setVisibility(View.GONE);
                                            return false;
                                        }
                                    })
                                    .circleCrop()
                                    .error(R.drawable.books)
                                    .into(foto_profil);
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
