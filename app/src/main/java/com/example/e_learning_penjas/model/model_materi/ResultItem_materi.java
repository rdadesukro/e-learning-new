package com.example.e_learning_penjas.model.model_materi;


import com.google.gson.annotations.SerializedName;


public class ResultItem_materi {

	@SerializedName("bab")
	private String bab;


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@SerializedName("status")
	private String status;


	@SerializedName("id_materi")
	private String idMateri;

	@SerializedName("nama")
	private String nama;

	@SerializedName("smester")
	private String smester;

	@SerializedName("id_guru")
	private String idGuru;

	@SerializedName("url")
	private String url;

	public void setBab(String bab){
		this.bab = bab;
	}

	public String getBab(){
		return bab;
	}

	public void setIdMateri(String idMateri){
		this.idMateri = idMateri;
	}

	public String getIdMateri(){
		return idMateri;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setSmester(String smester){
		this.smester = smester;
	}

	public String getSmester(){
		return smester;
	}

	public void setIdGuru(String idGuru){
		this.idGuru = idGuru;
	}

	public String getIdGuru(){
		return idGuru;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"ResultItem_materi{" +
			"bab = '" + bab + '\'' + 
			",id_materi = '" + idMateri + '\'' +
					",status = '" + status + '\'' +
					",nama = '" + nama + '\'' +
			",smester = '" + smester + '\'' + 
			",id_guru = '" + idGuru + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}