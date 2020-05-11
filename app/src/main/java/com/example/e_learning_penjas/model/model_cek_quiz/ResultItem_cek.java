package com.example.e_learning_penjas.model.model_cek_quiz;

import com.google.gson.annotations.SerializedName;

public class ResultItem_cek {

	@SerializedName("quiz")
	private String quiz;

	@SerializedName("bab")
	private String bab;

	@SerializedName("id_nilai")
	private String idNilai;

	@SerializedName("nama")
	private String nama;

	@SerializedName("nilai")
	private String nilai;

	@SerializedName("waktu")
	private String waktu;

	@SerializedName("nis")
	private String nis;

	@SerializedName("id_kelas")
	private String idKelas;

	@SerializedName("id_guru")
	private String idGuru;

	@SerializedName("status")
	private String status;

	public void setQuiz(String quiz){
		this.quiz = quiz;
	}

	public String getQuiz(){
		return quiz;
	}

	public void setBab(String bab){
		this.bab = bab;
	}

	public String getBab(){
		return bab;
	}

	public void setIdNilai(String idNilai){
		this.idNilai = idNilai;
	}

	public String getIdNilai(){
		return idNilai;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setNilai(String nilai){
		this.nilai = nilai;
	}

	public String getNilai(){
		return nilai;
	}

	public void setWaktu(String waktu){
		this.waktu = waktu;
	}

	public String getWaktu(){
		return waktu;
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

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResultItem{" + 
			"quiz = '" + quiz + '\'' + 
			",bab = '" + bab + '\'' + 
			",id_nilai = '" + idNilai + '\'' + 
			",nama = '" + nama + '\'' + 
			",nilai = '" + nilai + '\'' + 
			",waktu = '" + waktu + '\'' + 
			",nis = '" + nis + '\'' + 
			",id_kelas = '" + idKelas + '\'' + 
			",id_guru = '" + idGuru + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}