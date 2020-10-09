package com.example.e_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_learning.model.ResponsModel;
import com.example.e_learning.server.ApiRequest;
import com.example.e_learning.server.Retroserver;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;

public class menu_lupa_password extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty
    @BindView(R.id.txt_nik)
    EditText txt_nik;


    @NotEmpty
    @BindView(R.id.txt_tanggal)
    EditText txt_tanggal;

    @NotEmpty
    @BindView(R.id.txt_bulan)
    EditText txt_bulan;

    @NotEmpty
    @BindView(R.id.txt_tahun)
    EditText txt_tahun;

    @NotEmpty
    @Password(min = 6,message = "Minimal Password 6 Karakter")
    @BindView(R.id.txt_pass)
    EditText txt_pass;

    @NotEmpty
    @Password(min = 6,message = "Minimal Password 6 Karakter")
    @BindView(R.id.txt_konfirmasi)
    EditText txt_konfirmasi;

    @NotEmpty
    @BindView(R.id.btn_lupa)
    Button btn_lupa;
    Validator validator;
    String status;



    @BindView(R.id.spiner_login)
    Spinner spiner_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_lupa_password);
        ButterKnife.bind(this);
        validator = new Validator(this);
        validator.setValidationListener(this);

        String[] bencana = new String[]{
                "Pilih Login...",
                "Guru",
                "Siswa"

        };
        final List<String> bencanaList = new ArrayList<>( Arrays.asList(bencana));

        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,bencanaList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor( Color.parseColor("#673AB7"));
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spiner_login.setAdapter(spinnerArrayAdapter);

        spiner_login.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                    status=selectedItemText;

                    if (status.equals("Guru")){
                        txt_nik.setHint("NIP");
                    }else if (status.equals("Siswa")){
                        txt_nik.setHint("NIS");
                    }

                    if (selectedItemText.equals("A")){

                    }else {
                        //  id_mapel.setText("2");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }
    @OnClick(R.id.btn_lupa)
    void spin5() {
        validator.validate();

    }


    void check_validasi_reset_pass() {

        String nik = txt_nik.getText().toString().trim();
        String tgl= txt_tanggal.getText().toString().trim();
        String bulan= txt_bulan.getText().toString().trim();
        String tahun= txt_tahun.getText().toString().trim();
        String password= txt_pass.getText().toString().trim();
        if (bulan.length()<2){
            bulan= "0"+bulan;
        }
        if (tgl.length()<2){
            tgl= "0"+tgl;
        }
        String tgl_lahir = tahun+"-"+bulan+"-"+tgl;

        ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
        Call<ResponsModel> sendbio = null;
        if (status.equals("Guru")){
            sendbio = api.lupa_password_guru(nik,password,tgl_lahir);
        }else {
            sendbio = api.lupa_password_siswa(nik,password,tgl_lahir);
        }
        sendbio.enqueue(new Callback<ResponsModel>() {
            @Override
            public void onResponse(Call<ResponsModel> call, retrofit2.Response<ResponsModel> response) {

                String kode = response.body().getKode();
                Log.i("kode", "onResponse: "+kode);

                if (kode.equals("1")){

                    dialog_berhasil_kirim();
                }
                else if (kode.equals("0")){
                    if (status.equals("Guru")){
                        pw_salah("NIP");
                    }else {
                        pw_salah("NIS");
                    }

                }


            }

            @Override
            public void onFailure(Call<ResponsModel> call, Throwable t) {
                t.printStackTrace();
                if (t instanceof IOException) {
                    // pDialog.dismiss();
                    //Toast.makeText(ErrorHandlingActivity.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                    Toast.makeText(menu_lupa_password.this, "Jaringan Anda Bermasalah", Toast.LENGTH_SHORT).show();

                }
                else {
                    // pDialog.dismiss();
                    //  Toast.makeText(ErrorHandlingActivity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                    Toast.makeText(menu_lupa_password.this, "Jaringan Anda Bermasalah", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }



    public void pw_salah(String pesan){
        SweetAlertDialog pDialog = new SweetAlertDialog(menu_lupa_password.this, SweetAlertDialog.WARNING_TYPE);
        pDialog.setCancelable(false);
        pDialog.setTitleText(pesan+" Atau Tanggal Lahir Salah ");
        pDialog.setConfirmText("Ya");
        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();

            }
        });
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

    }
    void dialog_berhasil_kirim() {


        SweetAlertDialog pDialog = new SweetAlertDialog(menu_lupa_password.this, SweetAlertDialog.SUCCESS_TYPE);
        pDialog.setTitleText("Berhasil Ubah Password");
        pDialog.setContentText("Silahkan Login");
        pDialog.setConfirmText("Ok");
        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
                startActivity(new Intent(menu_lupa_password.this, menu_login.class));
                finish();

            }
        });
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

    }

    @Override
    public void onValidationSucceeded() {

        check_validasi_reset_pass();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }



    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            startActivity(new Intent(menu_lupa_password.this, menu_login.class));
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
