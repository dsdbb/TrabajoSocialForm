package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter;

import android.app.Activity;
import android.content.Intent;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Domicilio;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.Presentation.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.Presentation.activities.FormGrupoFamiliarActivity;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.RxBus;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.NextButtonClickedObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.FormDomicilioView;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.FormGrupoFamiliarView;
import ar.edu.uns.cs.trabajosocialform.R;

public class FormDomicilioPresenter extends GeneralSectionPresenter {

    private FormDomicilioView view;

    public FormDomicilioPresenter(FormDomicilioView view, Formulario form, Configuracion configuration, Formulario updateForm) {
        this.view = view;
        this.form = form;
        this.configuration = configuration;
        this.updateForm = updateForm;

        //Compruebo si se requiere la vista de solicitante
        if (!configuration.getDatos_solicitante().required()) {
            //continue
            onNextButtonClicked();
            view.finish();
        } else {
            view.inicializarGui();
            adaptView();
        }
        //If there is a form to update, fill fields
        if (updateForm != null) {
            this.update = true;
            view.rellenarCampos(updateForm);
        }

    }

    public void onNextButtonClicked(){
        Domicilio domicilio = view.tomarDatos();
        if(view.validate(domicilio,configuration)){
            form.setDomicilio(domicilio);
            Intent intent = new Intent(view.getActivity(),FormGrupoFamiliarActivity.class);
            putExtras(intent);
            view.getActivity().startActivity(intent);
        }
        else{
            view.showMessage(R.string.datos_invalidos);
        }
    }

    public void adaptView(){
        ViewAdapter va = new ViewAdapter(configuration,view.getActivity());
        va.adaptarDomicilio();
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
