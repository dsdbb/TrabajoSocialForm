package ar.edu.uns.cs.trabajosocialform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import ar.edu.uns.cs.trabajosocialform.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.DataModel.Solicitante;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;
import ar.edu.uns.cs.trabajosocialform.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.configuracion.Datos_solicitante;

public class FormSolicitanteActivity extends AppCompatActivity {

    private Bundle bundle;
    private Formulario form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_solicitante);

        inicializarGui();

        Intent intent = getIntent();
        bundle = intent.getBundleExtra("CONFIG");
        form = (Formulario)intent.getSerializableExtra("FORM");
        Configuracion config = (Configuracion)bundle.getSerializable("CONFIG");

        ViewAdapter va = new ViewAdapter(config,this);
        va.adaptarSolicitante();

    }


    public void continuar(View view){
        Solicitante solicitante = tomarDatos();
        form.setSolicitante(solicitante);
        Intent intent = new Intent(this,FormApoderadoActivity.class);
        intent.putExtra("CONFIG",bundle);
        intent.putExtra("FORM",form);
        startActivity(intent);
        finish();
    }

    private void inicializarGui(){
        Utils utils = new Utils(this);
        utils.setValuesTvEt(R.string.nombres_solicitante,R.id.panel_nombres_solicitante);
        utils.setValuesTvEt(R.string.apellidos_solicitante,R.id.panel_apellidos_solicitante);
        utils.setValuesTvEt(R.string.cuil_solicitante,R.id.panel_cuil_solicitante);
        utils.setValuesTvEt(R.string.telefono_principal_solicitante,R.id.panel_telefono_principal_solicitante);
        utils.setValuesTvEt(R.string.otro_telefono_solicitante,R.id.panel_otro_telefono_solicitante);
    }

    private Solicitante tomarDatos(){
        Utils utils = new Utils(this);
        String nombre = utils.getDataTvEt(R.id.panel_nombres_solicitante);
        String apellido = utils.getDataTvEt(R.id.panel_apellidos_solicitante);
        String cuilS = utils.getDataTvEt(R.id.panel_cuil_solicitante);
        String telefono = utils.getDataTvEt(R.id.panel_telefono_principal_solicitante);
        String otroTelefono = utils.getDataTvEt(R.id.panel_otro_telefono_solicitante);

        Long cuil = utils.getLongFromString(cuilS);

        Solicitante solicitante = new Solicitante(nombre,apellido,cuil,telefono,otroTelefono);

        return solicitante;

    }






}
