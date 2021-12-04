package com.example.coolerlogincalculator.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseLogin{

	@SerializedName("response")
	public List<String> response;

	@SerializedName("value")
	public String value;

	public List<String> getResponse(){
		return response;
	}

	public String getValue(){
		return value;
	}
}