package com.example.coolerlogincalculator.response;

import com.google.gson.annotations.SerializedName;

public class GruposItem{

	@SerializedName("descripcion")
	public String descripcion;

	@SerializedName("imagen")
	public String imagen;

	@SerializedName("nombre")
	public String nombre;
}