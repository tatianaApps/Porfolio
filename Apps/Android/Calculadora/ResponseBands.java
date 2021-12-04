package com.example.coolerlogincalculator.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ResponseBands{

	@SerializedName("grupos")
	public List<GruposItem> grupos;
}