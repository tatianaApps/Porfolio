package com.example.complete_app.response;

import com.google.gson.annotations.SerializedName;

public class ResponseLED{

	@SerializedName("response")
	public String response;

	@SerializedName("value")
	public String value;
}