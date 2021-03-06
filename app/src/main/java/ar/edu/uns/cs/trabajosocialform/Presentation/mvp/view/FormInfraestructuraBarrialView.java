package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.InfraestructuraBarrial;
import ar.edu.uns.cs.trabajosocialform.Data.Database.DatabaseAcces;
import ar.edu.uns.cs.trabajosocialform.Presentation.activities.MainActivity;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.RxBus;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.NextButtonClickedObserver;
import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;
import ar.edu.uns.cs.trabajosocialform.Presentation.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormInfraestructuraBarrialView extends ActivityView implements FormActions{

    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.siguiente_button) Button nextButton;

    public FormInfraestructuraBarrialView(AppCompatActivity activity){
        super(activity);
    }



  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_infraestructura_barrial);

        Intent intent = getIntent();
        form = (Formulario)intent.getSerializableExtra("FORM");
        config = (Configuracion)intent.getSerializableExtra("CONFIG");

        if(!config.getDatos_infraestructura_barrial().required()){
            continuar(null);
        }
        else{
            inicializarGui();
            Bind Activity to use ButterKnife facilities
            ButterKnife.bind(this);

            Chequeo si es un update y en ese caso relleno los campos
            update = getIntent().getBooleanExtra("UPDATE",false);
            if(update){
                updateForm = (Formulario)getIntent().getSerializableExtra("UPDATE_FORM");
                rellenarCampos();
            }

            ViewAdapter va = new ViewAdapter(config,this);
            va.adaptarInfraestructura_barrial();
        }

    }*/

    @Override
    @OnClick(R.id.siguiente_button)
    public void continuar(View view) {
        RxBus.post(new NextButtonClickedObserver.NextButtonClicked());
    }
     /*   InfraestructuraBarrial infraestructura = tomarDatos();
        form.setInfraestructuraBarrial(infraestructura);
        DatabaseAcces db = new DatabaseAcces();
        if(update){
            db.updateDatabase(this,form,updateForm);
        }
        else{
            db.saveInDatabase(this,form);
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

        if(config.getDatos_infraestructura_barrial().required()){
            finish();
        }

    }*/

    @Override
    public InfraestructuraBarrial tomarDatos(){
        Utils utils = new Utils(getActivity());
        String calles = utils.getDataTvSpinner(R.id.panel_infraestructura_calles);
        String iluminacion = utils.getDataTvSpinner(R.id.panel_iluminacion);
        String inundacionS = utils.getDataTvSpinner(R.id.panel_inundacion);
        String recoleccionS = utils.getDataTvSpinner(R.id.panel_recoleccion);
        String distanciaEducacion = utils.getDataTvSpinner(R.id.panel_distancia_educacion);
        String distanciaSalud = utils.getDataTvSpinner(R.id.panel_distancia_salud);
        String distanciaTransporte = utils.getDataTvSpinner(R.id.panel_distancia_transporte);

        Boolean inundacion = utils.getBoolean(inundacionS);
        Boolean recoleccion = utils.getBoolean(recoleccionS);

        return new InfraestructuraBarrial(calles,iluminacion,inundacion,recoleccion,distanciaEducacion,distanciaSalud,distanciaTransporte);
    }

    @Override
    public void inicializarGui(){
        Utils utils = new Utils(getActivity());

        utils.addContentToTemplate(R.layout.form_infraestructura_barrial);

        ButterKnife.bind(this,getActivity());

        /*Titulo toolbar*/
        toolbar.setTitle(R.string.titulo_infraestructura_barrial);

        nextButton.setText(R.string.boton_guardar);

        utils.setValuesTvSpinner(R.array.infraestructura_calles_opciones,R.string.titulo_infraestructura_calles,R.id.panel_infraestructura_calles);
        utils.setValuesTvSpinner(R.array.iluminacion_opciones,R.string.titulo_iluminacion,R.id.panel_iluminacion);
        utils.setValuesTvSpinner(R.array.inundacion_opciones,R.string.titulo_inundacion,R.id.panel_inundacion);
        utils.setValuesTvSpinner(R.array.recoleccion_opciones,R.string.titulo_recoleccion,R.id.panel_recoleccion);
        utils.setValuesTvSpinner(R.array.distancia_opciones,R.string.titulo_distancia_educacion,R.id.panel_distancia_educacion);
        utils.setValuesTvSpinner(R.array.distancia_opciones,R.string.titulo_distancia_salud,R.id.panel_distancia_salud);
        utils.setValuesTvSpinner(R.array.distancia_opciones,R.string.titulo_distancia_transporte,R.id.panel_distancia_transporte);

        /*Boton*/
        /*((Button)findViewById(R.id.siguiente_button)).setText("Guardar");
        utils.addNextButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continuar();
            }
        });*/
    }

    @Override
    public void rellenarCampos(Formulario updateForm){
        Utils utils = new Utils(getActivity());

        InfraestructuraBarrial infraestructura = updateForm.getInfraestructuraBarrial();
        utils.setValueToSpinner(R.id.panel_infraestructura_calles, R.array.infraestructura_calles_opciones, infraestructura.getInfraestructura_calles());
        utils.setValueToSpinner(R.id.panel_iluminacion, R.array.iluminacion_opciones, infraestructura.getIluminacion());
        String inundacion = utils.getStringFromBoolean(infraestructura.isInundacion());
        utils.setValueToSpinner(R.id.panel_inundacion, R.array.inundacion_opciones, inundacion);
        String recoleccion = utils.getStringFromBoolean(infraestructura.isRecoleccion_residuos());
        utils.setValueToSpinner(R.id.panel_recoleccion, R.array.recoleccion_opciones, recoleccion);
        utils.setValueToSpinner(R.id.panel_distancia_educacion, R.array.distancia_opciones, infraestructura.getDistancia_educacion());
        utils.setValueToSpinner(R.id.panel_distancia_salud, R.array.distancia_opciones, infraestructura.getDistancia_salud());
        utils.setValueToSpinner(R.id.panel_distancia_transporte, R.array.distancia_opciones, infraestructura.getDistancia_transporte());
    }

    @Override
    public boolean validate(Object obj, Configuracion configuracion){
        return true;
    }

}
