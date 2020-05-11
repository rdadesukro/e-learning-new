package com.example.e_learning_penjas;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.e_learning_penjas.app.AppConfig;
import com.example.e_learning_penjas.app.AppController;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.e_learning_penjas.app.AppConfig.URL_LOGIN_USER;

public class menu_login extends AppCompatActivity  implements Validator.ValidationListener {

    ProgressDialog pDialog;
    Button btn_login;
    TextView btn_lupa;
    Intent intent;

   
    @NotEmpty
    @BindView(R.id.edit_nis)
    EditText edit_nis;


    @NotEmpty
    @BindView(R.id.txt_sts)
    TextView txt_sts;
    @BindView(R.id.spiner_login)
    Spinner spiner_login;



    @NotEmpty
    @BindView(R.id.edit_pass)
    EditText edit_pass;


    int success;
    ConnectivityManager conMgr;
    private static final String TAG = menu_login.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public final static String TAG_nis = "nis_ambil";
    public final static String TAG_STATUS = "status";
    public final static String TAG_GURU = "id_guru";
    public final static String TAG_ID_KELAS = "id_kelas";
    public final static String TAG_NAMA = "nama";
    public final static String TAG_KELAS = "kelas";
    
    String status;
    String url ;

    Boolean session = false;
    String id,nama,nis,kelas,id_guru,id_kelas;
    String tag_json_obj = "json_obj_req";
    Validator validator;
    public static final String session_status = "session_status";
    SharedPreferences sharedpreferences;
    public static final String my_shared_preferences = "my_shared_preferences";
    //initially it is false
    private boolean loggedIn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_login);//  sesi();
        ButterKnife.bind(this);
        validator = new Validator(this);
        validator.setValidationListener(this);


        conMgr =(ConnectivityManager) getSystemService( Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_lupa = (TextView) findViewById(R.id.txt_lupa);
      edit_nis.requestFocus();

        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        id = sharedpreferences.getString(TAG_STATUS, null);
        nis = sharedpreferences.getString(TAG_nis, null);
        id_guru = sharedpreferences.getString(TAG_GURU, null);
        nama = sharedpreferences.getString(TAG_NAMA, null);
        kelas = sharedpreferences.getString(TAG_KELAS, null);

        if (session) {
            Intent intent = new Intent(menu_login.this, menu_utama.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.putExtra(TAG_STATUS, id);
            intent.putExtra(TAG_nis, nis);
            intent.putExtra(TAG_NAMA, nama);
            intent.putExtra(TAG_GURU, id_guru);
            intent.putExtra(TAG_ID_KELAS, id_kelas);
             intent.putExtra(TAG_KELAS, kelas);
            finish();
            startActivity(intent);
        }

        btn_login.setOnClickListener(new View.OnClickListener()
        {

            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                validator.validate();

            }
        });

//        btn_register.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                intent = new Intent(menu_login.this, menu_register.class);
//                finish();
//                startActivity(intent);
//            }
//        });
        btn_lupa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                intent = new Intent(menu_login.this, menu_lupa_password.class);
                finish();
                startActivity(intent);
            }
        });
        // db = new SQLiteHandler(getApplicationContext());

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
                    txt_sts.setText(selectedItemText);

                    if (txt_sts.getText().toString().equals("Guru")){
                        edit_nis.setHint("NIP");
                    }else if (txt_sts.getText().toString().equals("Siswa")){
                        edit_nis.setHint("NIS");
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
    @OnClick(R.id.btn_lihat)
    public void lihat() {
        if (txt_sts.getText().equals("true")){
            edit_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            txt_sts.setText("false");
            Toast.makeText(this, "status"+status, Toast.LENGTH_SHORT).show();

        }else if (txt_sts.getText().equals("false")){
            edit_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
            txt_sts.setText("true");
        }

    }
    @OnClick(R.id.txt_register)
    public void txt_register() {

        intent = new Intent(menu_login.this, menu_register.class);
        finish();
        startActivity(intent);
    }
    private void checkLogin(final String nis, final String password) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Logging in ...");
        showDialog();
        if (spiner_login.getSelectedItem().equals("Siswa")){
             url = "http://192.168.1.71/penjas/login.php";
        }else {
             url = "http://192.168.1.71/penjas/login_guru.php";
        }


        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Login Response_kecamatan: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);
                    Log.i("sukses", "onResponse: "+success);

                    // Check for error node in json
                    if (success == 1) {
//                        intent = new Intent(menu_login.this, menu_utama.class);
//                        finish();
//                        startActivity(intent);
                        String nis = jObj.getString(TAG_nis);
                        String id = jObj.getString(TAG_STATUS);
                        String nama = jObj.getString(TAG_NAMA);
                        String kelas = jObj.getString(TAG_KELAS);
                        String ID_kelas = jObj.getString(TAG_ID_KELAS);
                        String id_guru = jObj.getString(TAG_GURU);

                        Log.e("Successfully Login!", jObj.toString());

                        Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        // menyimpan login ke session_dtr
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putBoolean(session_status, true);
                        editor.putString(TAG_STATUS, id);
                        editor.putString(TAG_nis, nis);
                        editor.putString(TAG_NAMA, nama);
                        editor.putString(TAG_ID_KELAS, ID_kelas);
                        editor.putString(TAG_KELAS, kelas);
                        editor.putString(TAG_GURU,id_guru);
                        editor.commit();

                        // Memanggil main activity
                        Intent intent = new Intent(menu_login.this, menu_utama.class);
                        intent.putExtra(TAG_STATUS, id);
                        intent.putExtra(TAG_nis, nis);
                        intent.putExtra(TAG_ID_KELAS, ID_kelas);
                        intent.putExtra(TAG_NAMA, nama);
                        intent.putExtra(TAG_GURU, id_guru);
                            intent.putExtra(TAG_KELAS, kelas);
                        finish();
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("nis", nis);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//
//            startActivity(new Intent(menu_login.this, menu_login_utama.class));
//            finish();
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//    @OnClick(R.id.back)
//    void back() {
//        startActivity(new Intent(menu_login.this, menu_login_utama.class));
//        finish();
//
//    }
    @Override
    public void onValidationSucceeded() {

        String nis = edit_nis.getText().toString();
        String password = edit_pass.getText().toString();


//        if (spiner_login.gett)

        // mengecek kolom yang kosong
        if (nis.trim().length() > 0 && password.trim().length() > 0) {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
                checkLogin(nis, password);
            } else {
                Toast.makeText(getApplicationContext() ,"No Internet Connection", Toast.LENGTH_LONG).show();
            }
        } else {
            // Prompt user to enter credentials
            Toast.makeText(getApplicationContext() ,"Kolom tidak boleh kosong", Toast.LENGTH_LONG).show();
        }
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
}
