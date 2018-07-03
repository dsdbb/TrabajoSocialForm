package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Familiar;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Ingreso;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.IngresoNoLaboral;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Ocupacion;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Salud;
import ar.edu.uns.cs.trabajosocialform.Data.Database.DatabaseAcces;
import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;

public class DetallesFamiliarView extends AppCompatActivity {

    private Familiar familiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_familiar);

        familiar = (Familiar)getIntent().getSerializableExtra("FAMILIAR");
        inicializarGui();
    }

    public void inicializarGui(){
        Utils utils = new Utils(this);

        /*Titulo toolbar*/
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.titulo_detalles_familiar);

        /*DATOS GENERALES*/
        utils.setDetailValues(R.id.detalle_nombre_familiar, R.string.nombre_familiar, familiar.getNombres());
        utils.setDetailValues(R.id.detalle_apellido_familiar, R.string.apellido_familiar, familiar.getApellidos());
        utils.setDetailValues(R.id.detalle_tipo_doc_familiar, R.string.tipo_doc_familiar, familiar.getTipoDocumento());
        utils.setDetailValues(R.id.detalle_cuil_familiar, R.string.cuil_familiar, familiar.getCuil()+"");
        utils.setDetailValues(R.id.detalle_sexo_familiar, R.string.sexo_familiar, familiar.getSexo());
        utils.setDetailValues(R.id.detalle_nacion_familiar, R.string.nacion_familiar, familiar.getNacion());
        utils.setDetailValues(R.id.detalle_estado_civil_familiar, R.string.estado_civil_familiar, familiar.getEstado_civil());
        utils.setDetailValues(R.id.detalle_vinculo_familiar, R.string.vinculo_familiar, familiar.getVinculo());
        String fechaNac = utils.getStringFromDate(familiar.getFecha_nacimiento());
        utils.setDetailValues(R.id.detalle_fecha_nacimiento_familiar, R.string.fecha_nac_familiar, fechaNac);
        utils.setDetailValues(R.id.detalle_nucleo_familiar, R.string.nucleo_familiar, familiar.getNucleo());
        utils.setDetailValues(R.id.detalle_nivel_educativo_familiar, R.string.nivel_educativo, familiar.getNivel_educativo());
        utils.setDetailValues(R.id.detalle_capacitacion_familiar, R.string.capacitacion, familiar.getCapacitacion()+" ("+familiar.getAsistenciaCapacitacion()+")");

        DatabaseAcces db = new DatabaseAcces();
        Ocupacion ocupacion = db.getOcupacion(this, familiar.getOcupacionId());
        Ingreso ingreso = db.getIngreso(this, familiar.getIngresoId());
        Salud salud = db.getSalud(this, familiar.getSaludId());

        /*OCUPACION*/
        utils.setDetailValues(R.id.detalle_condicion_actividad_familiar, R.string.condicion_actividad, ocupacion.getCondicion_actividad());
        utils.setDetailValues(R.id.detalle_puesto_trabajo_familiar, R.string.puesto_trabajo, ocupacion.getPuesto_trabajo());
        utils.setDetailValues(R.id.detalle_aporte_jubilatorio_familiar, R.string.aporte_jubilatorio, ocupacion.getAporte_jubilatorio());
        utils.setDetailValues(R.id.detalle_duracion_empleo_familiar, R.string.duracion, ocupacion.getDuracion());
        utils.setDetailValues(R.id.detalle_tipo_actividad_familiar, R.string.tipo_actividad, ocupacion.getTipo_actividad());
        utils.setDetailValues(R.id.detalle_calificacion_familiar, R.string.calificacion, ocupacion.getCalificacion());

        /*INGRESOS*/
        utils.setDetailValues(R.id.detalle_ingresos_laborales_familiar, R.string.ingresos_laborales, ingreso.getIngresos_laborales()+"");

        List<IngresoNoLaboral> noLaborales = ingreso.getIngresosNoLaborales();
        for(int i=0; i<noLaborales.size(); i++){
            LinearLayout contenedor = findViewById(R.id.detalles_ingresos_no_laborales);
            LayoutInflater inflater = LayoutInflater.from(this);

            View inflatedView = inflater.inflate(R.layout.detalles_item, contenedor, false);
            contenedor.addView(inflatedView);
            IngresoNoLaboral inl = noLaborales.get(i);
            utils.setDetailValues(inflatedView,R.string.ingresos_no_laborales,inl.getOrigen() + "("+inl.getMonto() +")");

        }

        List<String> programasSocialesSTI = ingreso.getProgramas_sociales_sti();
        for(int i=0; i<programasSocialesSTI.size();i++){
            LinearLayout contenedor = findViewById(R.id.detalles_programas_sociales_sti);
            LayoutInflater inflater = LayoutInflater.from(this);

            View inflatedView = inflater.inflate(R.layout.detalles_item, contenedor, false);
            contenedor.addView(inflatedView);
            String programaSocial = programasSocialesSTI.get(i);
            utils.setDetailValues(inflatedView, R.string.programas_sociales_sti, programaSocial);
        }

        /* SALUD */
        utils.setDetailValues(R.id.detalle_cobertura_salud_familiar, R.string.cobertura, salud.getCobertura());
        String fechaParto = utils.getStringFromDate(salud.getFecha_estimada_embarazo());
        utils.setDetailValues(R.id.detalle_fecha_parto_familiar, R.string.fecha_parto, fechaParto);


       List<String> discapacidades = salud.getDiscapacidades();
       for(int i=0; i<discapacidades.size(); i++){
          View view = utils.inflarDetalles(R.id.detalles_discapacidad);
          String discapacidad = discapacidades.get(i);
          utils.setDetailValues(view, R.string.discapacidad, discapacidad);
       }

       List<String> enfermedadesCronicas = salud.getEnfermedadesCronicas();
       for(int i=0; i<enfermedadesCronicas.size();i++){
           View view = utils.inflarDetalles(R.id.detalles_enfermedad_cronica);
           String enfermedad = enfermedadesCronicas.get(i);
           utils.setDetailValues(view, R.string.enfermedad_cronica, enfermedad);
       }

       utils.setDetailValues(R.id.detalle_independencia_funcional, R.string.independencia_funcional, salud.getIndependencia_funcional());

    }
}

