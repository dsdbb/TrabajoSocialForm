package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter;

import android.app.Activity;

import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;

public abstract class Presenter {


    public abstract void adaptView(Configuracion config, Activity act);

}
