package com.example.apirestful.Interfaces;

import com.example.apirestful.Modelo.Modelo;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;


public interface ProductoApi {
    @Headers("Content-Type: text/html")
    @GET("posts")
    Call<List<Modelo>> obtenerDatos();

}
