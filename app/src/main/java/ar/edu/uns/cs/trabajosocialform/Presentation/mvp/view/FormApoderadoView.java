package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Apoderado;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.RxBus;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.NextButtonClickedObserver;
import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.Utils.FieldsValidator;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;
import ar.edu.uns.cs.trabajosocialform.Presentation.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Datos_apoderado;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormApoderadoView extends ActivityView implements FormActions{


    private EditText nombreEt;
    private EditText apellidoEt;
    private EditText cuilEt;
    private EditText fechaNacimientoEt;
    @BindView(R.id.toolbar)Toolbar toolbar;

    public FormApoderadoView(AppCompatActivity activity){
        super(activity);
        ButterKnife.bind(this,getActivity());
    }


    @Override
    @OnClick(R.id.siguiente_button)
    public void continuar(View view) {
        RxBus.post(new NextButtonClickedObserver.NextButtonClicked());
    }


    @Override
    public Apoderado tomarDatos(){
        Utils utils = new Utils(getActivity());

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


    public void inicializarGui(){
        Utils utils = new Utils(getActivity());
        /*Agrego formulario al template*/
        utils.addContentToTemplate(R.layout.form_apoderado);

        /*Titulo toolbar*/
        toolbar.setTitle(R.string.titulo_apoderado);

        /*Datos*/
        utils.setValuesTvEt(R.string.nombres_apoderado,R.id.panel_nombres_apoderado);
        utils.setValuesTvEt(R.string.apellidos_apoderado,R.id.panel_apellidos_apoderado);
        utils.setValuesTvEt(R.string.cuil_apoderado,R.id.panel_cuil_apoderado);
        utils.setValuesTvEt(R.string.telefono_apoderado,R.id.panel_telefono_principal_apoderado);
        utils.setValuesTvEt(R.string.fecha_nac_apoderado,R.id.panel_fecha_nacimiento_apoderado);
        utils.setValuesTvEt(R.string.motivos_de_poder, R.id.panel_motivos_poder_apoderado);

        utils.addDateListener(R.id.panel_fecha_nacimiento_apoderado);

        nombreEt = getActivity().findViewById(R.id.panel_nombres_apoderado).findViewById(R.id.editText);
        apellidoEt = getActivity().findViewById(R.id.panel_apellidos_apoderado).findViewById(R.id.editText);
        cuilEt = getActivity().findViewById(R.id.panel_cuil_apoderado).findViewById(R.id.editText);
        fechaNacimientoEt = getActivity().findViewById(R.id.panel_fecha_nacimiento_apoderado).findViewById(R.id.editText);
        //Allow only numbers
        cuilEt.setInputType(InputType.TYPE_CLASS_NUMBER);
    }


    public void rellenarCampos(Formulario updateForm){
        Utils utils = new Utils(getActivity());

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
    public boolean validate(Object obj,Configuracion config){

        boolean result = true;
        Apoderado apoderado = (Apoderado)obj;
        FieldsValidator validator = new FieldsValidator();

        if(!validator.validateShortString(apoderado.getNombres()) && config.getDatos_apoderado().isNombres_apoderado()){
            nombreEt.setBackgroundTintList(getActivity().getResources().getColorStateList(R.color.colorError));
            result = false;
        }
        else{
            nombreEt.setBackgroundTintList(getActivity().getResources().getColorStateList(R.color.colorMain));
        }
        if(!validator.validateShortString(apoderado.getApellidos()) && config.getDatos_apoderado().isApellidos_apoderado()){
            apellidoEt.setBackgroundTintList(getActivity().getResources().getColorStateList(R.color.colorError));
            result = false;
        }
        else{
            apellidoEt.setBackgroundTintList(getActivity().getResources().getColorStateList(R.color.colorMain));

        }
        if(!validator.validateCuil(apoderado.getCuil()) && config.getDatos_apoderado().isCuil_apoderado()){
            cuilEt.setBackgroundTintList(getActivity().getResources().getColorStateList(R.color.colorError));
            result = false;
        }
        else{
            cuilEt.setBackgroundTintList(getActivity().getResources().getColorStateList(R.color.colorMain));
        }
        if(!validator.validateDate(apoderado.getFecha_nacimiento()) && config.getDatos_apoderado().isFecha_nac_apoderado()){
            fechaNacimientoEt.setBackgroundTintList(getActivity().getResources().getColorStateList(R.color.colorError));
            result = false;
        }
        else{
            fechaNacimientoEt.setBackgroundTintList(getActivity().getResources().getColorStateList(R.color.colorMain));
        }

        return result;
    }

    public void finish() {
        getActivity().finish();
    }
}
