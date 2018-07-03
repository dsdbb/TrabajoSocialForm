package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Domicilio;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.Utils.FieldsValidator;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;
import ar.edu.uns.cs.trabajosocialform.Presentation.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormDomicilioView extends GeneralActivity {


    private EditText calleEt;
    private EditText numeroEt;
    private EditText localidadEt;
    private EditText delegacionEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_domicilio);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Intent intent = getIntent();
        form = (Formulario)intent.getSerializableExtra("FORM");
        config = (Configuracion)intent.getSerializableExtra("CONFIG");

        if(!config.getDatos_domicilio().required()){
            continuar(null);
        }
        else{
            inicializarGui();
            /*Bind Activity to use ButterKnife facilities*/
            ButterKnife.bind(this);

            calleEt = findViewById(R.id.panel_calle).findViewById(R.id.editText);
            numeroEt = findViewById(R.id.panel_numero).findViewById(R.id.editText);
            localidadEt = findViewById(R.id.panel_localidad).findViewById(R.id.editText);
            delegacionEt = findViewById(R.id.panel_delegacion).findViewById(R.id.editText);

            /*Chequeo si es un update y en ese caso relleno los campos*/
            update = getIntent().getBooleanExtra("UPDATE",false);
            if(update){
                updateForm = (Formulario)getIntent().getSerializableExtra("UPDATE_FORM");
                rellenarCampos();
            }

            ViewAdapter va = new ViewAdapter(config,this);
            va.adaptarDomicilio();
        }

    }

    @Override
    @OnClick(R.id.siguiente_button)
    public void continuar(View view){
        Domicilio domicilio = tomarDatos();

        if(validate(domicilio)){
            form.setDomicilio(domicilio);
            Intent intent = new Intent(this,FormGrupoFamiliarView.class);
            intent.putExtra("CONFIG",config);
            intent.putExtra("FORM",form);
            intent.putExtra("UPDATE", update);
            if(update){
                intent.putExtra("UPDATE_FORM", updateForm);
            }
            startActivity(intent);

            if(!config.getDatos_domicilio().required()){
                finish();
            }
        }
        else{
            Toast.makeText( this,R.string.datos_invalidos, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void inicializarGui(){
        Utils utils = new Utils(this);

        /*Agrego al layout general los campos de Domicilio*/
        utils.addContentToTemplate(R.layout.form_domicilio);

        /*Titulo toolbar*/
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.titulo_datos_domicilio);

        /*Datos de los campos*/
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
        /*utils.addNextButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continuar();
            }
        });*/

    }

    @Override
    protected Domicilio tomarDatos(){
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

    @Override
    protected void rellenarCampos(){
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

    @Override
    protected boolean validate(Object obj){
        boolean result = true;

        FieldsValidator validator = new FieldsValidator();
        Domicilio domicilio = (Domicilio)obj;

        if(!validator.validateLongString(domicilio.getCalle()) && config.getDatos_domicilio().isCalle()){
            calleEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorError));
            result = false;
        }
        else{
            calleEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorMain));
        }
        if(!validator.validateNumber(domicilio.getNumero()) && config.getDatos_domicilio().isNumero()){
            numeroEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorError));
            result = false;
        }
        else{
            numeroEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorMain));
        }
        if(!validator.validateShortString(domicilio.getLocalidad()) && config.getDatos_domicilio().isLocalidad()){
            localidadEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorError));
            result = false;
        }
        else{
            localidadEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorMain));
        }
        if(!validator.validateShortString(domicilio.getDelegacion()) && config.getDatos_domicilio().isDelegacion()){
            delegacionEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorError));
            result = false;
        }
        else{
            delegacionEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorMain));
        }



        return result;
    }
}
