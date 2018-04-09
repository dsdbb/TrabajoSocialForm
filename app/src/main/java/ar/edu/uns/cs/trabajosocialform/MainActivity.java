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



        /* Configuracion config = new Configuracion();

        String json = (new Gson()).toJson(config);

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<json.length(); i++){
            sb.append(json.charAt(i));
            if((json.charAt(i))==',') {
                sb.append('\n');

            }

        }

        Log.i("JSON de CONFIGURACION: ",sb.toString());
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray namesArray = jsonObject.names();

        for(int i=0; i<namesArray.length();i++){
            try {
                Log.i("name: ",namesArray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }*/

    }

}
