package ar.edu.uns.cs.trabajosocialform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import ar.edu.uns.cs.trabajosocialform.java_classes.DataModel.Domicilio;
import ar.edu.uns.cs.trabajosocialform.java_classes.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.java_classes.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.java_classes.configuracion.Datos_apoderado;
import ar.edu.uns.cs.trabajosocialform.java_classes.configuracion.Datos_domicilio;

public class FormDomicilioActivity extends AppCompatActivity {

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_domicilio);

        Intent intent = getIntent();
        bundle = intent.getBundleExtra("CONFIG");
        this.bundle = bundle;
        Configuracion config = (Configuracion)bundle.getSerializable("CONFIG");

        ViewAdapter va = new ViewAdapter(config,this);
        va.adaptarDomicilio();
    }

    public void continuar(View view){
        Intent intent = new Intent(this,FormGrupoFamiliarActivity.class);
        intent.putExtra("CONFIG",bundle);
        startActivity(intent);
    }


    private void tomarDatos(){
        Configuracion config = (Configuracion)bundle.getSerializable("CONFIG");
        Datos_domicilio datos = config.getDatos_domicilio();

        String calle = ((EditText)findViewById(R.id.calle_et)).getText().toString();
        String numero = ((EditText)findViewById(R.id.numero_et)).getText().toString();
        String manzana = ((EditText)findViewById(R.id.manzana_et)).getText().toString();
        String monoblock = ((EditText)findViewById(R.id.monoblock_torre_et)).getText().toString();
        String piso = ((EditText)findViewById(R.id.piso_et)).getText().toString();
        String accint = ((EditText)findViewById(R.id.acc_int_et)).getText().toString();
        String calle1 = ((EditText)findViewById(R.id.entre_calles1_et)).getText().toString();
        String calle2 = ((EditText)findViewById(R.id.entre_calles2_et)).getText().toString();
        String barrio = ((EditText)findViewById(R.id.barrio_et)).getText().toString();
        String delegacion = ((EditText)findViewById(R.id.delegacion_et)).getText().toString();
        String localidad = ((EditText)findViewById(R.id.localidad_et)).getText().toString();

        Domicilio domicilio = new Domicilio();

    }
}
