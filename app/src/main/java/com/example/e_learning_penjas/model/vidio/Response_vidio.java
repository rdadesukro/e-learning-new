package com.example.e_learning_penjas.model.vidio;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response_vidio {

	@SerializedName("result")
	private List<ResultItem_vidio> result;

	@SerializedName("kode")
	private int kode;

	@SerializedName("search_count")
	private int searchCount;

	public List<ResultItem_vidio> getResult(){
		return result;
	}

	public int getKode(){
		return kode;
	}

	public int getSearchCount(){
		return searchCount;
	}
}