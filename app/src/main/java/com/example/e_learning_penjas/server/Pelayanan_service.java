//package com.example.e_learning_penjas.server;
//
//import com.example.e_pelayanan.Model.BaseResponse;
//
//import retrofit2.Call;
//import retrofit2.http.Field;
//import retrofit2.http.FormUrlEncoded;
//import retrofit2.http.POST;
//
//public interface Pelayanan_service {
//    //data jambi
//    @FormUrlEncoded
//    @POST("simpan_data_user.php")
//    Call<BaseResponse> insert_data_anda(
//            @Field("nik") String nik,
//            @Field("password") String password,
//            @Field("no_hp") String no_hp,
//            @Field("token") String token);
//
//    //data bukan warga jambi
//    @FormUrlEncoded
//    @POST("simpan_data_bukan_warga.php")
//    Call<BaseResponse> insert_data_anda_bukan_warga(
//            @Field("nik") String nik,
//            @Field("password") String password,
//            @Field("nama") String nama,
//            @Field("tempat_lahir") String tempat_lahir,
//            @Field("tanggal_lahir") String tgl_lahir,
//            @Field("alamat") String alamat,
//            @Field("token") String token,
//            @Field("pekerjaan") String pekerjaan,
//            @Field("jk") String jk,
//            @Field("instansi_id") String instansi_id,
//            @Field("no_hp") String no_hp,
//            @Field("uuid") String uuid);
//
//
//    @FormUrlEncoded
//    @POST("tes_simpan_berkas.php")
//    Call<BaseResponse> insert_data_registrasi(
//            @Field("pemohon_id") String pemohon_id,
//            @Field("layanan_id") String layanan_id,
//            @Field("instansi_id") String instansi_id,
//            @Field("uuid") String uuid);
//
//
//
//    @FormUrlEncoded
//    @POST("simpan_berkas.php")
//    Call<BaseResponse> simpan_syarat(
//            @Field("registrasi_id") String registrasi_id,
//            @Field("syarat") String syarat,
//            @Field("syarat_value") String syarat_value,
//            @Field("status") String status);
//
//
//    @FormUrlEncoded
//    @POST("login.php")
//    Call<BaseResponse> loginRequest(@Field("nik") String nik,
//                                    @Field("password") String password);
//
//
//    @FormUrlEncoded
//    @POST("token_send.php")
//    Call<BaseResponse> Send_notif_staff(
//            @Field("id_masalah") String id_masalah,
//            @Field("judul") String judul
//    );
//
//}