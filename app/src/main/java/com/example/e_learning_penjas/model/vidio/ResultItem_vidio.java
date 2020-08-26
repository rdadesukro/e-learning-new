package com.example.e_learning_penjas.model.vidio;

import com.google.gson.annotations.SerializedName;

public class ResultItem_vidio {

	@SerializedName("id_vidio")
	private String idVidio;

	@SerializedName("nama")
	private String nama;

	@SerializedName("foto")
	private String foto;

	@SerializedName("link")
	private String link;

	public String getIdVidio(){
		return idVidio;
	}

	public String getNama(){
		return nama;
	}

	public String getFoto(){
		return foto;
	}

	public String getLink(){
		return link;
	}
}