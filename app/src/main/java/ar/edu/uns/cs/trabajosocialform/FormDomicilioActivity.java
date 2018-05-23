package ar.edu.uns.cs.trabajosocialform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import ar.edu.uns.cs.trabajosocialform.DataModel.Domicilio;
import ar.edu.uns.cs.trabajosocialform.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;
import ar.edu.uns.cs.trabajosocialform.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.configuracion.Datos_domicilio;

public class FormDomicilioActivity extends AppCompatActivity {

    private Bundle bundle;
    private Formulario form;
    private boolean update;
    private Formulario updateForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_domicilio);

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
        va.adaptarDomicilio();
    }

    public void continuar(){
        Domicilio domicilio = tomarDatos();
        form.setDomicilio(domicilio);
        Intent intent = new Intent(this,FormGrupoFamiliarActivity.class);
        intent.putExtra("CONFIG",bundle);
        intent.putExtra("FORM",form);
        intent.putExtra("UPDATE", update);
        if(update){
            intent.putExtra("UPDATE_FORM", updateForm);
        }
        startActivity(intent);
    }


    private void inicializarGui(){
        Utils utils = new Utils(this);

        /*Agrego al layout general los campos de Domicilio*/
        utils.addContentToTemplate(R.layout.form_domicilio);

        /*Datos de los campos*/
        utils.setTitleValue(R.id.titulo_domicilio,R.string.titulo_datos_domicilio);
        utils.setValuesTvEt(R.string.calle,R.id.panel_calle);
        utils.setValuesTvEt(R.string.numero, R.id.panel_numero);
        utils.setValuesTvEt(R.string.manzana, R.id.panel_manzana);
        utils.setValuesTvEt(R.string.monoblock_torre, R.id.panel_monoblock_torre);
        utils.setValuesTvEt(R.string.piso, R.id.panel_piso);
        utils.setValuesTvEt(R.string.acc_int, R.id.panel_acc_int);
        utils.setValuesTvEt(R.string.casa_dpto, R.id.panel_casa_depto);
        utils.setValuesTvEt(R.string.entre_calle1,R.id.panel_entre_calles1);
        utils.setValuesTvEt(R.string.entre_calle2,R.id.panel_entre_calles2);
        utils.setValuesTvEt(R.string.barrio, R.id.panel_barrio);
        utils.setValuesTvEt(R.string.localidad, R.id.panel_localidad);
        utils.setValuesTvEt(R.string.delegacion, R.id.panel_delegacion);

        /*Boton*/
        utils.addNextButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continuar();
            }
        });

    }

    private Domicilio tomarDatos(){
        Utils utils = new Utils(this);
        String calle = utils.getDataTvEt(R.id.panel_calle);
        String numeroS = utils.getDataTvEt(R.id.panel_numero);
        String manzanaS = utils.getDataTvEt(R.id.panel_manzana);
        String monoblockTorreS = utils.getDataTvEt(R.id.panel_monoblock_torre);
        String pisoS = utils.getDataTvEt(R.id.panel_piso);
        String accIntS = utils.getDataTvEt(R.id.panel_acc_int);
        String casaDptoS = utils.getDataTvEt(R.id.panel_casa_depto);
        String entreCalle1 = utils.getDataTvEt(R.id.panel_entre_calles1);
        String entreCalle2 = utils.getDataTvEt(R.id.panel_entre_calles2);
        String barrio = utils.getDataTvEt(R.id.panel_barrio);
        String localidad = utils.getDataTvEt(R.id.panel_localidad);
        String delegacion = utils.getDataTvEt(R.id.panel_delegacion);

        Integer numero = utils.getIntegerFromString(numeroS);
        Integer manzana = utils.getIntegerFromString(manzanaS);
        Integer monoblockTorre = utils.getIntegerFromString(monoblockTorreS);
        Integer piso = utils.getIntegerFromString(pisoS);
        Integer accInt = utils.getIntegerFromString(accIntS);
        Integer casaDpto = utils.getIntegerFromString(casaDptoS);

       return new Domicilio(calle,numero,manzana,monoblockTorre,piso,accInt,casaDpto,entreCalle1,entreCalle2,barrio,delegacion,localidad);

    }

    private void rellenarCampos(){
        Utils utils = new Utils(this);

        Domicilio domicilio = updateForm.getDomicilio();
        utils.setValueToEditText(R.id.panel_calle, domicilio.getCalle());
        utils.setValueToEditText(R.id.panel_numero, domicilio.getNumero());
        utils.setValueToEditText(R.id.panel_manzana, domicilio.getManzana());
        utils.setValueToEditText(R.id.panel_monoblock_torre, domicilio.getMonoblock_torre());
        utils.setValueToEditText(R.id.panel_piso, domicilio.getPiso());
        utils.setValueToEditText(R.id.panel_acc_int, domicilio.getAcc_int());
        utils.setValueToEditText(R.id.panel_casa_depto, domicilio.getCasa_dpto());
        utils.setValueToEditText(R.id.panel_entre_calles1, domicilio.getEntre_calle1());
        utils.setValueToEditText(R.id.panel_entre_calles2, domicilio.getEntre_calle2());
        utils.setValueToEditText(R.id.panel_barrio, domicilio.getBarrio());
        utils.setValueToEditText(R.id.panel_localidad, domicilio.getLocalidad());
        utils.setValueToEditText(R.id.panel_delegacion, domicilio.getDelegacion());

    }
}
