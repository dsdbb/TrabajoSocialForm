package ar.edu.uns.cs.trabajosocialform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ar.edu.uns.cs.trabajosocialform.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.DataModel.InfraestructuraBarrial;
import ar.edu.uns.cs.trabajosocialform.Database.DatabaseAcces;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;
import ar.edu.uns.cs.trabajosocialform.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.configuracion.Configuracion;

public class FormInfraestructuraBarrialActivity extends AppCompatActivity {

    private Bundle bundle;
    private Formulario form;
    private boolean update;
    private Formulario updateForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_infraestructura_barrial);

        inicializarValores();

        Intent intent = getIntent();
        bundle = intent.getBundleExtra("CONFIG");
        form = (Formulario)intent.getSerializableExtra("FORM");
        Configuracion config = (Configuracion)bundle.getSerializable("CONFIG");

        /*Chequeo si es un update y en ese caso relleno los campos*/
        update = getIntent().getBooleanExtra("UPDATE",false);
        if(update){
            updateForm = (Formulario)getIntent().getSerializableExtra("UPDATE_FORM");
            rellenarCampos();
        }

        ViewAdapter va = new ViewAdapter(config,this);
        va.adaptarInfraestructura_barrial();
    }

    public void continuar(View view){
        InfraestructuraBarrial infraestructura = tomarDatos();
        form.setInfraestructuraBarrial(infraestructura);
        (new DatabaseAcces()).saveInDatabase(this,form);
        finish();

    }

    private InfraestructuraBarrial tomarDatos(){
        Utils utils = new Utils(this);
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

    private void inicializarValores(){
        Utils utils = new Utils(this);
        utils.setValuesTvSpinner(R.array.infraestructura_calles_opciones,R.string.titulo_infraestructura_calles,R.id.panel_infraestructura_calles);
        utils.setValuesTvSpinner(R.array.iluminacion_opciones,R.string.titulo_iluminacion,R.id.panel_iluminacion);
        utils.setValuesTvSpinner(R.array.inundacion_opciones,R.string.titulo_inundacion,R.id.panel_inundacion);
        utils.setValuesTvSpinner(R.array.recoleccion_opciones,R.string.titulo_recoleccion,R.id.panel_recoleccion);
        utils.setValuesTvSpinner(R.array.distancia_opciones,R.string.titulo_distancia_educacion,R.id.panel_distancia_educacion);
        utils.setValuesTvSpinner(R.array.distancia_opciones,R.string.titulo_distancia_salud,R.id.panel_distancia_salud);
        utils.setValuesTvSpinner(R.array.distancia_opciones,R.string.titulo_distancia_transporte,R.id.panel_distancia_transporte);
    }

    private void rellenarCampos(){
        Utils utils = new Utils(this);

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
}
