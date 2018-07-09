package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter;

import android.app.Activity;
import android.content.Intent;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.CaracteristicasVivienda;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.Presentation.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.Presentation.activities.FormInfraestructuraBarrialActivity;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.RxBus;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.NextButtonClickedObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.FormCaracteristicasViviendaView;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.FormInfraestructuraBarrialView;
import ar.edu.uns.cs.trabajosocialform.R;

public class FormCaracteristicasViviendaPresenter extends GeneralSectionPresenter{

    private FormCaracteristicasViviendaView view;

    public FormCaracteristicasViviendaPresenter(FormCaracteristicasViviendaView view, Formulario form, Configuracion configuration, Formulario updateForm){
        this.view = view;
        this.form = form;
        this.configuration = configuration;
        this.updateForm = updateForm;

        //Compruebo si se requiere la vista de solicitante
        if (!configuration.getDatos_caracteristicas_vivienda().required()) {
            //continue
            skipSection();
            view.finish();
        } else {
            view.inicializarGui();
            adaptView();
            //If there is a form to update, fill fields
            if (updateForm != null) {
                this.update = true;
                view.rellenarCampos(updateForm);
            }
        }


    }

    public void skipSection(){
        Intent intent = new Intent(view.getActivity(),FormInfraestructuraBarrialActivity.class);
        putExtras(intent);
        view.getActivity().startActivity(intent);
    }


    public void onNextButtonClicked(){
        CaracteristicasVivienda caracteristicas = view.tomarDatos();
        if(view.validate(caracteristicas,configuration)){
            form.setCaracteristicasVivienda(caracteristicas);
            Intent intent = new Intent(view.getActivity(),FormInfraestructuraBarrialActivity.class);
            putExtras(intent);
            view.getActivity().startActivity(intent);
        }
        else{
            view.showMessage(R.string.datos_invalidos);
        }

    }

    public void adaptView(){
        ViewAdapter va = new ViewAdapter(configuration, view.getActivity());
        va.adaptarCaracteristicas_vivienda();
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
