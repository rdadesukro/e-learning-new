package com.example.e_learning.server;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retroserver {
    private  static  final String base_url = "http://192.168.43.48/penjas/";
      public static  final String base_url_image_before = "http://192.168.43.48/penjas/images/before/";
    public static  final String base_url_image_after = "http://192.168.43.48/penjas/images/after/";
    public static  final String base_url_image_user = "http://192.168.43.48/penjas/images/user/";
    public static  final String url_register = "http://192.168.43.48/penjas/register.php";
    public static  final String url_kirim_laporan_user = "http://192.168.43.48/penjas/kirim_laporan.php";
    public static  final String url_login = "http://192.168.43.48/penjas/Login.php";

    private static Retrofit retrofit;
    public static Retrofit getClient()
    {
        if(retrofit == null)
        {
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .readTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(5, TimeUnit.SECONDS)
                    .build();
            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit;
    }


}
