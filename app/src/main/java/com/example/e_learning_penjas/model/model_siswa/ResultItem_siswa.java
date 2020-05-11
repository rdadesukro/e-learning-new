package com.example.e_learning_penjas.model.model_siswa;

import com.google.gson.annotations.SerializedName;

public class ResultItem_siswa {

	@SerializedName("jk")
	private String jk;

	@SerializedName("nama")
	private String nama;

	public String getNama_kelas() {
		return nama_kelas;
	}

	public void setNama_kelas(String nama_kelas) {
		this.nama_kelas = nama_kelas;
	}

	@SerializedName("nama_kelas")
	private String nama_kelas;

	@SerializedName("tempat_lahir")
	private String tempatLahir;

	@SerializedName("no_hp")
	private String noHp;

	@SerializedName("foto")
	private String foto;

	@SerializedName("nis")
	private String nis;

	@SerializedName("id_kelas")
	private String idKelas;

	@SerializedName("id_guru")
	private String idGuru;

	@SerializedName("tgl_lahir")
	private String tglLahir;

	@SerializedName("id_siswa")
	private String idSiswa;

	@SerializedName("alamat")
	private String alamat;

	public void setJk(String jk){
		this.jk = jk;
	}

	public String getJk(){
		return jk;
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

	public void setNoHp(String noHp){
		this.noHp = noHp;
	}

	public String getNoHp(){
		return noHp;
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

	@Override
 	public String toString(){
		return 
			"ResultItem{" + 
			"jk = '" + jk + '\'' + 
			",nama = '" + nama + '\'' + 
			",tempat_lahir = '" + tempatLahir + '\'' + 
			",no_hp = '" + noHp + '\'' + 
			",foto = '" + foto + '\'' + 
			",nis = '" + nis + '\'' +
					",nama_kelas = '" + nama_kelas + '\'' +
					",id_kelas = '" + idKelas + '\'' +
			",id_guru = '" + idGuru + '\'' + 
			",tgl_lahir = '" + tglLahir + '\'' + 
			",id_siswa = '" + idSiswa + '\'' + 
			",alamat = '" + alamat + '\'' + 
			"}";
		}
}