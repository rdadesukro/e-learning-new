package com.example.e_learning.server;

import com.example.e_learning.model.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

//package com.example.e_learning_penjas.server;
//
//import com.example.e_pelayanan.Model.BaseResponse;
//
//import retrofit2.Call;
//import retrofit2.http.Field;
//import retrofit2.http.FormUrlEncoded;
//import retrofit2.http.POST;
//
public interface Pelayanan_service {

    @FormUrlEncoded
    @POST("edit_foto.php")
    Call<BaseResponse> edit_foto(@Field("foto") String foto,
                                 @Field("nis") String nis);

    @FormUrlEncoded
    @POST("edit_foto_guru.php")
    Call<BaseResponse> edit_foto_guru(@Field("foto") String foto,
                                 @Field("nip") String nip);
}