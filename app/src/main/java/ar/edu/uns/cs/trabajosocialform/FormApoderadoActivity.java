package ar.edu.uns.cs.trabajosocialform;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ar.edu.uns.cs.trabajosocialform.DataModel.Apoderado;
import ar.edu.uns.cs.trabajosocialform.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;
import ar.edu.uns.cs.trabajosocialform.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.configuracion.Datos_apoderado;

public class FormApoderadoActivity extends AppCompatActivity {

    private Bundle bundle;
    private Formulario form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_apoderado);

        inicializarGui();

        Intent intent = getIntent();
        bundle = intent.getBundleExtra("CONFIG");
        form = (Formulario)intent.getSerializableExtra("FORM");
        Configuracion config = (Configuracion)bundle.getSerializable("CONFIG");

        ViewAdapter va = new ViewAdapter(config,this);
        va.adaptarApoderado();

    }

    public void continuar(View view){
        Intent intent = new Intent(this,FormDomicilioActivity.class);
        Apoderado apoderado = tomarDatos();
        form.setApoderado(apoderado);
        intent.putExtra("CONFIG",bundle);
        intent.putExtra("FORM",form);
        startActivity(intent);
        finish();
    }

    private Apoderado tomarDatos(){
        Configuracion config = (Configuracion)bundle.getSerializable("CONFIG");
        Datos_apoderado datos = config.getDatos_apoderado();
        Utils utils = new Utils(this);

        String nombre = utils.getDataTvEt(R.id.panel_nombres_apoderado);
        String apellido = utils.getDataTvEt(R.id.panel_apellidos_apoderado);
        String cuilS = utils.getDataTvEt(R.id.panel_cuil_apoderado);
        String telefono = utils.getDataTvEt(R.id.panel_telefono_principal_apoderado);
        String fechaNacimiento = utils.getDataTvEt(R.id.panel_fecha_nacimiento_apoderado);
        String motivosPoder = utils.getDataTvEt(R.id.panel_motivos_poder_apoderado);

        Integer cuil=null;
        if(!cuilS.equals(""))
            cuil = Integer.parseInt(cuilS);

        Date fecha=null;
        if(!fechaNacimiento.equals("")){
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
            try {
                fecha = formatoDelTexto.parse(fechaNacimiento);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        Apoderado apoderado = new Apoderado(nombre,apellido,cuil,telefono,fecha,motivosPoder);

        return apoderado;

    }

    public void elegirFecha(View view){
        Utils.getFecha(this);
    }

    private void inicializarGui(){
        Utils utils = new Utils(this);
        utils.setValuesTvEt(R.string.nombres_apoderado,R.id.panel_nombres_apoderado);
        utils.setValuesTvEt(R.string.apellidos_apoderado,R.id.panel_apellidos_apoderado);
        utils.setValuesTvEt(R.string.cuil_apoderado,R.id.panel_cuil_apoderado);
        utils.setValuesTvEt(R.string.telefono_apoderado,R.id.panel_telefono_principal_apoderado);
        utils.setValuesTvEt(R.string.fecha_nac_apoderado,R.id.panel_fecha_nacimiento_apoderado);
        utils.setValuesTvEt(R.string.motivos_de_poder, R.id.panel_motivos_poder_apoderado);

        utils.addDateListener(R.id.panel_fecha_nacimiento_apoderado);

    }

}
