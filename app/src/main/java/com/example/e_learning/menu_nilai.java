package com.example.e_learning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.e_learning.adapter.adapter_nilai;
import com.example.e_learning.model.model_nilai.Response_nilai;
import com.example.e_learning.model.model_nilai.ResultItem_nilai;
import com.example.e_learning.server.ApiRequest;
import com.example.e_learning.server.Retroserver;

import java.util.ArrayList;
import java.util.List;

public class menu_nilai extends AppCompatActivity {
    private List<ResultItem_nilai> data = new ArrayList<>();
    adapter_nilai adapter;




    SearchView searchView;


    private RecyclerView mRecycler;
    private RecyclerView.LayoutManager mManager,manager;
    SharedPreferences sharedpreferences;
    public final static String TAG_nis = "nis_ambil";
    public final static String TAG_STATUS = "status";
    public final static String TAG_GURU = "id_guru";
    public final static String TAG_NAMA = "nama";
    public final static String TAG_KELAS = "kelas";
    public static final String my_shared_preferences = "my_shared_preferences";
    String id,nik,nama,instansi_id;
    String status="1";

    public static final String session_status = "session_status";
    SwipeRefreshLayout mSwipeRefreshLayout;
    Boolean session = false;
    @BindView(R.id.toolbar3)
    androidx.appcompat.widget.Toolbar Toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_nilai);
        ButterKnife.bind(this);

        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        nik = sharedpreferences.getString(TAG_nis, null);
        nama = sharedpreferences.getString(TAG_NAMA, null);
        mRecycler = (RecyclerView) findViewById(R.id.rv);
        Log.i("id_pemohon"+id, "onCreate: "+instansi_id);

       // Toast.makeText(this, "ID"+id, Toast.LENGTH_SHORT).show();
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swifeRefresh);

        setSupportActionBar(Toolbar);
//        setSupportActionBar(Toolbar);
//        if (getSupportActionBar()!=null)
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }

        status="1";




        init_get_laporan_baru_saya();


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                init_get_laporan_baru_saya();
            }
        });



    }

    public void get_laporan_selesai() {

        ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
        Call<Response_nilai> call = null;
        if (status.equals("1")){
           call = api.Get_data_NILAI("1",nik);
        }else if (status.equals("2")){
            call = api.Get_data_NILAI("2",nik);
        }else if (status.equals("3")){
            call = api.Get_data_NILAI("3",nik);
        }else if (status.equals("4")){
            call = api.Get_data_NILAI("4",nik);
        }else if (status.equals("5")){
            call = api.Get_data_NILAI("5",nik);
        }else if (status.equals("6")){
            call = api.Get_data_NILAI("6",nik);
        }else {
            call = api.Get_data_NILAI("7",nik);
        }

        call.enqueue(new Callback<Response_nilai>() {
            @Override
            public void onResponse(Call<Response_nilai> call, Response<Response_nilai> response) {

                try {

                    data = response.body().getResult();
                    adapter = new adapter_nilai(menu_nilai.this,data);

                    mRecycler.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    mSwipeRefreshLayout.setRefreshing(false);
                   // Toast.makeText(menu_laporan_masuk_pejabat.this, "cek data"+data.size(), Toast.LENGTH_SHORT).show();
                    Log.i("cek_data", "onResponse: "+data.size());


                    if (data.size()==0){

                        Toast.makeText(menu_nilai.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();



                    } else {

                    }
                } catch (Exception e) {
                    Log.e("onResponse", "There is an error"+e);

                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Response_nilai> call, Throwable t) {
                t.printStackTrace();


            }
        });
    }

    public  void  onResume() {

        super.onResume();

        // cek_berita();
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        nama = sharedpreferences.getString(TAG_NAMA, null);

        get_laporan_selesai();
    }


    public void init_get_laporan_baru_saya() {


        if (checkInternet()){

            mRecycler = (RecyclerView) findViewById(R.id.rv);
            mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            manager = new LinearLayoutManager(this);
            mRecycler.setLayoutManager(mManager);
            mRecycler.setHasFixedSize(true);
            get_laporan_selesai();

        }
        else {

            Toast.makeText(this, "Anda membutuhkan koneksi internet untuk mengambil gambar", Toast.LENGTH_SHORT).show();
        }







    }








    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            startActivity(new Intent(menu_nilai.this, menu_utama.class));
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    class chekinternet {
        public  String getConnectivityStatusString(Context context) {
            String status = null;
            ConnectivityManager cm = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null) {
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                    status = "Wifi enabled";
                    return status;
                } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                    status = "Mobile data enabled";
                    return status;
                }
            } else {
                status = "No internet is available";


                return status;
            }
            return status;
        }

    }
