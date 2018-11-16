package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Domicilio;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.RxBus;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.NextButtonClickedObserver;
import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.Utils.FieldsValidator;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;
import ar.edu.uns.cs.trabajosocialform.Presentation.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormDomicilioView extends ActivityView implements FormActions {


    private EditText numeroEt;
    private EditText localidadEt;
    private EditText delegacionEt;
    private EditText manzanaEt;
    private EditText monoblockTorreEt;
    private EditText pisoEt;
    private EditText accIntEt;
    private EditText casaDptoEt;
    private EditText calleEt;
    @BindView(R.id.toolbar)Toolbar toolbar;


    public FormDomicilioView(AppCompatActivity activity){
        super(activity);
        ButterKnife.bind(this,activity);
    }


    @Override
    @OnClick(R.id.siguiente_button)
    public void continuar(View view) {
        RxBus.post(new NextButtonClickedObserver.NextButtonClicked());
    }

    @Override
    public void inicializarGui(){
        Utils utils = new Utils(getActivity());

        /*Agrego al layout general los campos de Domicilio*/
        utils.addContentToTemplate(R.layout.form_domicilio);

        /*Titulo toolbar*/
        toolbar.setTitle(R.string.titulo_datos_domicilio);

        /*Datos de los campos*/
        utils.setValuesTvSpinner(R.array.calles,R.string.calle,R.id.panel_calle);
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

        //calleEt = getActivity().findViewById(R.id.panel_calle).findViewById(R.id.spinner);
        numeroEt = getActivity().findViewById(R.id.panel_numero).findViewById(R.id.editText);
        localidadEt = getActivity().findViewById(R.id.panel_localidad).findViewById(R.id.editText);
        delegacionEt = getActivity().findViewById(R.id.panel_delegacion).findViewById(R.id.editText);
        manzanaEt = getActivity().findViewById(R.id.panel_manzana).findViewById(R.id.editText);
        monoblockTorreEt = getActivity().findViewById(R.id.panel_monoblock_torre).findViewById(R.id.editText);
        pisoEt = getActivity().findViewById(R.id.panel_piso).findViewById(R.id.editText);
        accIntEt = getActivity().findViewById(R.id.panel_acc_int).findViewById(R.id.editText);
        casaDptoEt = getActivity().findViewById(R.id.panel_casa_depto).findViewById(R.id.editText);

        numeroEt.setInputType(InputType.TYPE_CLASS_NUMBER);
        manzanaEt.setInputType(InputType.TYPE_CLASS_NUMBER);
        monoblockTorreEt.setInputType(InputType.TYPE_CLASS_NUMBER);
        pisoEt.setInputType(InputType.TYPE_CLASS_NUMBER);
        accIntEt.setInputType(InputType.TYPE_CLASS_NUMBER);
        casaDptoEt.setInputType(InputType.TYPE_CLASS_NUMBER);

    }

    @Override
    public Domicilio tomarDatos(){
        Utils utils = new Utils(getActivity());
        String calle = utils.getDataTvSpinner(R.id.panel_calle);
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
    public void rellenarCampos(Formulario updateForm){
        Utils utils = new Utils(getActivity());

        Domicilio domicilio = updateForm.getDomicilio();
        utils.setValueToSpinner(R.id.panel_calle,R.array.calles,domicilio.getCalle());
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
    public boolean validate(Object obj, Configuracion config){
        boolean result = true;

        FieldsValidator validator = new FieldsValidator();
        Domicilio domicilio = (Domicilio)obj;

        /*if(!validator.validateLongString(domicilio.getCalle()) && config.getDatos_domicilio().isCalle()){
            //calleEt.setBackgroundTintList(getActivity().getResources().getColorStateList(R.color.colorError));
            calleEt.getBackground().mutate().setColorFilter(getActivity().getResources().getColor(R.color.colorError), PorterDuff.Mode.SRC_ATOP);
            result = false;
        }
        else{
            calleEt.getBackground().mutate().setColorFilter(getActivity().getResources().getColor(R.color.colorMain), PorterDuff.Mode.SRC_ATOP);
        }*/
        if(!validator.validateNumber(domicilio.getNumero()) && config.getDatos_domicilio().isNumero()){
            numeroEt.getBackground().mutate().setColorFilter(getActivity().getResources().getColor(R.color.colorError), PorterDuff.Mode.SRC_ATOP);
            result = false;
        }
        else{
            numeroEt.getBackground().mutate().setColorFilter(getActivity().getResources().getColor(R.color.colorMain), PorterDuff.Mode.SRC_ATOP);
        }
        /*if(!validator.validateShortString(domicilio.getLocalidad()) && config.getDatos_domicilio().isLocalidad()){
            localidadEt.getBackground().mutate().setColorFilter(getActivity().getResources().getColor(R.color.colorError), PorterDuff.Mode.SRC_ATOP);
            result = false;
        }
        else{
            localidadEt.getBackground().mutate().setColorFilter(getActivity().getResources().getColor(R.color.colorMain), PorterDuff.Mode.SRC_ATOP);
        }
        if(!validator.validateShortString(domicilio.getDelegacion()) && config.getDatos_domicilio().isDelegacion()){
            delegacionEt.getBackground().mutate().setColorFilter(getActivity().getResources().getColor(R.color.colorError), PorterDuff.Mode.SRC_ATOP);
            result = false;
        }
        else{
            delegacionEt.getBackground().mutate().setColorFilter(getActivity().getResources().getColor(R.color.colorMain), PorterDuff.Mode.SRC_ATOP);
        }*/



        return result;
    }

}
