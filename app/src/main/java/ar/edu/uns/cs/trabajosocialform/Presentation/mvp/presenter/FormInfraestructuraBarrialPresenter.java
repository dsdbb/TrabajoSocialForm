package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter;

import android.app.Activity;
import android.content.Intent;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.InfraestructuraBarrial;
import ar.edu.uns.cs.trabajosocialform.Data.Database.DatabaseAcces;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.Presentation.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.Presentation.activities.MainActivity;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.RxBus;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.NextButtonClickedObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.FormInfraestructuraBarrialView;
import ar.edu.uns.cs.trabajosocialform.R;
import io.reactivex.observers.DisposableObserver;

public class FormInfraestructuraBarrialPresenter extends GeneralSectionPresenter{

    private FormInfraestructuraBarrialView view;

    public FormInfraestructuraBarrialPresenter(FormInfraestructuraBarrialView view, Formulario form, Configuracion configuration, Formulario updateForm) {
        this.view = view;
        this.form = form;
        this.configuration = configuration;
        this.updateForm = updateForm;

        //Compruebo si se requiere la vista de solicitante
        if (!configuration.getDatos_infraestructura_barrial().required()) {
            //continue
            skipSectionAndSave();
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

    public void skipSectionAndSave(){
        DatabaseAcces db = new DatabaseAcces();
        if(update){
            db.updateDatabase(view.getActivity(), form, updateForm, new DisposableObserver<Boolean>() {
                @Override
                public void onNext(Boolean aBoolean) {
                    if(aBoolean) view.showMessage(R.string.update_correcto);
                }

                @Override
                public void onError(Throwable e) {
                    view.showMessage(R.string.error_update);
                }

                @Override
                public void onComplete() {

                }
            });
        }
        else{
            db.saveInDatabase(view.getActivity(), form, new DisposableObserver<Boolean>() {
                @Override
                public void onNext(Boolean aBoolean) {
                    if(aBoolean) view.showMessage(R.string.insert_correcto);
                }

                @Override
                public void onError(Throwable e) {
                    view.showMessage(R.string.error_insert);
                }

                @Override
                public void onComplete() {

                }
            });
        }

        Intent intent = new Intent(view.getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        view.getActivity().startActivity(intent);
    }

    public void onNextButtonClicked(){
        InfraestructuraBarrial infraestructura = view.tomarDatos();
        form.setInfraestructuraBarrial(infraestructura);
        skipSectionAndSave();
    }

    public void adaptView(){
        ViewAdapter va = new ViewAdapter(configuration,view.getActivity());
        va.adaptarInfraestructura_barrial();
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
