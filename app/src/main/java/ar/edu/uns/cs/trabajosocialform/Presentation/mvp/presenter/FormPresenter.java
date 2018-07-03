package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter;

import android.app.Activity;

import com.google.gson.Gson;

import ar.edu.uns.cs.trabajosocialform.Data.Database.StorageAccess;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;

public class FormPresenter {

    private Activity act;
    private StorageAccess model;

    public FormPresenter(Activity act){
        this.act = act;
        model = new StorageAccess();
    }

    /**
     * This method gets the configuration file saved in local storage to adapt the user interface according to it
     * @return a Configuracion object with the values of the configuration file
     */
    public Configuracion getConfigurationFile(){

        /*Get Configuracion in Json format and get the Configuracion object from it*/
        String json = model.getConfigurationJson(act);
        Configuracion config = (new Gson()).fromJson(json, Configuracion.class);
        return config;
    }

}
