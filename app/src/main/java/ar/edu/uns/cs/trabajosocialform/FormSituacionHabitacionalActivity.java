package ar.edu.uns.cs.trabajosocialform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ar.edu.uns.cs.trabajosocialform.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.DataModel.SituacionHabitacional;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;
import ar.edu.uns.cs.trabajosocialform.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.configuracion.Configuracion;

public class FormSituacionHabitacionalActivity extends AppCompatActivity {

    private Bundle bundle;
    private Formulario form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_situacion_habitacional);

        inicializarValores();

        Intent intent = getIntent();
        bundle = intent.getBundleExtra("CONFIG");
        form = (Formulario)intent.getSerializableExtra("FORM");
        Configuracion config = (Configuracion)bundle.getSerializable("CONFIG");

        ViewAdapter va = new ViewAdapter(config,this);
        va.adaptarSituacion_habitacional();
    }

    public void continuar(View view){
        SituacionHabitacional situacion = tomarDatos();
        form.setSituacionHabitacional(situacion);
        Intent intent = new Intent(this,FormCaracteristicasViviendaActivity.class);
        intent.putExtra("CONFIG",bundle);
        intent.putExtra("FORM",form);
        startActivity(intent);
        finish();
    }

    private SituacionHabitacional tomarDatos(){
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

    private void inicializarValores(){
        Utils utils = new Utils(this);
        utils.setValuesTvSpinner(R.array.tipo_vivienda_opciones,R.string.titulo_tipo_vivienda,R.id.panel_tipo_vivienda);
        utils.setValuesTvSpinner(R.array.tenencia_vivienda_terreno_opciones,R.string.titulo_tenencia_vivienda_terreno,R.id.panel_tenencia_vivienda_terreno);
        utils.setValuesTvEt(R.string.titulo_tiempo_ocupacion,R.id.panel_tiempo_ocupacion);
        utils.setValuesTvEt(R.string.titulo_cantidad_hogares_vivienda,R.id.panel_cantidad_hogares_vivienda);
        utils.setValuesTvEt(R.string.titulo_cantidad_cuartos_UE,R.id.panel_cantidad_cuartos_ue);
    }
}
