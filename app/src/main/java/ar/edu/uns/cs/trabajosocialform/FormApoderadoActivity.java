package ar.edu.uns.cs.trabajosocialform;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ar.edu.uns.cs.trabajosocialform.DataModel.Apoderado;
import ar.edu.uns.cs.trabajosocialform.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Utils.FieldsValidator;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;
import ar.edu.uns.cs.trabajosocialform.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.configuracion.Datos_apoderado;

public class FormApoderadoActivity extends GeneralActivity {


    private EditText nombreEt;
    private EditText apellidoEt;
    private EditText cuilEt;
    private EditText fechaNacimientoEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_apoderado);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        Intent intent = getIntent();
        form = (Formulario)intent.getSerializableExtra("FORM");
        config = (Configuracion)intent.getSerializableExtra("CONFIG");

        if(!config.getDatos_apoderado().required()){
            continuar();
        }
        else{
            inicializarGui();

            nombreEt = findViewById(R.id.panel_nombres_apoderado).findViewById(R.id.editText);
            apellidoEt = findViewById(R.id.panel_apellidos_apoderado).findViewById(R.id.editText);
            cuilEt = findViewById(R.id.panel_cuil_apoderado).findViewById(R.id.editText);
            fechaNacimientoEt = findViewById(R.id.panel_fecha_nacimiento_apoderado).findViewById(R.id.editText);

            /*Chequeo si es un update y en ese caso relleno los campos*/
            update = getIntent().getBooleanExtra("UPDATE",false);
            if(update){
                updateForm = (Formulario)getIntent().getSerializableExtra("UPDATE_FORM");
                rellenarCampos();
            }

            ViewAdapter va = new ViewAdapter(config,this);
            va.adaptarApoderado();
        }


    }

    @Override
    public void continuar(){
        Intent intent = new Intent(this,FormDomicilioActivity.class);
        Apoderado apoderado = tomarDatos();

        if(validate(apoderado)){
            form.setApoderado(apoderado);
            intent.putExtra("CONFIG",config);
            intent.putExtra("FORM",form);
            intent.putExtra("UPDATE",update);
            if(update)
                intent.putExtra("UPDATE_FORM", updateForm);
            startActivity(intent);

            if(!config.getDatos_apoderado().required()){
                finish();
            }
        }
        else{
            Toast.makeText( this,R.string.datos_invalidos, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected Apoderado tomarDatos(){
        Datos_apoderado datos = config.getDatos_apoderado();
        Utils utils = new Utils(this);

        String nombre = utils.getDataTvEt(R.id.panel_nombres_apoderado);
        String apellido = utils.getDataTvEt(R.id.panel_apellidos_apoderado);
        String cuilS = utils.getDataTvEt(R.id.panel_cuil_apoderado);
        String telefono = utils.getDataTvEt(R.id.panel_telefono_principal_apoderado);
        String fechaNacimiento = utils.getDataTvEt(R.id.panel_fecha_nacimiento_apoderado);
        String motivosPoder = utils.getDataTvEt(R.id.panel_motivos_poder_apoderado);

       Long cuil = utils.getLongFromString(cuilS);

       Date fecha = utils.getDateFromString(fechaNacimiento);

        Apoderado apoderado = new Apoderado(nombre,apellido,cuil,telefono,fecha,motivosPoder);

        return apoderado;

    }


    protected void inicializarGui(){
        Utils utils = new Utils(this);
        /*Agrego formulario al template*/
        utils.addContentToTemplate(R.layout.form_apoderado);

        /*Titulo toolbar*/
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.titulo_apoderado);

        /*Datos*/
        utils.setValuesTvEt(R.string.nombres_apoderado,R.id.panel_nombres_apoderado);
        utils.setValuesTvEt(R.string.apellidos_apoderado,R.id.panel_apellidos_apoderado);
        utils.setValuesTvEt(R.string.cuil_apoderado,R.id.panel_cuil_apoderado);
        utils.setValuesTvEt(R.string.telefono_apoderado,R.id.panel_telefono_principal_apoderado);
        utils.setValuesTvEt(R.string.fecha_nac_apoderado,R.id.panel_fecha_nacimiento_apoderado);
        utils.setValuesTvEt(R.string.motivos_de_poder, R.id.panel_motivos_poder_apoderado);

        utils.addDateListener(R.id.panel_fecha_nacimiento_apoderado);

        utils.addNextButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continuar();
            }
        });

    }


    protected void rellenarCampos(){
        Utils utils = new Utils(this);

        Apoderado apoderado = updateForm.getApoderado();
        utils.setValueToEditText(R.id.panel_nombres_apoderado, apoderado.getNombres() );
        utils.setValueToEditText(R.id.panel_apellidos_apoderado, apoderado.getApellidos());
        utils.setValueToEditText(R.id.panel_cuil_apoderado, apoderado.getCuil());
        utils.setValueToEditText(R.id.panel_telefono_principal_apoderado, apoderado.getTelefono());
        String fecha = utils.getStringFromDate(apoderado.getFecha_nacimiento());
        utils.setValueToEditText(R.id.panel_fecha_nacimiento_apoderado, fecha);
        utils.setValueToEditText(R.id.panel_motivos_poder_apoderado, apoderado.getMotivos_poder());
    }

    @Override
    protected boolean validate(Object obj){

        boolean result = true;
        Apoderado apoderado = (Apoderado)obj;
        FieldsValidator validator = new FieldsValidator();

        if(!validator.validateShortString(apoderado.getNombres())){
            nombreEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorError));
            result = false;
        }
        else{
            nombreEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorMain));
        }
        if(!validator.validateShortString(apoderado.getApellidos())){
            apellidoEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorError));
            result = false;
        }
        else{
            apellidoEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorMain));

        }
        if(!validator.validateCuil(apoderado.getCuil())){
            cuilEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorError));
            result = false;
        }
        else{
            cuilEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorMain));
        }
        if(!validator.validateDate(apoderado.getFecha_nacimiento())){
            fechaNacimientoEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorError));
            result = false;
        }
        else{
            fechaNacimientoEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorMain));
        }

        return result;
    }

}
