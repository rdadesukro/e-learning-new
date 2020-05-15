package com.example.e_learning_penjas.server;


import com.example.e_learning_penjas.model.BaseResponse;
import com.example.e_learning_penjas.model.DataModel_register;
import com.example.e_learning_penjas.model.model_cek_quiz.Response_cek;
import com.example.e_learning_penjas.model.model_materi.Response_materi;
import com.example.e_learning_penjas.model.model_nilai.Response_nilai;
import com.example.e_learning_penjas.model.model_profil.Response_profil;
import com.example.e_learning_penjas.model.model_siswa.Response_siswa;


import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


public interface ApiRequest {


    @POST("Register_nis.php")
    Call<DataModel_register> get_register_nis(@Query("nis") String nik,
                                              @Query("password") String password); // view berdasarkan ID

    @POST("register_guru.php")
    Call<DataModel_register> register_guru(@Query("nip") String nip,
                                              @Query("password") String password); // view berdasarkan ID


    @GET("data_materi.php")
    Call<Response_materi> Get_data_materi(@Query("id_guru") String id_guru);

    @Multipart
     @POST("edit_materi.php")
    Call<BaseResponse> Edit_data_materi(
            @Part("id_materi") RequestBody id,
            @Part("nama") RequestBody nama,
            @Part("smester") RequestBody smester,
            @Part("bab") RequestBody bab,
            @Part MultipartBody.Part file);


    @FormUrlEncoded
    @POST("simpan_quiz.php")
    Call<BaseResponse> simpan_quiz(
            @Field("nis") String nis ,
            @Field("nama") String nama,
            @Field("id_guru") String id_guru,
            @Field("id_kelas") String id_kelas,
            @Field("quiz") String quiz,
            @Field("nilai") String nilai);

    @GET("cek_quiz.php")
    Call<Response_cek> cek_quiz(@Query("nis") String nis,
                                @Query("quiz") String quiz);


    @Multipart
    @POST("tambah_materi.php")
    Call<BaseResponse> tambah_materi(
            @Part("nama") RequestBody nama,
            @Part("smester") RequestBody smester,
            @Part("bab") RequestBody bab,
            @Part MultipartBody.Part file);

    @GET("data_nilai.php")
    Call<Response_nilai> Get_data_NILAI();

    @GET("data_siswa.php")
    Call<Response_siswa> Get_data_SISWA();

    @GET("profil_siswa.php")
    Call<Response_profil> profil_siswa(@Query("nis") String nis);


    @FormUrlEncoded
    @POST("update_baca.php")
    Call<BaseResponse> Up_baca(@Field("id_materi") String id_materi);
}


