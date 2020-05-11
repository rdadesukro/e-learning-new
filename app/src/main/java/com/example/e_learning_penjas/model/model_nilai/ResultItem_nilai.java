package com.example.e_learning_penjas.model.model_nilai;


import com.google.gson.annotations.SerializedName;


public class ResultItem_nilai {

	@SerializedName("quiz")
	private String quiz;

	@SerializedName("bab")
	private String bab;

	@SerializedName("nama")
	private String nama;

	@SerializedName("nama_kelas")
	private String namaKelas;

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public void setNilai(String nilai) {
		this.nilai = nilai;
	}

	@SerializedName("foto")
	private String foto;

	@SerializedName("nilai")
	private String nilai;

	@SerializedName("ambil_nis")
	private String ambilNis;

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

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setNamaKelas(String namaKelas){
		this.namaKelas = namaKelas;
	}

	public String getNamaKelas(){
		return namaKelas;
	}



	public String getFoto(){
		return foto;
	}



	public String getNilai(){
		return nilai;
	}

	public void setAmbilNis(String ambilNis){
		this.ambilNis = ambilNis;
	}

	public String getAmbilNis(){
		return ambilNis;
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
			"ResultItem_nilai{" +
			"quiz = '" + quiz + '\'' + 
			",bab = '" + bab + '\'' + 
			",nama = '" + nama + '\'' + 
			",nama_kelas = '" + namaKelas + '\'' + 
			",foto = '" + foto + '\'' + 
			",nilai = '" + nilai + '\'' + 
			",ambil_nis = '" + ambilNis + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}