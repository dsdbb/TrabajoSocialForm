package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.util.Log;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Solicitante;
import ar.edu.uns.cs.trabajosocialform.Presentation.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.Presentation.activities.FormApoderadoAcitivity;
import ar.edu.uns.cs.trabajosocialform.Presentation.activities.FormSolicitanteActivity;
import ar.edu.uns.cs.trabajosocialform.Presentation.activities.MainActivity;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.RxBus;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.NextButtonClickedObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.OnActivityResultObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.PhotoButtonClickedObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.FormApoderadoView;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.FormSolicitanteView;
import ar.edu.uns.cs.trabajosocialform.R;

public class FormSolicitantePresenter extends GeneralSectionPresenter {

    private FormSolicitanteView view;


    public FormSolicitantePresenter(FormSolicitanteView view, Formulario form, Configuracion config, Formulario updateForm){
        this.view = view;
        this.form = form;
        this.configuration = config;
        this.updateForm = updateForm;

        //Compruebo si se requiere la vista de solicitante
        if(!configuration.getDatos_solicitante().required()){
            //continue
            onNextButtonClicked();
            view.finish();
        }
        else{
            view.inicializarGui();
            adaptView();
        }
        //If there is a form to update, fill fields
        if(updateForm!=null){
            view.rellenarCampos(updateForm);
        }
    }

    public void adaptView(){
        ViewAdapter va = new ViewAdapter(configuration, view.getActivity());
        va.adaptarSolicitante();
    }

    public void onNextButtonClicked(){
        Solicitante solicitante = view.tomarDatos();
        if(view.validate(solicitante,configuration)){
            form.setSolicitante(solicitante);
            Intent intent = new Intent(view.getActivity(),FormApoderadoAcitivity.class);
            putExtras(intent);
            view.getActivity().startActivity(intent);
        }
        else{
            view.showMessage(R.string.datos_invalidos);
        }
    }

    private void onPhotoButtonClicked(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(view.getActivity().getPackageManager()) != null) {
            view.getActivity().startActivityForResult(takePictureIntent, FormSolicitanteActivity.CAMERA_PHOTO);
        }
    }

    private void onPhotoActivityResult(Bitmap photo){
        view.setPhoto(photo);
    }

    public void register() {
        Activity activity = view.getActivity();
        if (activity==null){
            return;
        }

        RxBus.subscribe(activity, new NextButtonClickedObserver() {
            @Override
            public void onEvent(NextButtonClicked value) {
                onNextButtonClicked();
            }
        });

        RxBus.subscribe(activity, new PhotoButtonClickedObserver() {
            @Override
            public void onEvent(PhotoButtonClicked value) {
                onPhotoButtonClicked();
            }
        });

        RxBus.subscribe(activity, new OnActivityResultObserver() {
            @Override
            public void onEvent(OnActivityResult value) {
                onPhotoActivityResult(value.getPhoto());
            }
        });
    }

    public void unregister() {
        Activity activity = view.getActivity();

        if (activity==null){
            return;
        }
        RxBus.clear(activity);
    }

}
