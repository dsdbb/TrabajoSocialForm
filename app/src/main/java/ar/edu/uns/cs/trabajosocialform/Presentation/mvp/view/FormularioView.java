package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.RxBus;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.NextButtonClickedObserver;
import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter.FormularioPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormularioView extends ActivityView implements FormActions {


    private AppCompatActivity activity;
    @BindView(R.id.toolbar)Toolbar toolbar;

    private List<View> planesSocialesRequeridos;

    public FormularioView(AppCompatActivity activity){
        super(activity);
        ButterKnife.bind(this,activity);
        planesSocialesRequeridos = new ArrayList<View>();
    }


    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        planesSocialesRequeridos = new ArrayList<View>();

        //I need to get the configuration file to show the corresponding fields
        config = presenter.getConfigurationFile();

        inicializarGui();
        ButterKnife.bind(this);

        //If the action is an update the fields must be filled with corresponding information
        update = getIntent().getBooleanExtra("UPDATE",false);
        if(update){
            updateForm =(Formulario)getIntent().getSerializableExtra("UPDATE_FORM");
            rellenarCampos();
        }

    }*/

    @Override
    @OnClick(R.id.siguiente_button)
    public void continuar(View view){
        RxBus.post(new NextButtonClickedObserver.NextButtonClicked());
    }


    public Formulario tomarDatos(){
        Utils utils = new Utils(getActivity());

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


    public void rellenarCampos(Formulario form){

    }

    public void inicializarGui(){
        /*Toolbar title*/
        toolbar.setTitle(R.string.titulo_formulario);

        /*Add the fields associated to the gerneral data of the form to the template*/
        Utils utils = new Utils(getActivity());
        utils.addContentToTemplate(R.layout.form_general);

        utils.setValuesTvEt(R.string.nombre_entrevistador,R.id.panel_nombre_entrevistador);
        utils.setValuesTvEt(R.string.apellido_entrevistador, R.id.panel_apellido_entrevistador);
        utils.setValuesTvEt(R.string.titulo_planes_sociales, R.id.panel_planes_sociales);
        utils.addAddingButtonListener(R.id.panel_planes_sociales,R.layout.panel_planes_sociales,planesSocialesRequeridos);
        utils.addRemovingButtonListener(R.id.panel_planes_sociales,planesSocialesRequeridos);
    }

    @Override
    public boolean validate(Object obj,Configuracion config){

        return true;
    }
}
