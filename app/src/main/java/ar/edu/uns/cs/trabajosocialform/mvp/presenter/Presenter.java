package ar.edu.uns.cs.trabajosocialform.mvp.presenter;

import android.app.Activity;

import ar.edu.uns.cs.trabajosocialform.configuracion.Configuracion;

public abstract class Presenter {


    public abstract void adaptView(Configuracion config, Activity act);

}
