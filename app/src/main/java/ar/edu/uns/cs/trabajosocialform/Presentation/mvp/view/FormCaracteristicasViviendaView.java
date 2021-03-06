package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import java.text.Normalizer;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.CaracteristicasVivienda;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.RxBus;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.NextButtonClickedObserver;
import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;
import ar.edu.uns.cs.trabajosocialform.Presentation.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormCaracteristicasViviendaView extends ActivityView implements FormActions{

    @BindView(R.id.toolbar)Toolbar toolbar;

    public FormCaracteristicasViviendaView(AppCompatActivity activity){
        super(activity);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    }

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_caracteristicas_vivienda);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Intent intent = getIntent();
        form = (Formulario)intent.getSerializableExtra("FORM");
        config = (Configuracion)intent.getSerializableExtra("CONFIG");

        if(!config.getDatos_caracteristicas_vivienda().required()){
            continuar(null);
        }
        else{
            inicializarGui();
            Bind Activity to use ButterKnife facilities
            ButterKnife.bind(this);

           *Chequeo si es un update y en ese caso relleno los campos
            update = getIntent().getBooleanExtra("UPDATE",false);
            if(update){
                updateForm = (Formulario)getIntent().getSerializableExtra("UPDATE_FORM");
                rellenarCampos();
            }

            ViewAdapter va = new ViewAdapter(config,this);
            va.adaptarCaracteristicas_vivienda();
        }

    }*/

    @Override
    @OnClick(R.id.siguiente_button)
    public void continuar(View view) {
        RxBus.post(new NextButtonClickedObserver.NextButtonClicked());
    }
    /*    CaracteristicasVivienda caracteristicas = tomarDatos();
        form.setCaracteristicasVivienda(caracteristicas);
        Intent intent = new Intent(this,FormInfraestructuraBarrialView.class);
        intent.putExtra("CONFIG",config);
        intent.putExtra("FORM",form);
        intent.putExtra("UPDATE", update);
        if(update)
            intent.putExtra("UPDATE_FORM",updateForm);
        startActivity(intent);

        if(!config.getDatos_caracteristicas_vivienda().required()){
            finish();
        }
    }*/

    @Override
    public CaracteristicasVivienda tomarDatos(){
        Utils utils = new Utils(getActivity());

        String techo = utils.getDataTvSpinner(R.id.panel_techo);
        String revestimientoTechoS = utils.getDataTvSpinner(R.id.panel_revestimiento_techo);
        String paredes = utils.getDataTvSpinner(R.id.panel_paredes);
        String revestimientoParedesS = utils.getDataTvSpinner(R.id.panel_revestimiento_paredes);
        String pisos = utils.getDataTvSpinner(R.id.panel_pisos);
        String agua = utils.getDataTvSpinner(R.id.panel_agua);
        String fuenteAgua = utils.getDataTvSpinner(R.id.panel_fuente_agua);
        String baño = utils.getDataTvSpinner(R.id.panel_baño);
        String bañoTiene = utils.getDataTvSpinner(R.id.panel_baño_tiene);
        String desague = utils.getDataTvSpinner(R.id.panel_desague);
        String cocinaS = utils.getDataTvSpinner(R.id.panel_cocina);
        String electricidad = utils.getDataTvSpinner(R.id.panel_electricidad);
        String combustibleCocina = utils.getDataTvSpinner(R.id.panel_combustible_cocina);

        Boolean cocina = utils.getBoolean(cocinaS);
        Boolean revestimientoTecho = utils.getBoolean(revestimientoTechoS);
        Boolean revestimientoParedes = utils.getBoolean(revestimientoParedesS);

        return new CaracteristicasVivienda(techo, revestimientoTecho, paredes, revestimientoParedes, pisos, agua, fuenteAgua,
                baño, bañoTiene, desague, cocina, electricidad, combustibleCocina);
    }

    @Override
    public void inicializarGui(){
        Utils utils = new Utils(getActivity());

        utils.addContentToTemplate(R.layout.form_caracteristicas_vivienda);

        ButterKnife.bind(this,getActivity());

        /*Titulo toolbar*/
        toolbar.setTitle(R.string.titulo_caracteristicas_vivienda);

        utils.setValuesTvSpinner(R.array.techo_opciones,R.string.titulo_techo,R.id.panel_techo);
        utils.setValuesTvSpinner(R.array.revestimiento_techo_opciones,R.string.titulo_revestimiento_techo,R.id.panel_revestimiento_techo);
        utils.setValuesTvSpinner(R.array.paredes_opciones,R.string.titulo_paredes,R.id.panel_paredes);
        utils.setValuesTvSpinner(R.array.revestimiento_pared_opciones,R.string.titulo_revestimiento_pared,R.id.panel_revestimiento_paredes);
        utils.setValuesTvSpinner(R.array.pisos_opciones,R.string.titulo_pisos,R.id.panel_pisos);
        utils.setValuesTvSpinner(R.array.agua_opciones,R.string.titulo_agua,R.id.panel_agua);
        utils.setValuesTvSpinner(R.array.fuente_agua_opciones,R.string.titulo_fuente_agua,R.id.panel_fuente_agua);
        utils.setValuesTvSpinner(R.array.baño_opciones,R.string.titulo_baño,R.id.panel_baño);
        utils.setValuesTvSpinner(R.array.baño_tiene_opciones,R.string.titulo_baño_tiene,R.id.panel_baño_tiene);
        utils.setValuesTvSpinner(R.array.desague_opciones,R.string.titulo_desague,R.id.panel_desague);
        utils.setValuesTvSpinner(R.array.cocina_opciones,R.string.titulo_cocina,R.id.panel_cocina);
        utils.setValuesTvSpinner(R.array.electricidad_opciones,R.string.titulo_electricidad,R.id.panel_electricidad);
        utils.setValuesTvSpinner(R.array.combustible_cocina_opciones,R.string.titulo_combustible_cocina,R.id.panel_combustible_cocina);


        /*utils.addNextButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continuar();
            }
        });*/
    }

    @Override
    public void rellenarCampos(Formulario updateForm){
        Utils utils = new Utils(getActivity());

        CaracteristicasVivienda caracteristicas = updateForm.getCaracteristicasVivienda();
        utils.setValueToSpinner(R.id.panel_techo, R.array.techo_opciones, caracteristicas.getMaterial_techo());
        String revestTecho = utils.getStringFromBoolean(caracteristicas.isRevestimiento_techo());
        utils.setValueToSpinner(R.id.panel_revestimiento_techo, R.array.revestimiento_techo_opciones, revestTecho);
        utils.setValueToSpinner(R.id.panel_paredes, R.array.paredes_opciones, caracteristicas.getMaterial_paredes());
        String revestPared = utils.getStringFromBoolean(caracteristicas.isRevestimiento_paredes());
        utils.setValueToSpinner(R.id.panel_revestimiento_paredes, R.array.revestimiento_pared_opciones, revestPared);
        utils.setValueToSpinner(R.id.panel_pisos, R.array.pisos_opciones, caracteristicas.getMaterial_pisos());
        utils.setValueToSpinner(R.id.panel_agua, R.array.agua_opciones, caracteristicas.getAgua());
        utils.setValueToSpinner(R.id.panel_fuente_agua, R.array.fuente_agua_opciones, caracteristicas.getFuente_agua());
        utils.setValueToSpinner(R.id.panel_baño, R.array.baño_opciones, caracteristicas.getBanio());
        utils.setValueToSpinner(R.id.panel_baño_tiene, R.array.baño_tiene_opciones, caracteristicas.getBanio_tiene());
        utils.setValueToSpinner(R.id.panel_desague, R.array.desague_opciones, caracteristicas.getDesague());
        String cocina = utils.getStringFromBoolean(caracteristicas.isCocina());
        utils.setValueToSpinner(R.id.panel_cocina, R.array.cocina_opciones, cocina);
        utils.setValueToSpinner(R.id.panel_electricidad, R.array.electricidad_opciones, caracteristicas.getElectricidad());
        utils.setValueToSpinner(R.id.panel_combustible_cocina, R.array.combustible_cocina_opciones, caracteristicas.getCombustible_cocina());

    }

    @Override
    public boolean validate(Object obj, Configuracion config){
        return true;
    }


}
