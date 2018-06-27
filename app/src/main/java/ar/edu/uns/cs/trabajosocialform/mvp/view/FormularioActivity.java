package ar.edu.uns.cs.trabajosocialform.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import ar.edu.uns.cs.trabajosocialform.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;
import ar.edu.uns.cs.trabajosocialform.mvp.presenter.initFormPresenter;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormularioActivity extends GeneralActivity {


    private initFormPresenter presenter;
    private List<View> planesSocialesRequeridos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        planesSocialesRequeridos = new ArrayList<View>();
        /*Set the presenter*/
        presenter = new initFormPresenter(this);

        /*I need to get the configuration file to show the corresponding fields*/
        config = presenter.getConfigurationFile();

        inicializarGui();
        ButterKnife.bind(this);

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
        Utils utils = new Utils(this);

        String nombre = utils.getDataTvEt(R.id.panel_nombre_entrevistador);
        String apellido = utils.getDataTvEt(R.id.panel_apellido_entrevistador);
        Formulario form = new Formulario();
        for (View view:planesSocialesRequeridos) {
            String plan = ((Spinner)view.findViewById(R.id.spinner_plan_social)).getSelectedItem().toString();
            form.addPlanSocialSolicitado(plan);
        }
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

        /*Add the fields associated to the gerneral data of the form to the template*/
        Utils utils = new Utils(this);
        utils.addContentToTemplate(R.layout.form_general);

        utils.setValuesTvEt(R.string.nombre_entrevistador,R.id.panel_nombre_entrevistador);
        utils.setValuesTvEt(R.string.apellido_entrevistador, R.id.panel_apellido_entrevistador);
        utils.setValuesTvEt(R.string.titulo_planes_sociales, R.id.panel_planes_sociales);
        utils.addAddingButtonListener(R.id.panel_planes_sociales,R.layout.panel_planes_sociales,planesSocialesRequeridos);
        utils.addRemovingButtonListener(R.id.panel_planes_sociales,planesSocialesRequeridos);
        //utils.setValuesTvSpinner(R.array.planes_sociales_opciones,R.string.titulo_planes_sociales,R.id.panel_planes_sociales);
    }

    @Override
    protected boolean validate(Object obj){

        return true;
    }
}
