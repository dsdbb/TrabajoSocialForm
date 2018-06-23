package ar.edu.uns.cs.trabajosocialform.mvp.presenter;

import android.app.Activity;

import ar.edu.uns.cs.trabajosocialform.Utils.Validator;
import ar.edu.uns.cs.trabajosocialform.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.mvp.view.FormSolicitanteActivity;

public class solicitantePresenter extends Presenter {

    @Override
    public void adaptView(Configuracion config, Activity act){
        ViewAdapter va = new ViewAdapter(config, act);
        va.adaptarSolicitante();
    }



}
