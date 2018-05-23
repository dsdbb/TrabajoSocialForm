package ar.edu.uns.cs.trabajosocialform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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
    private Formulario updateForm;
    private boolean update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_solicitante);

        inicializarGui();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Intent intent = getIntent();
        bundle = intent.getBundleExtra("CONFIG");
        form = (Formulario)intent.getSerializableExtra("FORM");
        Configuracion config = (Configuracion)bundle.getSerializable("CONFIG");

        /*Chequeo si es un update y en ese caso relleno los campos*/
        update = getIntent().getBooleanExtra("UPDATE",false);
        if(update){
            updateForm = (Formulario)getIntent().getSerializableExtra("UPDATE_FORM");
            rellenarCampos();
        }

        ViewAdapter va = new ViewAdapter(config,this);
        va.adaptarSolicitante();

    }


    public void continuar(){
        Solicitante solicitante = tomarDatos();
        form.setSolicitante(solicitante);
        Intent intent = new Intent(this,FormApoderadoActivity.class);
        intent.putExtra("CONFIG",bundle);
        intent.putExtra("FORM",form);
        intent.putExtra("UPDATE",update);
        if(update){
            intent.putExtra("UPDATE_FORM",updateForm);
        }
        startActivity(intent);

    }

    private void inicializarGui(){
        Utils utils = new Utils(this);
        /*Agrego contenido al template general*/
        utils.addContentToTemplate(R.layout.form_solicitante);

        /*Datos*/
        utils.setTitleValue(R.id.titulo_solicitante,R.string.titulo_solicitante);
        utils.setValuesTvEt(R.string.nombres_solicitante,R.id.panel_nombres_solicitante);
        utils.setValuesTvEt(R.string.apellidos_solicitante,R.id.panel_apellidos_solicitante);
        utils.setValuesTvEt(R.string.cuil_solicitante,R.id.panel_cuil_solicitante);
        utils.setValuesTvEt(R.string.telefono_principal_solicitante,R.id.panel_telefono_principal_solicitante);
        utils.setValuesTvEt(R.string.otro_telefono_solicitante,R.id.panel_otro_telefono_solicitante);

        utils.addNextButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continuar();
            }
        });
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

    private void rellenarCampos(){
        Utils utils = new Utils(this);

        Solicitante solicitante = updateForm.getSolicitante();
        utils.setValueToEditText(R.id.panel_nombres_solicitante,solicitante.getNombres());
        utils.setValueToEditText(R.id.panel_apellidos_solicitante, solicitante.getApellidos());
        utils.setValueToEditText(R.id.panel_cuil_solicitante,solicitante.getCuil());
        utils.setValueToEditText(R.id.panel_telefono_principal_solicitante, solicitante.getTelefono());
        utils.setValueToEditText(R.id.panel_otro_telefono_solicitante, solicitante.getOtro_telefono());
    }





}
