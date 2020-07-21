package com.example.e_learning_penjas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_learning_penjas.model.model_cek_quiz.Response_cek;
import com.example.e_learning_penjas.model.model_cek_quiz.ResultItem_cek;
import com.example.e_learning_penjas.server.ApiRequest;
import com.example.e_learning_penjas.server.Retroserver;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class menu_quiz extends AppCompatActivity {
    @NotEmpty
    @BindView(R.id.card_quiz_saatu)
    CardView card_quiz_saatu;
    private List<ResultItem_cek> data = new ArrayList<>();
    @NotEmpty
    @BindView(R.id.card_quiz_dua)
    CardView card_quiz_dua;
    String nis,quiz,nis_tampung;
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
        setContentView(R.layout.menu_quiz);
        ButterKnife.bind(this);
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        nis = sharedpreferences.getString(TAG_nis, null);

    }

    @OnClick(R.id.card_quiz_saatu)
    public void card_quiz_saatu() {
//        Intent intent = new Intent(menu_quiz.this, quiz_satu.class);
//        startActivity(intent);
        cek("1");


    }


    @OnClick(R.id.card_quiz_dua)
    public void card_quiz_dua() {

//        Intent intent = new Intent(menu_quiz.this, quiz_dua.class);
//        startActivity(intent);
        cek("2");
    }
    void cek(String quiz_tampung){
        ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
        Call<Response_cek> call = api.cek_quiz(nis,quiz_tampung);
        call.enqueue(new Callback<Response_cek>() {
            @Override
            public void onResponse(Call<Response_cek> call, Response<Response_cek> response) {


              //  final List<Response_cek> masalah_list = response.body().getResult();
                data = response.body().getResult();



                if (data.size()==0) {
                    if (quiz_tampung.equals("1")){
                        Intent intent = new Intent(menu_quiz.this, quiz_satu.class);
                            startActivity(intent);
                    }else {
                        Intent intent = new Intent(menu_quiz.this, quiz_dua.class);
                         startActivity(intent);
                    }

                }else {
                    Toast.makeText(menu_quiz.this, "Sudah Quiz", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Response_cek> call, Throwable t) {
                t.printStackTrace();
                if (t instanceof IOException) {



                }
                else {

                    //  Toast.makeText(ErrorHandlingActivity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();


                }
            }
        });

    }

}
