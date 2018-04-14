package ar.edu.uns.cs.trabajosocialform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ar.edu.uns.cs.trabajosocialform.java_classes.configuracion.Configuracion;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void configurarNuevoFormulario(View view){
        Intent intent = new Intent(this,configurarNuevoFormulario_Activity.class);
        startActivity(intent);
    }

    public void seleccionarFormulario(View view){
        Intent intent = new Intent(this,FormSolicitanteActivity.class);
        startActivity(intent);
    }

}
