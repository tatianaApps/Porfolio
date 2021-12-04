package com.example.coolerlogincalculator.response;

import com.google.gson.annotations.SerializedName;

public class ResponseTrapItem{

	@SerializedName("name")
	public String name;

	@SerializedName("genre")
	public String genre;

	@SerializedName("pic")
	public String pic;
}