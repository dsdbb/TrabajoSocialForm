package ar.edu.uns.cs.trabajosocialform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
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
    }

    public void guardar(View view){
        Familiar familiar = tomarDatos();
        form.getFamiliares().add(familiar);
        getIntent().putExtra("FORM",form);
        finish();
    }

    private Familiar tomarDatos(){
        /*Tomo los datos generales*/
        String nombre = ((EditText)findViewById(R.id.nombres_familiar_et)).getText().toString();
        String apellido = ((EditText)findViewById(R.id.apellidos_familiar_et)).getText().toString();
        String sexo = ((Spinner)findViewById(R.id.spinner_sexo_familiar)).getSelectedItem().toString();
        String nacion = ((Spinner)findViewById(R.id.spinner_nacion_familiar)).getSelectedItem().toString();
        String tipoDoc = ((Spinner)findViewById(R.id.spinner_tipo_doc_familiar)).getSelectedItem().toString();
        String cuilS = ((EditText)findViewById(R.id.cuil_familiar_et)).getText().toString();
        String estadoCivil = ((Spinner)findViewById(R.id.spinner_estado_civil_familiar)).getSelectedItem().toString();
        String vinculo = ((Spinner)findViewById(R.id.spinner_vinculo_familiar)).getSelectedItem().toString();
        String nucleo = ((Spinner)findViewById(R.id.spinner_nucleo_familiar)).getSelectedItem().toString();
        String fechaNacS = ((EditText)findViewById(R.id.fecha_nac_familiar_et)).getText().toString();
        String nivelEducativo = ((Spinner)findViewById(R.id.spinner_nivel_educativo)).getSelectedItem().toString();
        String capacitacion = ((Spinner)findViewById(R.id.spinner_capacitacion)).getSelectedItem().toString();

        Date fechaNac=null;
        if(!fechaNacS.equals("")){
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
            try {
                fechaNac = formatoDelTexto.parse(fechaNacS);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        Integer cuil = null;
        if(!cuilS.equals(""))
            cuil = Integer.parseInt(cuilS);

        /*Tomo datos de la Ocupacion y creo el objeto correspondiente*/
        String condicionActividad = ((Spinner)findViewById(R.id.spinner_condicion_actividad)).getSelectedItem().toString();
        String puestoTrabajo = ((Spinner)findViewById(R.id.spinner_puesto_trabajo)).getSelectedItem().toString();
        String aporteJubilatorio = ((Spinner)findViewById(R.id.spinner_aporte_jubilatorio)).getSelectedItem().toString();
        String duracionEmpleo = ((Spinner)findViewById(R.id.spinner_duracion_empleo)).getSelectedItem().toString();
        String tipoActividad = ((Spinner)findViewById(R.id.spinner_tipo_actividad)).getSelectedItem().toString();
        String calificacion = ((Spinner)findViewById(R.id.spinner_calificacion)).getSelectedItem().toString();

        Ocupacion ocupacion = new Ocupacion(condicionActividad,puestoTrabajo,aporteJubilatorio,duracionEmpleo,tipoActividad,calificacion);

        /*Tomo datos del Ingreso y creo el objeto correspondiente*/
        Ingreso ingreso = new Ingreso();
        String ingresoLaboralS = ((EditText)findViewById(R.id.ingresos_laborales_et)).getText().toString();
        Integer ingresoLaboral = null;
        if(!ingresoLaboralS.equals(""))
            ingresoLaboral = Integer.parseInt(ingresoLaboralS);
        ingreso.setIngresos_laborales(ingresoLaboral);
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


        /*Tomo los datos de Salud y creo el objeto correspondiente*/
        Salud salud = new Salud();
        String cobertura = ((Spinner)findViewById(R.id.spinner_cobertura)).getSelectedItem().toString();
        String fechaPartoS = ((EditText)findViewById(R.id.fecha_parto_et)).getText().toString();
        Date fechaParto=null;
        if(!fechaPartoS.equals("")){
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
            try {
                fechaParto = formatoDelTexto.parse(fechaPartoS);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        salud.setCobertura(cobertura);
        salud.setFecha_estimada_embarazo(fechaParto);

        for (View vista:discapacidades) {
            String discapacidad = ((Spinner)vista.findViewById(R.id.spinner_nueva_discapacidad)).getSelectedItem().toString();
            salud.getDiscapacidades().add(discapacidad);
        }
        for(View vista:enfermedadesCronicas){
            String enfermedadCronica = ((Spinner)vista.findViewById(R.id.spinner_nueva_enfermedad_cronica)).getSelectedItem().toString();
            salud.getEnfermedadesCronicas().add(enfermedadCronica);
        }

        /*Tenemos todos los datos necesario para crear al familiar por lo tanto pasamos a la instanciacion de la clase Familiar*/
        return new Familiar(nombre,apellido,sexo,nacion,cuil,fechaNac,estadoCivil,vinculo,nucleo,
                nivelEducativo,capacitacion,ocupacion,ingreso,salud);
    }

    private void rellenarListasDesplegables(){
        Spinner spinner = (Spinner)findViewById(R.id.spinner_sexo_familiar);
        String[] values = getResources().getStringArray(R.array.sexo_familiar_opciones);
        for (String valor: values) {
            Log.i("Valor: ",valor);

        }
        spinner.setAdapter(new ArrayAdapter<String>(this,R.layout.spinner_row,values));
    }

    public void nuevoIngresoNoLaboral(View view){
        LinearLayout contenedor = (LinearLayout) findViewById(R.id.panel_nuevo_ingreso_no_laboral);
        LayoutInflater inflater = LayoutInflater.from(this);

        View inflatedView = inflater.inflate(R.layout.panel_ingreso_no_laboral, contenedor, false);
        ingresosNoLaborales.add(inflatedView);
        contenedor.addView(inflatedView);
    }

    public void nuevoProgramaSocialSti(View view){
        LinearLayout contenedor = (LinearLayout) findViewById(R.id.panel_nuevo_programa_social_sti);
        LayoutInflater inflater = LayoutInflater.from(this);

        View inflatedView = inflater.inflate(R.layout.panel_nuevo_programa_social_sti, contenedor, false);
        programasSocialesSti.add(inflatedView);
        contenedor.addView(inflatedView);
    }

    public void elegirFecha(View view){
        Utils.getFecha(this);
    }

    public void nuevaDiscapacidad(View view){
        LinearLayout contenedor = (LinearLayout) findViewById(R.id.panel_nueva_discapacidad);
        LayoutInflater inflater = LayoutInflater.from(this);

        View inflatedView = inflater.inflate(R.layout.panel_nueva_discapacidad, contenedor, false);
        discapacidades.add(inflatedView);
        contenedor.addView(inflatedView);
    }

    public void nuevaEnfermedadCronica(View view){
        LinearLayout contenedor = (LinearLayout) findViewById(R.id.panel_nueva_enfermedad_cronica);
        LayoutInflater inflater = LayoutInflater.from(this);

        View inflatedView = inflater.inflate(R.layout.panel_nueva_enfermedad_cronica, contenedor, false);
        enfermedadesCronicas.add(inflatedView);
        contenedor.addView(inflatedView);
    }
}
