package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter;

import android.app.Activity;
import android.content.Intent;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.Presentation.activities.MainActivity;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.RxBus;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.ActivityView;

public abstract class GeneralSectionPresenter {

    protected Formulario form;
    protected Formulario updateForm;
    protected boolean update;
    protected Configuracion configuration;

    /**
     * Set the data that may need the next Activity
     * @param intent that is going to start the next activity and that holds the data
     */
    protected void putExtras(Intent intent) {
        intent.putExtra(MainActivity.KEY_CONFIGURATION_FILE, configuration);
        intent.putExtra(MainActivity.KEY_ACTUAL_FORM, form);
        intent.putExtra(MainActivity.KEY_UPDATE, update);
        if (update) {
            intent.putExtra(MainActivity.KEY_UPDATE_FORM, updateForm);
        }
    }


}
