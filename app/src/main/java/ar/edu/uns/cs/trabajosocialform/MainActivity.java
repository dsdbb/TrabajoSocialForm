package ar.edu.uns.cs.trabajosocialform;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import ar.edu.uns.cs.trabajosocialform.Database.DatabaseAcces;
import ar.edu.uns.cs.trabajosocialform.ServerConnection.ServerSingleton;

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
        Intent intent = new Intent(this,FormularioActivity.class);
        startActivity(intent);
    }

    public void verEntradas(View view) {
        Intent intent = new Intent(this,EntradasActivity.class);
        startActivity(intent);

    }
}
