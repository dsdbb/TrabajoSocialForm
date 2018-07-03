package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter;

import android.app.Activity;

import ar.edu.uns.cs.trabajosocialform.Presentation.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;

public class solicitantePresenter extends Presenter {

    @Override
    public void adaptView(Configuracion config, Activity act){
        ViewAdapter va = new ViewAdapter(config, act);
        va.adaptarSolicitante();
    }



}
