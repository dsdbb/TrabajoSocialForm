package ar.edu.uns.cs.trabajosocialform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import ar.edu.uns.cs.trabajosocialform.java_classes.DataModel.Solicitante;
import ar.edu.uns.cs.trabajosocialform.java_classes.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.java_classes.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.java_classes.configuracion.Datos_solicitante;

public class FormSolicitanteActivity extends AppCompatActivity {

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_solicitante);

        Intent intent = getIntent();
        bundle = intent.getBundleExtra("CONFIG");
        this.bundle = bundle;
        Configuracion config = (Configuracion)bundle.getSerializable("CONFIG");

        ViewAdapter va = new ViewAdapter(config,this);
        va.adaptarSolicitante();

    }


    public void continuar(View view){
        tomarDatos();
        Intent intent = new Intent(this,FormApoderadoActivity.class);
        intent.putExtra("CONFIG",bundle);
        startActivity(intent);
    }

    private void tomarDatos(){
        Configuracion config = (Configuracion)bundle.getSerializable("CONFIG");
        Datos_solicitante datos = config.getDatos_solicitante();

        EditText nombreEt = (EditText) findViewById(R.id.nombres_solicitante_form_et);
        EditText apellidoEt = (EditText) findViewById(R.id.apellidos_solicitante_form_et);
        EditText cuilEt = (EditText) findViewById(R.id.cuil_solicitante_form_et);
        EditText telefonoEt = (EditText) findViewById(R.id.telefonoPrincipal_solicitante_form_et);
        EditText otroTelefonoEt = (EditText) findViewById(R.id.otroTelefono_solicitante_form_et);

        String nombre = nombreEt.getText().toString();
        String apellido = apellidoEt.getText().toString();
        Integer cuil=null;

        if(!cuilEt.getText().toString().equals(""))
            cuil = Integer.parseInt(cuilEt.getText().toString());


        String telefono = telefonoEt.getText().toString();
        String otroTelefono = otroTelefonoEt.getText().toString();

        Solicitante solicitante = new Solicitante(nombre,apellido,cuil,telefono,otroTelefono);
    }




}
