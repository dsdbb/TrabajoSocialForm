package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Familiar;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Ingreso;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.IngresoNoLaboral;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Ocupacion;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Salud;
import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.Utils.FieldsValidator;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;
import ar.edu.uns.cs.trabajosocialform.Presentation.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NuevoFamiliarView extends GeneralActivity {

    private List<View> ingresosNoLaborales;
    private List<View> programasSocialesSti;
    private List<View> discapacidades;
    private List<View> enfermedadesCronicas;
    private Familiar updateFamiliar;

    private EditText nombreEt;
    private EditText apellidoEt;
    private EditText cuilEt;
    private EditText fechaNacimientoEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_familiar);

        ingresosNoLaborales = new ArrayList<View>();
        programasSocialesSti = new ArrayList<View>();
        discapacidades = new ArrayList<View>();
        enfermedadesCronicas = new ArrayList<View>();
        form = (Formulario)getIntent().getSerializableExtra("FORM");

        Intent intent = getIntent();
        config = (Configuracion)intent.getSerializableExtra("CONFIG");

        inicializarGui();
        /*Bind Activity to use ButterKnife facilities*/
        ButterKnife.bind(this);

        nombreEt = findViewById(R.id.panel_nombres_familiar).findViewById(R.id.editText);
        apellidoEt = findViewById(R.id.panel_apellidos_familiar).findViewById(R.id.editText);
        cuilEt = findViewById(R.id.panel_cuil_familiar).findViewById(R.id.editText);
        fechaNacimientoEt = findViewById(R.id.panel_fecha_nac_familiar).findViewById(R.id.editText);
        cuilEt.setInputType(InputType.TYPE_CLASS_NUMBER);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        /*Chequeo si es un update y en ese caso relleno los campos*/
        update = getIntent().getBooleanExtra("UPDATE",false);
        if(update){
            updateFamiliar = (Familiar)getIntent().getSerializableExtra("UPDATE_FAMILIAR");
            rellenarCampos();
        }

        ViewAdapter va = new ViewAdapter(config,this);
        va.adaptarNuevoFamiliar();
    }

    @Override
    @OnClick(R.id.siguiente_button)
    public void continuar(View view){
        Familiar familiar = tomarDatos();
        if(validate(familiar)){
            form.getFamiliares().add(familiar);
            Intent resultIntent = new Intent();
            if(update){
                resultIntent.putExtra("UPDATED_FAMILIAR",true);
                resultIntent.putExtra("OLD_FAMILIAR",updateFamiliar);
            }
            resultIntent.putExtra("RETURN_FORM",form);
            setResult(Activity.RESULT_OK,resultIntent);
            finish();
        }
        else{
            Toast.makeText(this,R.string.datos_invalidos,Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected Familiar tomarDatos(){
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
            String programa = ((Spinner)vista.findViewById(R.id.spinner_nuevo_programa_social_sti)).getSelectedItem().toString();
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

    @Override
    protected void inicializarGui(){
        Utils utils = new Utils(this);

        /*Titulo toolbar*/
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.titulo_nuevo_familiar);


        inicializarDatosGenerales(utils);
        inicializarOcupacion(utils);
        inicializarIngresos(utils);
        inicializarSalud(utils);

        /*Get most important EditText*/

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
        utils.addRemovingButtonListener(R.id.panel_ingresos_no_laborales, ingresosNoLaborales);
        utils.setValuesTvEt(R.string.programas_sociales_sti, R.id.panel_programas_sociales_sti);
        utils.addAddingButtonListener(R.id.panel_programas_sociales_sti,R.layout.panel_nuevo_programa_social_sti,programasSocialesSti);
        utils.addRemovingButtonListener(R.id.panel_programas_sociales_sti,programasSocialesSti);
    }

    private void inicializarSalud(Utils utils){
        utils.setValuesTvSpinner(R.array.cobertura_opciones, R.string.cobertura, R.id.panel_cobertura);
        utils.setValuesTvEt(R.string.fecha_parto, R.id.panel_embarazo);
        utils.setValuesTvEt(R.string.discapacidad, R.id.panel_discapacidad);
        utils.addAddingButtonListener(R.id.panel_discapacidad,R.layout.panel_nueva_discapacidad,discapacidades);
        utils.addRemovingButtonListener(R.id.panel_discapacidad, discapacidades);
        utils.setValuesTvEt(R.string.enfermedad_cronica, R.id.panel_enfermedad_cronica);
        utils.addAddingButtonListener(R.id.panel_enfermedad_cronica,R.layout.panel_nueva_enfermedad_cronica,enfermedadesCronicas);
        utils.addRemovingButtonListener(R.id.panel_enfermedad_cronica, enfermedadesCronicas);
        utils.setValuesTvSpinner(R.array.independencia_funcional_opciones, R.string.independencia_funcional, R.id.panel_independecia_funcional);

    }

    @Override
    protected void rellenarCampos(){
        Utils utils = new Utils(this);

        /*Datos generales del familiar*/
        utils.setValueToEditText(R.id.panel_nombres_familiar, updateFamiliar.getNombres());
        utils.setValueToEditText(R.id.panel_apellidos_familiar, updateFamiliar.getApellidos());
        utils.setValueToSpinner(R.id.panel_tipo_doc_familiar,R.array.tipo_doc_opciones ,updateFamiliar.getTipoDocumento());
        utils.setValueToEditText(R.id.panel_cuil_familiar, updateFamiliar.getCuil());
        utils.setValueToSpinner(R.id.panel_sexo_familiar, R.array.sexo_opciones, updateFamiliar.getSexo());
        utils.setValueToSpinner(R.id.panel_nacion_familiar, R.array.nacionalidad_familiar_opciones,updateFamiliar.getNacion());
        String fecha = utils.getStringFromDate(updateFamiliar.getFecha_nacimiento());
        utils.setValueToEditText(R.id.panel_fecha_nac_familiar, fecha);
        utils.setValueToSpinner(R.id.panel_estado_civil_familiar, R.array.estado_civil_opciones, updateFamiliar.getEstado_civil());
        utils.setValueToSpinner(R.id.panel_vinculo_familiar, R.array.vinculo_opciones, updateFamiliar.getVinculo());
        utils.setValueToSpinner(R.id.panel_nucleo_familiar, R.array.nucleo_opciones, updateFamiliar.getNucleo());
        utils.setValueToSpinner(R.id.panel_nivel_educativo, R.array.nivel_educativo_opciones, updateFamiliar.getNivel_educativo());
        utils.setValueToSpinner2(R.id.panel_capacitacion, R.array.capacitacion_opciones, updateFamiliar.getCapacitacion(),
                R.array.asistencia_capacitacion_opciones, updateFamiliar.getAsistenciaCapacitacion());

        /*Datos de la ocupacion*/
        Ocupacion ocupacion = updateFamiliar.getOcupacion();
        utils.setValueToSpinner(R.id.panel_condicion_actividad, R.array.capacitacion_opciones, ocupacion.getCondicion_actividad());
        utils.setValueToSpinner(R.id.panel_puesto_trabajo, R.array.puesto_trabajo_opciones, ocupacion.getPuesto_trabajo());
        utils.setValueToSpinner(R.id.panel_aporte_jubilatorio, R.array.aportes_jubilatorios_opciones, ocupacion.getAporte_jubilatorio());
        utils.setValueToSpinner(R.id.panel_duracion_empleo, R.array.duracion_empleo_opciones, ocupacion.getDuracion());
        utils.setValueToSpinner(R.id.panel_tipo_actividad, R.array.tipo_actividad_opciones, ocupacion.getTipo_actividad());
        utils.setValueToSpinner(R.id.panel_calificacion, R.array.calificacion_opciones, ocupacion.getCalificacion());

        /*Datos de los Ingresos*/
        Ingreso ingreso = updateFamiliar.getIngreso();
        utils.setValueToEditText(R.id.panel_ingresos_laborales, ingreso.getIngresos_laborales());
        List<IngresoNoLaboral> noLaborales = ingreso.getIngresosNoLaborales();
        for(int i=0;i<noLaborales.size();i++){
           View view = utils.inflarEnUpdate(R.id.panel_ingresos_no_laborales,R.layout.panel_ingreso_no_laboral);
           ingresosNoLaborales.add(view);
           Spinner origen = view.findViewById(R.id.spinner_origen_ingreso_no_laboral);
           EditText monto = view.findViewById(R.id.monto_ingreso_no_laboral_et);

           ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ingresos_no_laborales_opciones, android.R.layout.simple_spinner_item);
            origen.setAdapter(adapter);
            String compareValue = noLaborales.get(i).getOrigen();
            if (compareValue != null) {
                int spinnerPosition = adapter.getPosition(compareValue);
                origen.setSelection(spinnerPosition);
            }

            Integer valor = noLaborales.get(i).getMonto();
            Log.i("Valor",valor+"");
            monto.setText(valor+"");
        }

        List<String> programasSocialesStiString = ingreso.getProgramas_sociales_sti();
        for(int i=0; i<programasSocialesStiString.size();i++){
            View view = utils.inflarEnUpdate(R.id.panel_programas_sociales_sti,R.layout.panel_nuevo_programa_social_sti);
            programasSocialesSti.add(view);
            Spinner spinner = view.findViewById(R.id.spinner_nuevo_programa_social_sti);
            utils.setValueToSpinner(spinner, R.array.programas_sociales_sti_opciones, programasSocialesStiString.get(i));
        }

        /*Salud*/
        Salud salud = updateFamiliar.getSalud();
        utils.setValueToSpinner(R.id.panel_cobertura, R.array.cobertura_opciones, salud.getCobertura());
        String fechaParto = utils.getStringFromDate(salud.getFecha_estimada_embarazo());
        utils.setValueToEditText(R.id.panel_embarazo, fechaParto);

        List<String> discapacidadesString = salud.getDiscapacidades();
        for(int i=0; i<discapacidadesString.size();i++){
            View view = utils.inflarEnUpdate(R.id.panel_discapacidad,R.layout.panel_nueva_discapacidad);
            discapacidades.add(view);
            Spinner spinner = view.findViewById(R.id.spinner_nueva_discapacidad);
            utils.setValueToSpinner(spinner, R.array.discapacidad_opciones,discapacidadesString.get(i));
        }

        List<String> enfermedadesString = salud.getEnfermedadesCronicas();
        for(int i=0;i<enfermedadesString.size();i++){
            View view = utils.inflarEnUpdate(R.id.panel_enfermedad_cronica, R.layout.panel_nueva_enfermedad_cronica);
            enfermedadesCronicas.add(view);
            Spinner spinner = view.findViewById(R.id.spinner_nueva_enfermedad_cronica);
            utils.setValueToSpinner(spinner, R.array.enfermedad_cronica_opciones, enfermedadesString.get(i));
        }

        utils.setValueToSpinner(R.id.panel_independecia_funcional, R.array.independencia_funcional_opciones, salud.getIndependencia_funcional());


    }

    @Override
    protected boolean validate(Object obj){
        boolean result = true;
        Familiar familiar = (Familiar)obj;

        FieldsValidator validator = new FieldsValidator();

        if(!validator.validateName(familiar.getNombres()) && config.getDatos_grupo_familiar().isNombres()){
            nombreEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorError));
            result = false;
        }
        else{
            nombreEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorMain));
        }
        if(!validator.validateSurname(familiar.getApellidos()) && config.getDatos_grupo_familiar().isApellidos()){
            apellidoEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorError));
            result = false;
        }
        else{
            apellidoEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorMain));
        }
        if(!validator.validateCuil(familiar.getCuil()) && config.getDatos_grupo_familiar().isCuil()){
            cuilEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorError));
            result = false;
        }
        else{
            cuilEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorMain));
        }
        if(!validator.validateDate(familiar.getFecha_nacimiento()) && config.getDatos_grupo_familiar().isFecha_nac()){
            fechaNacimientoEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorError));
            result = false;
        }
        else{
            fechaNacimientoEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorMain));
        }

        return result;
    }

}
