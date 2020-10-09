package com.example.e_learning.model.model_materi;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class Response_materi {

	@SerializedName("result")
	private List<ResultItem_materi> result;

	@SerializedName("status")
	private boolean status;

	@SerializedName("desc")
	private String desc;

	public void setResult(List<ResultItem_materi> result){
		this.result = result;
	}

	public List<ResultItem_materi> getResult(){
		return result;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	public void setDesc(String desc){
		this.desc = desc;
	}

	public String getDesc(){
		return desc;
	}

	@Override
 	public String toString(){
		return 
			"Response_materi{" +
			"result = '" + result + '\'' + 
			",status = '" + status + '\'' + 
			",desc = '" + desc + '\'' + 
			"}";
		}
}