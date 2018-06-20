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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import ar.edu.uns.cs.trabajosocialform.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.presenter.initFormPresenter;

public class FormularioActivity extends GeneralActivity {


    private initFormPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        /*Set the presenter*/
        presenter = new initFormPresenter(this);

        /*I need to get the configuration file to show the corresponding fields*/
        config = presenter.getConfigurationFile();

        /*If the action is an update the fields must be filled with corresponding information*/
        update = getIntent().getBooleanExtra("UPDATE",false);
        if(update){
            updateForm =(Formulario)getIntent().getSerializableExtra("UPDATE_FORM");
            rellenarCampos();
        }

        findViewById(R.id.siguiente_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continuar();
            }
        });
    }


    @Override
    public void continuar(){
        form = tomarDatos();
        Intent intent = new Intent(this,FormSolicitanteActivity.class);

        putExtras(intent);
       /* intent.putExtra("CONFIG",config);
        intent.putExtra("FORM",form);
        intent.putExtra("UPDATE",update);
        if(update){
            intent.putExtra("UPDATE_FORM", updateForm);
        }*/

        startActivity(intent);

    }

    @Override
    protected Formulario tomarDatos(){
        String nombre = ((EditText)findViewById(R.id.nombre_entrevistador_et)).getText().toString();
        String apellido = ((EditText)findViewById(R.id.apellido_entrevistador_et)).getText().toString();

        Formulario form = new Formulario();
        form.setNombreEntrevistador(nombre);
        form.setApellidoEntrevistador(apellido);

        return form;
    }



    @Override
    protected void rellenarCampos(){

    }

    @Override
    protected void inicializarGui(){}

    @Override
    protected boolean validate(Object obj){

        return true;
    }
}
