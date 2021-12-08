package com.example.apirestful;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import com.example.apirestful.Interfaces.ProductoApi;
import com.example.apirestful.Modelo.Modelo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView vistaJson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         vistaJson = findViewById(R.id.txtVistaJson);
         ObtenerDatos();
    }

    private void ObtenerDatos()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gorest.co.in/public/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.d("IMPRESION",retrofit.toString());
    ProductoApi productoApi = retrofit.create(ProductoApi.class);

    Call<List<Modelo>> call = productoApi.obtenerDatos();
    call.enqueue(new Callback<List<Modelo>>() {
        @Override
        public void onResponse(Call<List<Modelo>> call, Response<List<Modelo>> response) {
            if (!response.isSuccessful())
            {
                //txtVistaJson.setText();
                vistaJson.setText("Codigo "+ response.code());
                return;
            }

            List<Modelo> modeloList = response.body();
            for (Modelo lista:modeloList)
            {
                String contenido = "";
                contenido +="id:" + lista.getId() + "\n";
                contenido +="name:" + lista.getName() + "\n";
                contenido +="email:" + lista.getEmail() + "\n";
                contenido +="gender:" + lista.getGender()+ "\n";
                contenido +="status:" + lista.getStatus()+ "\n\n";
                vistaJson.append(contenido);
            }
        }

        @Override
        public void onFailure(Call<List<Modelo>> call, Throwable t) {
        vistaJson.setText("ALGO FALLÓ "+t.getMessage());
        }
    });

    }
}
