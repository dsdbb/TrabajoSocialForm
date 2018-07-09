package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Familiar;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.Database.DatabaseAcces;
import ar.edu.uns.cs.trabajosocialform.Data.Database.StorageAccess;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.Presentation.ViewAdapter.DetailsViewAdapter;
import ar.edu.uns.cs.trabajosocialform.Presentation.activities.DetallesFamiliarActivity;
import ar.edu.uns.cs.trabajosocialform.Presentation.activities.DetallesFormActivity;
import ar.edu.uns.cs.trabajosocialform.Presentation.activities.MainActivity;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.RxBus;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.FamiliarDetallesItemClickedObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.NextButtonClickedObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.DetallesFamiliarView;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.DetallesFormView;

public class DetallesFormPresenter {

    private DetallesFormView view;
    private Formulario form;

    public DetallesFormPresenter(DetallesFormView view, Formulario form){
        this.view = view;
        this.form = form;
        DatabaseAcces db = new DatabaseAcces();
        Formulario completeForm = db.getCompleteForm(view.getActivity(),form);
        view.inicializarGui(completeForm);

        //Adapt view
        String jsonConfig = (new StorageAccess()).getConfigurationJson(view.getActivity());
        Configuracion config = (new Gson()).fromJson(jsonConfig,Configuracion.class);
        DetailsViewAdapter adapter = new DetailsViewAdapter(config,view.getActivity());
        adapter.adaptDetails();
    }

    public void onFamiliarDetallesItemClicked(int position){
        Intent intent = new Intent(view.getActivity(),DetallesFamiliarActivity.class);
        Familiar familiar = form.getFamiliares().get(position);
        intent.putExtra(MainActivity.KEY_FAMILIAR,familiar);
        view.getActivity().startActivity(intent);
    }

    public void register() {
        Activity activity = view.getActivity();
        if (activity==null){
            return;
        }

        RxBus.subscribe(activity, new FamiliarDetallesItemClickedObserver() {
            @Override
            public void onEvent(FamiliarDetallesItemClicked value) {
                onFamiliarDetallesItemClicked(value.getPosition());
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
