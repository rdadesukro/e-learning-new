package com.example.e_learning_penjas.server;


import com.example.e_learning_penjas.model.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyInterface {

    String JSONURL = "http://192.168.1.71/e_pelayanan/";

    @GET("upload_syarat.php")
    Call<String> Get_data_berkas1(@Query("layanan_id") String layanan_id);





    @FormUrlEncoded
    @POST("hapus.php")
    Call<BaseResponse> hapus(
            @Field("id_materi") String id_materi);
}
