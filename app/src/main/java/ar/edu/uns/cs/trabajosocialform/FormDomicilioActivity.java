package ar.edu.uns.cs.trabajosocialform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import ar.edu.uns.cs.trabajosocialform.DataModel.Domicilio;
import ar.edu.uns.cs.trabajosocialform.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.configuracion.Datos_domicilio;

public class FormDomicilioActivity extends AppCompatActivity {

    private Bundle bundle;
    private Formulario form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_domicilio);

        Intent intent = getIntent();
        bundle = intent.getBundleExtra("CONFIG");
        form = (Formulario)intent.getSerializableExtra("FORM");
        Configuracion config = (Configuracion)bundle.getSerializable("CONFIG");

        ViewAdapter va = new ViewAdapter(config,this);
        va.adaptarDomicilio();
    }

    public void continuar(View view){
        Domicilio domicilio = tomarDatos();
        form.setDomicilio(domicilio);
        Intent intent = new Intent(this,FormGrupoFamiliarActivity.class);
        intent.putExtra("CONFIG",bundle);
        intent.putExtra("FORM",form);
        startActivity(intent);
        finish();
    }


    private Domicilio tomarDatos(){
        Configuracion config = (Configuracion)bundle.getSerializable("CONFIG");
        Datos_domicilio datos = config.getDatos_domicilio();

        String calle = ((EditText)findViewById(R.id.calle_et)).getText().toString();
        String numeroS = ((EditText)findViewById(R.id.numero_et)).getText().toString();
        String manzanaS = ((EditText)findViewById(R.id.manzana_et)).getText().toString();
        String monoblockS = ((EditText)findViewById(R.id.monoblock_torre_et)).getText().toString();
        String pisoS = ((EditText)findViewById(R.id.piso_et)).getText().toString();
        String accintS = ((EditText)findViewById(R.id.acc_int_et)).getText().toString();
        String deptoS = ((EditText)findViewById(R.id.acc_int_et)).getText().toString();
        String calle1 = ((EditText)findViewById(R.id.entre_calles1_et)).getText().toString();
        String calle2 = ((EditText)findViewById(R.id.entre_calles2_et)).getText().toString();
        String barrio = ((EditText)findViewById(R.id.barrio_et)).getText().toString();
        String delegacion = ((EditText)findViewById(R.id.delegacion_et)).getText().toString();
        String localidad = ((EditText)findViewById(R.id.localidad_et)).getText().toString();

        Integer numero = null;
        if(!numeroS.equals(""))
           numero = Integer.parseInt(numeroS);

        Integer manzana = null;
        if(!manzanaS.equals(""))
            manzana = Integer.parseInt(manzanaS);

        Integer monoblock = null;
        if(!monoblockS.equals(""))
            monoblock = Integer.parseInt(monoblockS);

        Integer piso = null;
        if(!pisoS.equals(""))
            piso = Integer.parseInt(pisoS);

        Integer accint = null;
        if(!accintS.equals(""))
            accint = Integer.parseInt(accintS);

        Integer depto = null;
        if(!deptoS.equals(""))
            depto = Integer.parseInt(deptoS);

       return new Domicilio(calle,numero,manzana,monoblock,piso,accint,depto,calle1,calle2,barrio,delegacion,localidad);

    }
}
