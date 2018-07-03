package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter;

import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.Database.DatabaseAcces;
import ar.edu.uns.cs.trabajosocialform.Data.Database.StorageAccess;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.Presentation.ViewAdapter.DetailsViewAdapter;
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
}