//
//
//
//
//    public void server(){
//
//        SweetAlertDialog pDialog = new SweetAlertDialog(menu_laporan_masuk_pejabat.this, SweetAlertDialog.WARNING_TYPE);
//        pDialog.setTitleText("Opps....!!!");
//        pDialog.setContentText("Server Lagi Down");
//        pDialog.setCancelable(false);
//        pDialog.setConfirmText("Ya");
//        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//            @Override
//            public void onClick(SweetAlertDialog sweetAlertDialog) {
//                sweetAlertDialog.dismissWithAnimation();
//
//            }
//        });
//        pDialog.setCanceledOnTouchOutside(false);
//        pDialog.show();
//
//
//    }
//    public void data(){
//
//        SweetAlertDialog pDialog = new SweetAlertDialog(menu_laporan_masuk_pejabat.this, SweetAlertDialog.WARNING_TYPE);
//        pDialog.setTitleText("Opps....!!!");
//        pDialog.setContentText("Data Anda Tidak ada");
//        pDialog.setCancelable(false);
//        pDialog.setConfirmText("Ya");
//        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//            @Override
//            public void onClick(SweetAlertDialog sweetAlertDialog) {
//                sweetAlertDialog.dismissWithAnimation();
//
//            }
//        });
//        pDialog.setCanceledOnTouchOutside(false);
//        pDialog.show();
//
//
//    }
//
    public boolean checkInternet(){
        boolean connectStatus = true;
        ConnectivityManager ConnectionManager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()==true ) {
            connectStatus = true;

        }
        else {
            connectStatus = false;
            // Display message in dialog box if you have internet connection
//            SweetAlertDialog pDialog = new SweetAlertDialog(menu_laporan_masuk_pejabat.this, SweetAlertDialog.WARNING_TYPE);
//            pDialog.setTitleText("Oppsss....!!1");
//            pDialog.setContentText("Anda Membutukan Koneksi Untuk Menampilkan Berkas!");
//            pDialog.setCancelable(false);
//            pDialog.setConfirmText("OK");
//            pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                @Override
//                public void onClick(SweetAlertDialog sweetAlertDialog) {
//                    sweetAlertDialog.dismissWithAnimation();
//                    init_get_laporan_baru_saya();
//
//                }
//            });
//            pDialog.setCanceledOnTouchOutside(false);
//            pDialog.show();
        }
        return connectStatus;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tes,menu);
//        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);
//        searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
//        searchView.setQueryHint("Cari Nilai Siswa...");
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//
//                searchView.clearFocus();
//                if (status.equals("1")){
//                  //  unit_cari();
//                }else {
//                    //unit_cari_dialihkan();
//                }
//
//
//
//                return false;
//
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                //  adapter.getFilter().filter(newText);
//                if (status.equals("masuk")){
//                 //   unit_cari();
//                }else {
//                   // unit_cari_dialihkan();
//                }
//
//                return false;
//            }
//        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.quiz1:

                status="1";
                getSupportActionBar().setTitle("NILAI QUIZ 1");
                init_get_laporan_baru_saya();

                return true;

            case R.id.quiz2:
                status="2";
                getSupportActionBar().setTitle("NILAI QUIZ 2");
                init_get_laporan_baru_saya();
                return true;

            case R.id.quiz3:
                status="3";
                getSupportActionBar().setTitle("NILAI QUIZ 3");
                init_get_laporan_baru_saya();
                return true;

            case R.id.quiz4:
                status="4";
                getSupportActionBar().setTitle("NILAI QUIZ 4");
                init_get_laporan_baru_saya();
                return true;

            case R.id.quiz5:
                status="5";
                getSupportActionBar().setTitle("NILAI QUIZ 5");
                init_get_laporan_baru_saya();
                return true;

            case R.id.quiz6:
                status="6";
                getSupportActionBar().setTitle("NILAI QUIZ 6");
                init_get_laporan_baru_saya();
                return true;

            case R.id.quiz7:
                status="7";
                getSupportActionBar().setTitle("NILAI QUIZ 7");
                init_get_laporan_baru_saya();
                return true;



            default:

                super.onOptionsItemSelected(item);

        }
        return true;

    }

}
