package com.example.e_learning_penjas.model.model_profil;

import com.google.gson.annotations.SerializedName;

public class ResultItem_profil {

	@SerializedName("jk")
	private String jk;

	@SerializedName("wali_kelas")
	private String waliKelas;

	@SerializedName("no_hp")
	private String noHp;

	@SerializedName("nama_kelas")
	private String namaKelas;

	@SerializedName("id_guru")
	private String idGuru;

	@SerializedName("tgl_lahir")
	private String tglLahir;

	@SerializedName("id_siswa")
	private String idSiswa;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("nama")
	private String nama;

	@SerializedName("tempat_lahir")
	private String tempatLahir;

	@SerializedName("foto")
	private String foto;

	@SerializedName("nis")
	private String nis;

	@SerializedName("id_kelas")
	private String idKelas;

	public void setJk(String jk){
		this.jk = jk;
	}

	public String getJk(){
		return jk;
	}

	public void setWaliKelas(String waliKelas){
		this.waliKelas = waliKelas;
	}

	public String getWaliKelas(){
		return waliKelas;
	}

	public void setNoHp(String noHp){
		this.noHp = noHp;
	}

	public String getNoHp(){
		return noHp;
	}

	public void setNamaKelas(String namaKelas){
		this.namaKelas = namaKelas;
	}

	public String getNamaKelas(){
		return namaKelas;
	}

	public void setIdGuru(String idGuru){
		this.idGuru = idGuru;
	}

	public String getIdGuru(){
		return idGuru;
	}

	public void setTglLahir(String tglLahir){
		this.tglLahir = tglLahir;
	}

	public String getTglLahir(){
		return tglLahir;
	}

	public void setIdSiswa(String idSiswa){
		this.idSiswa = idSiswa;
	}

	public String getIdSiswa(){
		return idSiswa;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setTempatLahir(String tempatLahir){
		this.tempatLahir = tempatLahir;
	}

	public String getTempatLahir(){
		return tempatLahir;
	}

	public void setFoto(String foto){
		this.foto = foto;
	}

	public String getFoto(){
		return foto;
	}

	public void setNis(String nis){
		this.nis = nis;
	}

	public String getNis(){
		return nis;
	}

	public void setIdKelas(String idKelas){
		this.idKelas = idKelas;
	}

	public String getIdKelas(){
		return idKelas;
	}

	@Override
 	public String toString(){
		return 
			"ResultItem{" + 
			"jk = '" + jk + '\'' + 
			",wali_kelas = '" + waliKelas + '\'' + 
			",no_hp = '" + noHp + '\'' + 
			",nama_kelas = '" + namaKelas + '\'' + 
			",id_guru = '" + idGuru + '\'' + 
			",tgl_lahir = '" + tglLahir + '\'' + 
			",id_siswa = '" + idSiswa + '\'' + 
			",alamat = '" + alamat + '\'' + 
			",nama = '" + nama + '\'' + 
			",tempat_lahir = '" + tempatLahir + '\'' + 
			",foto = '" + foto + '\'' + 
			",nis = '" + nis + '\'' + 
			",id_kelas = '" + idKelas + '\'' + 
			"}";
		}
}