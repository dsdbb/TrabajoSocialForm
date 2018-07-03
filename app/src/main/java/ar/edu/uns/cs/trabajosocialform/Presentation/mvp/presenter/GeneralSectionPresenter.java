package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter;

import android.content.Intent;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;

public abstract class GeneralSectionPresenter {

    protected Formulario form;
    protected Formulario updateForm;
    protected boolean update;
    protected Configuracion configuracion;

    /**
     * Set the data that may need the next Activity
     * @param intent that is going to start the next activity and that holds the data
     */
    protected void putExtras(Intent intent) {
        intent.putExtra("CONFIG", configuracion);
        intent.putExtra("FORM", form);
        intent.putExtra("UPDATE", update);
        if (update) {
            intent.putExtra("UPDATE_FORM", updateForm);
        }
    }
}
