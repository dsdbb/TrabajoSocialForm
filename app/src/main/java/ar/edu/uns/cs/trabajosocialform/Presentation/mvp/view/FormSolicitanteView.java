package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Solicitante;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.RxBus;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.NextButtonClickedObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.PhotoButtonClickedObserver;
import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.Utils.FieldsValidator;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter.FormSolicitantePresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * This class is responsible of managing the UI of the Solicitante data and get the user data insertion
 * related with it.
 */

public class FormSolicitanteView extends ActivityView implements FormActions{


    private EditText nombreEt;
    private EditText apellidoEt;
    private EditText cuilEt;
    private EditText telefonoEt;
    private EditText otroTelefonoEt;
    private ImageView imageView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    private FormSolicitantePresenter presenter;

    private Bitmap foto;
    private static final int REQUEST_IMAGE_CAPTURE = 1;


    public FormSolicitanteView(AppCompatActivity activity){
        super(activity);
    }


    @Override
    @OnClick(R.id.siguiente_button)
    public void continuar(View view) {
        RxBus.post(new NextButtonClickedObserver.NextButtonClicked());
    }
/*

    /**
     * Inits an Activity to use the camera to take a photo.
     */
    @OnClick(R.id.plus_button)
    public void tomarFoto(View view) {
        RxBus.post(new PhotoButtonClickedObserver.PhotoButtonClicked());
    }


    @Override
    public void inicializarGui(){
        Utils utils = new Utils(getActivity());

        /*Add the fields associated to the Solicitante to the template*/
        utils.addContentToTemplate(R.layout.form_solicitante);

        ButterKnife.bind(this,getActivity());

        /*Toolbar title*/
        toolbar.setTitle(R.string.titulo_solicitante);

        /*Datos*/
        utils.setValuesTvEt(R.string.nombres_solicitante,R.id.panel_nombres_solicitante);
        utils.setValuesTvEt(R.string.apellidos_solicitante,R.id.panel_apellidos_solicitante);
        utils.setValuesTvEt(R.string.cuil_solicitante,R.id.panel_cuil_solicitante);
        utils.setValuesTvEt(R.string.telefono_principal_solicitante,R.id.panel_telefono_principal_solicitante);
        utils.setValuesTvEt(R.string.otro_telefono_solicitante,R.id.panel_otro_telefono_solicitante);


        nombreEt = getActivity().findViewById(R.id.panel_nombres_solicitante).findViewById(R.id.editText);
        apellidoEt = getActivity().findViewById(R.id.panel_apellidos_solicitante).findViewById(R.id.editText);
        cuilEt = getActivity().findViewById(R.id.panel_cuil_solicitante).findViewById(R.id.editText);
        telefonoEt = getActivity().findViewById(R.id.panel_telefono_principal_solicitante).findViewById(R.id.editText);
        otroTelefonoEt = getActivity().findViewById(R.id.panel_otro_telefono_solicitante).findViewById(R.id.editText);
        imageView = getActivity().findViewById(R.id.panel_agregar_imagen).findViewById(R.id.imageView);
        //Allow only numbers
        cuilEt.setInputType(InputType.TYPE_CLASS_NUMBER);


    }

    @Override
    public Solicitante tomarDatos(){
        Utils utils = new Utils(getActivity());
        String nombre = utils.getDataTvEt(R.id.panel_nombres_solicitante);
        String apellido = utils.getDataTvEt(R.id.panel_apellidos_solicitante);
        String cuilS = utils.getDataTvEt(R.id.panel_cuil_solicitante);
        String telefono = utils.getDataTvEt(R.id.panel_telefono_principal_solicitante);
        String otroTelefono = utils.getDataTvEt(R.id.panel_otro_telefono_solicitante);

        Long cuil = utils.getLongFromString(cuilS);

        String fotoString = utils.bitmapToString(foto);
        Solicitante solicitante = new Solicitante(nombre,apellido,cuil,telefono,otroTelefono,fotoString);

        return solicitante;

    }


    @Override
    public void rellenarCampos(Formulario updateForm){
        Utils utils = new Utils(getActivity());

        Solicitante solicitante = updateForm.getSolicitante();
        utils.setValueToEditText(R.id.panel_nombres_solicitante,solicitante.getNombres());
        utils.setValueToEditText(R.id.panel_apellidos_solicitante, solicitante.getApellidos());
        utils.setValueToEditText(R.id.panel_cuil_solicitante,solicitante.getCuil());
        utils.setValueToEditText(R.id.panel_telefono_principal_solicitante, solicitante.getTelefono());
        utils.setValueToEditText(R.id.panel_otro_telefono_solicitante, solicitante.getOtro_telefono());
    }

    @Override
    public boolean validate(Object obj,Configuracion config){
        boolean result = true;

        Solicitante solicitante = (Solicitante) obj;
        FieldsValidator validator = new FieldsValidator();

        if(!validator.validateShortString(solicitante.getNombres()) && config.getDatos_solicitante().isNombres_solicitante()){
            nombreEt.setBackgroundTintList(getActivity().getResources().getColorStateList(R.color.colorError));
            result = false;
        }
        else{
            nombreEt.setBackgroundTintList(getActivity().getResources().getColorStateList(R.color.colorMain));
        }
        if(!validator.validateShortString(solicitante.getApellidos()) && config.getDatos_solicitante().isApellidos_solicitante()){
            apellidoEt.setBackgroundTintList(getActivity().getResources().getColorStateList(R.color.colorError));
            result = false;
        }
        else{
            apellidoEt.setBackgroundTintList(getActivity().getResources().getColorStateList(R.color.colorMain));
        }
        if(!validator.validateCuil(solicitante.getCuil()) && config.getDatos_solicitante().isCuil_solicitante()){
            cuilEt.setBackgroundTintList(getActivity().getResources().getColorStateList(R.color.colorError));
            result = false;
        }
        else{
            cuilEt.setBackgroundTintList(getActivity().getResources().getColorStateList(R.color.colorMain));
        }

        return result;
    }

    public void finish() {
        getActivity().finish();
    }

    public void setPhoto(Bitmap photo) {
        foto = photo;
        Log.i("ImageBitmap",photo.toString());
        imageView.setImageBitmap(foto);
    }
}
