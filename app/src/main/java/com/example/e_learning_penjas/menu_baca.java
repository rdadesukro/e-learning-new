package com.example.e_learning_penjas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.e_learning_penjas.model.BaseResponse;
import com.example.e_learning_penjas.server.ApiRequest;
import com.example.e_learning_penjas.server.Retroserver;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.krishna.fileloader.FileLoader;
import com.krishna.fileloader.listener.FileRequestListener;
import com.krishna.fileloader.pojo.FileResponse;
import com.krishna.fileloader.request.FileLoadRequest;

import java.io.File;

public class menu_baca extends AppCompatActivity implements OnLoadCompleteListener, OnPageErrorListener {
    ProgressBar pdfViewProgressBar;
    Boolean session = false;


    public final static String TAG_ID = "id_pemohon";
    public static final String session_status = "session_status";
    SharedPreferences sharedpreferences;
    String token;
    public static final String my_shared_preferences = "my_shared_preferences";
    String id;
     String id_materi;
    String id_layanan;
    @BindView(R.id.toolbar3)
    Toolbar toolbar3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_baca);
        ButterKnife.bind(this);

        final PDFView pdfView= findViewById(R.id.pdfView);
        pdfViewProgressBar=findViewById(R.id.pdfViewProgressBar);

        pdfViewProgressBar.setVisibility(View.VISIBLE);
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        id = sharedpreferences.getString(TAG_ID, "ade");
        //UNPACK OUR DATA FROM INTENT
        Intent i=this.getIntent();
        final String path=i.getExtras().getString("url");
        id_materi =i.getExtras().getString("id_materi");
        String nama =i.getExtras().getString("nama");
        Toast.makeText(this, ""+path +"  "+id_materi, Toast.LENGTH_SHORT).show();
        toolbar3.setTitle(nama);
        Log.i("data_materi", "onCreate: "+path);

        edit();


        FileLoader.with(this)
                .load("http://192.168.43.48/penjas/materi/"+path,false) //2nd parameter is optioal, pass true to force load from network
                .fromDirectory("My_PDFs", FileLoader.DIR_INTERNAL)
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                        pdfViewProgressBar.setVisibility(View.GONE);
                        File pdfFile = response.getBody();
                        try {
                            pdfView.fromFile(pdfFile)
                                    .defaultPage(1)
                                    .enableAnnotationRendering(true)
                                    .onLoad(menu_baca.this)
                                    .scrollHandle(new DefaultScrollHandle(menu_baca.this))
                                    .spacing(10) // in dp
                                    .onPageError(menu_baca.this)
                                    .load();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {
                        pdfViewProgressBar.setVisibility(View.GONE);
                        Toast.makeText(menu_baca.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

    }

    void edit (){
        ApiRequest api2 = Retroserver.getClient().create(ApiRequest.class);
        Call<BaseResponse> update2 = api2.Up_baca(id_materi);
        update2.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                Log.d("Retro", "Response_tes");
                String kode = response.body().getKode();
                if (kode.equals("1")){
                    Toast.makeText(menu_baca.this, "sukses", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(menu_baca.this, "gagal", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(menu_baca.this, "kode"+response.body().getKode(), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                // Pd.hide();
                Log.d("Retro", "OnFailure");
                Toast.makeText(menu_baca.this,"gagal update", Toast.LENGTH_SHORT).show();
            }
        });
    }



    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            startActivity(new Intent(menu_baca.this, menu_mapel.class));
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void loadComplete(int nbPages) {
        pdfViewProgressBar.setVisibility(View.GONE);
        Toast.makeText(menu_baca.this, String.valueOf(nbPages), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPageError(int page, Throwable t) {
        pdfViewProgressBar.setVisibility(View.GONE);
        Toast.makeText(menu_baca.this, t.getMessage(), Toast.LENGTH_LONG).show();
    }
}
