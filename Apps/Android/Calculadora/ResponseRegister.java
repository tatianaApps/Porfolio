package com.example.coolerlogincalculator.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseRegister{

	@SerializedName("response")
	public List<Object> response;

	@SerializedName("value")
	public String value;
}