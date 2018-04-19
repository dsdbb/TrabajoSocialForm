package ar.edu.uns.cs.trabajosocialform;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ar.edu.uns.cs.trabajosocialform.fragments.DatePickerFragment;
import ar.edu.uns.cs.trabajosocialform.java_classes.DataModel.Apoderado;
import ar.edu.uns.cs.trabajosocialform.java_classes.DataModel.Solicitante;
import ar.edu.uns.cs.trabajosocialform.java_classes.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.java_classes.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.java_classes.configuracion.Datos_apoderado;
import ar.edu.uns.cs.trabajosocialform.java_classes.configuracion.Datos_solicitante;

public class FormApoderadoActivity extends AppCompatActivity {

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_apoderado);

        Intent intent = getIntent();
        bundle = intent.getBundleExtra("CONFIG");
        this.bundle = bundle;
        Configuracion config = (Configuracion)bundle.getSerializable("CONFIG");

        ViewAdapter va = new ViewAdapter(config,this);
        va.adaptarApoderado();

    }

    public void continuar(View view){
        Intent intent = new Intent(this,FormDomicilioActivity.class);
        tomarDatos();
        intent.putExtra("CONFIG",bundle);
        startActivity(intent);
    }

    private void tomarDatos(){
        Configuracion config = (Configuracion)bundle.getSerializable("CONFIG");
        Datos_apoderado datos = config.getDatos_apoderado();

        EditText nombreEt = (EditText) findViewById(R.id.nombres_apoderado_form_et);
        EditText apellidoEt = (EditText) findViewById(R.id.apellidos_apoderado_form_et);
        EditText cuilEt = (EditText) findViewById(R.id.cuil_apoderado_form_et);
        EditText telefonoEt = (EditText) findViewById(R.id.telefonoPrincipal_apoderado_form_et);
        EditText fechaNacEt = (EditText) findViewById(R.id.fecha_nac_apoderado_form_et);
        EditText motivosPoderEt = (EditText) findViewById(R.id.motivosDePoder_apoderado_form_et);

        String nombre = nombreEt.getText().toString();
        String apellido = apellidoEt.getText().toString();
        Integer cuil=null;

        if(!cuilEt.getText().toString().equals(""))
            cuil = Integer.parseInt(cuilEt.getText().toString());


        String telefono = telefonoEt.getText().toString();
        String fechaNacimiento = fechaNacEt.getText().toString();
        Date fecha=null;
        if(!fechaNacimiento.equals("")){
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
            try {
                fecha = formatoDelTexto.parse(fechaNacimiento);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        String motivosPoder = motivosPoderEt.getText().toString();

        Apoderado apoderado = new Apoderado(nombre,apellido,cuil,telefono,fecha,motivosPoder);


    }

    public void elegirFecha(View view){
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String mes = String.format("%02d",month+1);
                String dia = String.format("%02d",day);
                // +1 because january is zero
                final String selectedDate = dia + " / " + mes + " / " + year;
                ((EditText)findViewById(R.id.fecha_nac_apoderado_form_et)).setText(selectedDate);
            }
        });
        newFragment.show(getFragmentManager(), "datePicker");
    }
}
