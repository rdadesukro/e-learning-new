package com.example.e_learning_penjas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.function.Predicate;

import butterknife.BindView;
import butterknife.ButterKnife;

public class menu_detail_siswa extends AppCompatActivity {
    String nama,nis,jk,no_hp,kelas,foto,alamat,tgl,tempat;

    @BindView(R.id.txt_nama)
    TextView txt_nama;

    @BindView(R.id.txt_nis)
    TextView txt_nis;

    @BindView(R.id.txt_jk)
    TextView txt_jk;

    @BindView(R.id.txt_kls)
    TextView txt_kelas;

    @BindView(R.id.txt_tgl)
    TextView txt_tgl;

    @BindView(R.id.txt_no)
    TextView txt_no;


    @BindView(R.id.img_profil)
    ImageView img_profil;



    @BindView(R.id.progres_foto)
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail_siswa);
        ButterKnife.bind(this);

        Bundle b = getIntent().getExtras();
        nama= (String) b.getCharSequence("nama");
        nis= (String) b.getCharSequence("nis");
        alamat= (String) b.getCharSequence("alamat");
        tempat= (String) b.getCharSequence("tempat");
        jk= (String) b.getCharSequence("jk");
        no_hp= (String) b.getCharSequence("no");
        foto= (String) b.getCharSequence("foto");
        kelas= (String) b.getCharSequence("kelas");
        foto= (String) b.getCharSequence("foto");
        tgl= (String) b.getCharSequence("tgl");

        txt_nama.setText(nama);
        if (jk.equals("LK")){
            txt_jk.setText("Laki-Laki");
        }else {
            txt_jk.setText("Perempuan");
        }

        txt_kelas.setText(kelas);
        txt_nis.setText(nis);
        txt_tgl.setText(tempat+", "+tgl);
        txt_no.setText(no_hp);


        Glide.with(menu_detail_siswa.this)
                .load("http://192.168.1.71/penjas/images/profil/"+foto)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                       progressBar.setVisibility(View.GONE);
                        return false;
                    }
                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                       progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(img_profil);


    }
}
