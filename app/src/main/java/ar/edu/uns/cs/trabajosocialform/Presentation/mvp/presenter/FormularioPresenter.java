package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.Database.StorageAccess;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.Presentation.activities.FormSolicitanteActivity;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.RxBus;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.NextButtonClickedObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.FormSolicitanteView;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.FormularioView;

public class FormularioPresenter extends GeneralSectionPresenter{

    private FormularioView view;
    private StorageAccess storageAccess;

    public FormularioPresenter(FormularioView view, Formulario updateForm){
        this.view = view;
        this.updateForm = updateForm;

        storageAccess = new StorageAccess();
        configuration = getConfigurationFileFromStorage();

        view.inicializarGui();

        /*If the action is an update the fields must be filled with corresponding information*/
        if(updateForm != null){
            this.update=true;
            view.rellenarCampos(updateForm);
        }
    }

    public void onNextButtonClicked(){
        form = view.tomarDatos();
        Intent intent = new Intent(view.getContext(),FormSolicitanteActivity.class);
        putExtras(intent);
        view.getActivity().startActivity(intent);
    }

    private Configuracion getConfigurationFileFromStorage(){
        return storageAccess.getConfigurationFile(view.getActivity());
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

    }

    public void unregister() {
        Activity activity = view.getActivity();

        if (activity==null){
            return;
        }
        RxBus.clear(activity);
    }




}
