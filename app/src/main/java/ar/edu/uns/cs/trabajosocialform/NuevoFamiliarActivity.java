package ar.edu.uns.cs.trabajosocialform;

import android.app.Activity;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.edu.uns.cs.trabajosocialform.DataModel.Familiar;
import ar.edu.uns.cs.trabajosocialform.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.DataModel.Ingreso;
import ar.edu.uns.cs.trabajosocialform.DataModel.IngresoNoLaboral;
import ar.edu.uns.cs.trabajosocialform.DataModel.Ocupacion;
import ar.edu.uns.cs.trabajosocialform.DataModel.Salud;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;

public class NuevoFamiliarActivity extends AppCompatActivity {

    private List<View> ingresosNoLaborales;
    private List<View> programasSocialesSti;
    private List<View> discapacidades;
    private List<View> enfermedadesCronicas;
    private Formulario form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_familiar);
        ingresosNoLaborales = new ArrayList<View>();
        programasSocialesSti = new ArrayList<View>();
        discapacidades = new ArrayList<View>();
        enfermedadesCronicas = new ArrayList<View>();
        form = (Formulario)getIntent().getSerializableExtra("FORM");

        inicializarGui();
    }

    public void guardar(View view){
        Familiar familiar = tomarDatos();
        form.getFamiliares().add(familiar);
        //getIntent().putExtra("FORM",form);
        Intent resultIntent = new Intent();
        resultIntent.putExtra("RETURN_FORM",form);
        setResult(Activity.RESULT_OK,resultIntent);
        finish();
    }

    private Familiar tomarDatos(){
        Utils utils = new Utils(this);

        /*Tomo los datos generales del familiar*/
        String nombre = utils.getDataTvEt(R.id.panel_nombres_familiar);
        String apellido = utils.getDataTvEt(R.id.panel_apellidos_familiar);
        String tipoDoc = utils.getDataTvSpinner(R.id.panel_tipo_doc_familiar);
        String cuilS = utils.getDataTvEt(R.id.panel_cuil_familiar);
        String sexo = utils.getDataTvSpinner(R.id.panel_sexo_familiar);
        String nacion = utils.getDataTvSpinner(R.id.panel_nacion_familiar);
        String fechaNacS = utils.getDataTvEt(R.id.panel_fecha_nac_familiar);
        String estadoCivil = utils.getDataTvSpinner(R.id.panel_estado_civil_familiar);
        String vinculo = utils.getDataTvSpinner(R.id.panel_vinculo_familiar);
        String nucleo = utils.getDataTvSpinner(R.id.panel_nucleo_familiar);
        String nivelEducativo = utils.getDataTvSpinner(R.id.panel_nivel_educativo);
        List<String> stringsCapacitacion = utils.getDataTvSpinner2(R.id.panel_capacitacion);
        String capacitacion = stringsCapacitacion.get(0);
        String asistenciaCapacitacion = stringsCapacitacion.get(1);

        /*Convierto datos*/
        Long cuil = utils.getLongFromString(cuilS);
        Date fechaNac = utils.getDateFromString(fechaNacS);

        /*Tomo los datos de la ocupacion*/
        String condicionActividad = utils.getDataTvSpinner(R.id.panel_condicion_actividad);
        String puestoTrabajo = utils.getDataTvSpinner(R.id.panel_puesto_trabajo);
        String aporteJubilatorio = utils.getDataTvSpinner(R.id.panel_aporte_jubilatorio);
        String duracion = utils.getDataTvSpinner(R.id.panel_duracion_empleo);
        String tipoActividad = utils.getDataTvSpinner(R.id.panel_tipo_actividad);
        String calificacion = utils.getDataTvSpinner(R.id.panel_calificacion);

        /*Creo el objeto correspondiente*/
        Ocupacion ocupacion = new Ocupacion(condicionActividad, puestoTrabajo, aporteJubilatorio, duracion,
                tipoActividad, calificacion);


        /*Tomo los datos de ingreso y creo el objeto correspondiente*/
        Ingreso ingreso = new Ingreso();
        String ingresosLaboralesS = utils.getDataTvEt(R.id.panel_ingresos_laborales);
        Integer ingresosLaborales = utils.getIntegerFromString(ingresosLaboralesS);
        ingreso.setIngresos_laborales(ingresosLaborales);

        for (View vista: ingresosNoLaborales) {
            String origen = ((Spinner)vista.findViewById(R.id.spinner_origen_ingreso_no_laboral)).getSelectedItem().toString();
            String montoS = ((EditText)findViewById(R.id.monto_ingreso_no_laboral_et)).getText().toString();
            Integer monto = null;
            if(!montoS.equals(""))
                monto = Integer.parseInt(montoS);
            IngresoNoLaboral inl = new IngresoNoLaboral(origen,monto);
            ingreso.getIngresosNoLaborales().add(inl);
        }
        for(View vista: programasSocialesSti){
            String programa = ((Spinner)findViewById(R.id.spinner_nuevo_programa_social_sti)).getSelectedItem().toString();
            ingreso.getProgramas_sociales_sti().add(programa);
        }

        /*Tomo los datos de salud y creo el objeto correspondiente*/
        Salud salud = new Salud();

        String cobertura = utils.getDataTvSpinner(R.id.panel_cobertura);
        String fechaPartoS = utils.getDataTvEt(R.id.panel_embarazo);
        Date fechaParto = utils.getDateFromString(fechaPartoS);
        String independenciaFuncional = utils.getDataTvSpinner(R.id.panel_independecia_funcional);

        salud.setCobertura(cobertura);
        salud.setFecha_estimada_embarazo(fechaParto);
        salud.setIndependencia_funcional(independenciaFuncional);

        for (View vista:discapacidades) {
            String discapacidad = ((Spinner)vista.findViewById(R.id.spinner_nueva_discapacidad)).getSelectedItem().toString();
            salud.getDiscapacidades().add(discapacidad);
        }
        for(View vista:enfermedadesCronicas){
            String enfermedadCronica = ((Spinner)vista.findViewById(R.id.spinner_nueva_enfermedad_cronica)).getSelectedItem().toString();
            salud.getEnfermedadesCronicas().add(enfermedadCronica);
        }

        /*Tenemos todos los datos necesario para crear al familiar por lo tanto pasamos a la instanciacion de la clase Familiar*/
        return new Familiar(nombre,apellido,sexo,nacion,tipoDoc,cuil,fechaNac,estadoCivil,vinculo,nucleo,
                nivelEducativo,capacitacion,asistenciaCapacitacion,ocupacion,ingreso,salud);
    }

    private void inicializarGui(){
        Utils utils = new Utils(this);
        inicializarDatosGenerales(utils);
        inicializarOcupacion(utils);
        inicializarIngresos(utils);
        inicializarSalud(utils);
    }

    private void inicializarDatosGenerales(Utils utils){
        utils.setValuesTvEt(R.string.nombre_familiar,R.id.panel_nombres_familiar);
        utils.setValuesTvEt(R.string.apellido_familiar,R.id.panel_apellidos_familiar);
        utils.setValuesTvSpinner(R.array.tipo_doc_opciones, R.string.tipo_doc_familiar, R.id.panel_tipo_doc_familiar);
        utils.setValuesTvEt(R.string.cuil_familiar,R.id.panel_cuil_familiar);
        utils.setValuesTvSpinner(R.array.sexo_opciones,R.string.sexo_familiar,R.id.panel_sexo_familiar);
        utils.setValuesTvSpinner(R.array.nacionalidad_familiar_opciones,R.string.nacion_familiar,R.id.panel_nacion_familiar);
        utils.setValuesTvEt(R.string.fecha_nac_familiar,R.id.panel_fecha_nac_familiar);
        utils.addDateListener(R.id.panel_fecha_nac_familiar);
        utils.setValuesTvSpinner(R.array.estado_civil_opciones,R.string.estado_civil_familiar,R.id.panel_estado_civil_familiar);
        utils.setValuesTvSpinner(R.array.vinculo_opciones, R.string.vinculo_familiar, R.id.panel_vinculo_familiar);
        utils.setValuesTvSpinner(R.array.nucleo_opciones, R.string.nucleo_familiar, R.id.panel_nucleo_familiar);

        /*EDUCACION*/
        utils.setValuesTvSpinner(R.array.nivel_educativo_opciones,R.string.nivel_educativo,R.id.panel_nivel_educativo);
        utils.setValuesTvSpinner2(R.array.capacitacion_opciones,R.array.asistencia_capacitacion_opciones,R.string.capacitacion,R.id.panel_capacitacion);
    }

    private void inicializarOcupacion(Utils utils){
        utils.setValuesTvSpinner(R.array.condicion_actividad_opciones, R.string.condicion_actividad, R.id.panel_condicion_actividad);
        utils.setValuesTvSpinner(R.array.puesto_trabajo_opciones, R.string.puesto_trabajo, R.id.panel_puesto_trabajo);
        utils.setValuesTvSpinner(R.array.aportes_jubilatorios_opciones, R.string.aporte_jubilatorio, R.id.panel_aporte_jubilatorio);
        utils.setValuesTvSpinner(R.array.duracion_empleo_opciones, R.string.duracion, R.id.panel_duracion_empleo);
        utils.setValuesTvSpinner(R.array.tipo_actividad_opciones, R.string.tipo_actividad, R.id.panel_tipo_actividad);
        utils.setValuesTvSpinner(R.array.calificacion_opciones, R.string.calificacion, R.id.panel_calificacion);
    }

    private void inicializarIngresos(Utils utils){
        utils.setValuesTvEt(R.string.ingresos_laborales, R.id.panel_ingresos_laborales);
        utils.setValuesTvEt(R.string.ingresos_no_laborales, R.id.panel_ingresos_no_laborales);
        utils.addAddingButtonListener(R.id.panel_ingresos_no_laborales, R.layout.panel_ingreso_no_laboral, ingresosNoLaborales);
        utils.setValuesTvEt(R.string.programas_sociales_sti, R.id.panel_programas_sociales_sti);
        utils.addAddingButtonListener(R.id.panel_programas_sociales_sti,R.layout.panel_nuevo_programa_social_sti,programasSocialesSti);

    }

    private void inicializarSalud(Utils utils){
        utils.setValuesTvSpinner(R.array.cobertura_opciones, R.string.cobertura, R.id.panel_cobertura);
        utils.setValuesTvEt(R.string.fecha_parto, R.id.panel_embarazo);
        utils.setValuesTvEt(R.string.discapacidad, R.id.panel_discapacidad);
        utils.addAddingButtonListener(R.id.panel_discapacidad,R.layout.panel_nueva_discapacidad,discapacidades);
        utils.setValuesTvEt(R.string.enfermedad_cronica, R.id.panel_enfermedad_cronica);
        utils.addAddingButtonListener(R.id.panel_enfermedad_cronica,R.layout.panel_nueva_enfermedad_cronica,enfermedadesCronicas);
        utils.setValuesTvSpinner(R.array.independencia_funcional_opciones, R.string.independencia_funcional, R.id.panel_independecia_funcional);

    }


}
