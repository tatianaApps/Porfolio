package com.example.complete_app.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseRegister{

	@SerializedName("response")
	public List<String> response;

	@SerializedName("value")
	public String value;
}