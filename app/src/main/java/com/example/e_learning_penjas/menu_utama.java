package com.example.e_learning_penjas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import static com.example.e_learning_penjas.app.AppConfig.TAG_ID;

public class menu_utama extends AppCompatActivity {


    @BindView(R.id.txt_nama)
    TextView txt_nama;

    @BindView(R.id.txt_nip)
    TextView txt_nip;

    @BindView(R.id.cardView6)
    CardView cardView6;


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

        }

        Glide.with(menu_utama.this)
                .load("http://192.168.1.71/penjas/images/profil/ade.jpg")
                .apply(new RequestOptions()
                        .fitCenter()
                        .circleCrop()
                        .error(R.drawable.profil))
                .into(foto_profil);
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
        Toast.makeText(this, ""+status+"   "+nis, Toast.LENGTH_SHORT).show();
        if (status.equals("siswa")){
            card_siswa.setVisibility(View.GONE);
            txt_nip.setText("NIK: "+nis);
        }else {
            txt_nip.setText("NIP: "+nis);
        }

        txt_nama.setText(nama);
        Log.i("id_guru", "onResume: "+id_guru + " " + id_guru);
    }
}
