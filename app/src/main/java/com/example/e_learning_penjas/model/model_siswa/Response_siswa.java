package com.example.e_learning_penjas.model.model_siswa;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response_siswa {

	@SerializedName("result")
	private List<ResultItem_siswa> result;

	@SerializedName("status")
	private boolean status;

	@SerializedName("desc")
	private String desc;

	public void setResult(List<ResultItem_siswa> result){
		this.result = result;
	}

	public List<ResultItem_siswa> getResult(){
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
			"Response{" + 
			"result = '" + result + '\'' + 
			",status = '" + status + '\'' + 
			",desc = '" + desc + '\'' + 
			"}";
		}
}