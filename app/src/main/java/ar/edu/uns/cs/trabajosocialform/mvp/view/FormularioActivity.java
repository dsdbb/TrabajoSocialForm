package ar.edu.uns.cs.trabajosocialform.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import ar.edu.uns.cs.trabajosocialform.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.mvp.presenter.initFormPresenter;
import butterknife.OnClick;

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

        inicializarGui();

        /*If the action is an update the fields must be filled with corresponding information*/
        update = getIntent().getBooleanExtra("UPDATE",false);
        if(update){
            updateForm =(Formulario)getIntent().getSerializableExtra("UPDATE_FORM");
            rellenarCampos();
        }

    }

    @Override
    @OnClick(R.id.siguiente_button)
    public void continuar(View view){
        form = tomarDatos();
        Intent intent = new Intent(this,FormSolicitanteActivity.class);
        putExtras(intent);
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
    protected void inicializarGui(){
        /*Toolbar title*/
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.titulo_formulario);
    }

    @Override
    protected boolean validate(Object obj){

        return true;
    }
}
