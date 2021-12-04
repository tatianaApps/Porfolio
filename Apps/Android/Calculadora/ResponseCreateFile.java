package com.example.coolerlogincalculator.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseCreateFile{

	@SerializedName("response")
	private List<Object> response;

	@SerializedName("value")
	private String value;
}