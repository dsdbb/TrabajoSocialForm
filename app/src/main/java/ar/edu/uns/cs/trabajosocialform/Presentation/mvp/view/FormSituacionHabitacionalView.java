package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.google.gson.Gson;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.SituacionHabitacional;
import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;
import ar.edu.uns.cs.trabajosocialform.Presentation.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormSituacionHabitacionalView extends GeneralActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_situacion_habitacional);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        Intent intent = getIntent();
        form = (Formulario)intent.getSerializableExtra("FORM");
        config = (Configuracion)intent.getSerializableExtra("CONFIG");

        if(!config.getDatos_situacion_habitacional().required()){
            continuar(null);
        }
        else{
            inicializarGui();
            /*Bind Activity to use ButterKnife facilities*/
            ButterKnife.bind(this);

            /*Chequeo si es un update y en ese caso relleno los campos*/
            update = getIntent().getBooleanExtra("UPDATE",false);
            if(update){
                updateForm = (Formulario)getIntent().getSerializableExtra("UPDATE_FORM");
                Log.i("JSON uForm EN SITHAB: ", (new Gson()).toJson(updateForm));
                Log.i("JSON form EN SITHAB: ", (new Gson()).toJson(form));
                rellenarCampos();
            }

            ViewAdapter va = new ViewAdapter(config,this);
            va.adaptarSituacion_habitacional();
        }


    }

    @Override
    @OnClick(R.id.siguiente_button)
    public void continuar(View view){
        SituacionHabitacional situacion = tomarDatos();
        form.setSituacionHabitacional(situacion);
        Intent intent = new Intent(this,FormCaracteristicasViviendaActivity.class);
        intent.putExtra("CONFIG",config);
        intent.putExtra("FORM",form);
        intent.putExtra("UPDATE",update);
        if(update)
            intent.putExtra("UPDATE_FORM",updateForm);
        startActivity(intent);

        if(!config.getDatos_situacion_habitacional().required()){
              finish();
        }
    }

    @Override
    protected SituacionHabitacional tomarDatos(){
        Utils utils = new Utils(this);
        String tipoVivienda = utils.getDataTvSpinner(R.id.panel_tipo_vivienda);
        String tenencia = utils.getDataTvSpinner(R.id.panel_tenencia_vivienda_terreno);
        String tiempoOcupacionS = utils.getDataTvEt(R.id.panel_tiempo_ocupacion);
        String cantidadHogaresViviendaS = utils.getDataTvEt(R.id.panel_cantidad_hogares_vivienda);
        String cantidadCuartosUeS = utils.getDataTvEt(R.id.panel_cantidad_cuartos_ue);

        Integer tiempoOcupacion=null;
        if(!tiempoOcupacionS.equals(""))
            tiempoOcupacion = Integer.parseInt(tiempoOcupacionS);

        Integer cantidadHogaresVivienda = null;
        if(!cantidadHogaresViviendaS.equals(""))
            cantidadHogaresVivienda = Integer.parseInt(cantidadHogaresViviendaS);

        Integer cantidadCuartosUe = null;
        if(!cantidadCuartosUeS.equals(""))
            cantidadCuartosUe = Integer.parseInt(cantidadCuartosUeS);

        return new SituacionHabitacional(tipoVivienda,tenencia,tiempoOcupacion,cantidadHogaresVivienda,cantidadCuartosUe);
    }

    @Override
    protected void inicializarGui(){
        Utils utils = new Utils(this);

        utils.addContentToTemplate(R.layout.form_situacion_habitacional);

        /*Titulo toolbar*/
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.titulo_situacion_habitacional);

        utils.setValuesTvSpinner(R.array.tipo_vivienda_opciones,R.string.titulo_tipo_vivienda,R.id.panel_tipo_vivienda);
        utils.setValuesTvSpinner(R.array.tenencia_vivienda_terreno_opciones,R.string.titulo_tenencia_vivienda_terreno,R.id.panel_tenencia_vivienda_terreno);
        utils.setValuesTvEt(R.string.titulo_tiempo_ocupacion,R.id.panel_tiempo_ocupacion);
        utils.setValuesTvEt(R.string.titulo_cantidad_hogares_vivienda,R.id.panel_cantidad_hogares_vivienda);
        utils.setValuesTvEt(R.string.titulo_cantidad_cuartos_UE,R.id.panel_cantidad_cuartos_ue);

       /* utils.addNextButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continuar();
            }
        });*/
    }

    @Override
    protected void rellenarCampos(){
        Utils utils = new Utils(this);

        SituacionHabitacional situacionHabitacional = updateForm.getSituacionHabitacional();
        utils.setValueToSpinner(R.id.panel_tipo_vivienda, R.array.tipo_vivienda_opciones, situacionHabitacional.getTipo_vivienda());
        utils.setValueToSpinner(R.id.panel_tenencia_vivienda_terreno, R.array.tenencia_vivienda_terreno_opciones, situacionHabitacional.getTenencia_vivienda_terreno());
        utils.setValueToEditText(R.id.panel_tiempo_ocupacion, situacionHabitacional.getTiempo_ocupacion());
        utils.setValueToEditText(R.id.panel_cantidad_hogares_vivienda, situacionHabitacional.getCantidad_hogares_vivienda());
        utils.setValueToEditText(R.id.panel_cantidad_cuartos_ue, situacionHabitacional.getCantidad_cuartos_ue());

    }

    @Override
    protected boolean validate(Object obj){
        return true;
    }
}
