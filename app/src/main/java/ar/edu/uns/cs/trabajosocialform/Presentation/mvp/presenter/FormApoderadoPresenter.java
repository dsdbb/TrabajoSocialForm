package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter;

import android.app.Activity;
import android.content.Intent;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Apoderado;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.Presentation.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.Presentation.activities.FormDomicilioActivity;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.RxBus;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.NextButtonClickedObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.OnActivityResultObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.PhotoButtonClickedObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.FormActions;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.FormApoderadoView;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.FormDomicilioView;
import ar.edu.uns.cs.trabajosocialform.R;

public class FormApoderadoPresenter extends GeneralSectionPresenter{

    private FormApoderadoView view;

    public FormApoderadoPresenter(FormApoderadoView view, Formulario form, Configuracion configuration, Formulario updateForm){
        this.view = view;
        this.form = form;
        this.configuration = configuration;
        this.updateForm = updateForm;

        if (!configuration.getDatos_apoderado().required()) {
            onNextButtonClicked();
            view.finish();
        } else {
            view.inicializarGui();
            adaptView();
        }
        if(updateForm!=null){
            view.rellenarCampos(updateForm);
        }
    }

    public void onNextButtonClicked(){
        Apoderado apoderado = view.tomarDatos();
        if(view.validate(apoderado,configuration)){
            form.setApoderado(apoderado);
            Intent intent = new Intent(view.getActivity(),FormDomicilioActivity.class);
            putExtras(intent);
            view.getActivity().startActivity(intent);
        }
        else{
            view.showMessage(R.string.datos_invalidos);
        }

    }

    public void adaptView(){
        ViewAdapter va = new ViewAdapter(configuration, view.getActivity());
        va.adaptarApoderado();
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
