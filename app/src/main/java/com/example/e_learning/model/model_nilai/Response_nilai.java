package com.example.e_learning.model.model_nilai;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class Response_nilai {

	@SerializedName("result")
	private List<ResultItem_nilai> result;

	@SerializedName("status")
	private boolean status;

	@SerializedName("desc")
	private String desc;

	public void setResult(List<ResultItem_nilai> result){
		this.result = result;
	}

	public List<ResultItem_nilai> getResult(){
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
			"Response_nilai{" +
			"result = '" + result + '\'' + 
			",status = '" + status + '\'' + 
			",desc = '" + desc + '\'' + 
			"}";
		}
}