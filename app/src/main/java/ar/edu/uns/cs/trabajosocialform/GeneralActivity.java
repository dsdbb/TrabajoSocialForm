package ar.edu.uns.cs.trabajosocialform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ar.edu.uns.cs.trabajosocialform.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.configuracion.Configuracion;

public abstract class GeneralActivity extends AppCompatActivity {

    protected Formulario form;
    protected Formulario updateForm;
    protected boolean update;
    protected Configuracion config;


    /**
     * Once the fields of this activity are completed the app continue with the next set of
     * fields to get information. This method prepare all the data that the next activity
     * needs to work properly and inits the Activity.
     */
    protected abstract void continuar();

    /**
     * Once the user finishes this method take the data that is in the fields and save it in a Solicitante object
     * @return the correspondent Object with the information completed by the user
     */
    protected abstract Object tomarDatos();

    /**
     * This method is responsible of completing the fields if the action made by the user in an update instead
     * of an insert
     */
    protected abstract void rellenarCampos();

    /**
     * Complete the generic fields with corresponding information
     */
    protected abstract void inicializarGui();

    protected abstract boolean validate(Object object);

    /**
     * Set the data that may need the next Activity
     * @param intent that is going to start the next activity and that holds the data
     */
    protected void putExtras(Intent intent){
        intent.putExtra("CONFIG",config);
        intent.putExtra("FORM",form);
        intent.putExtra("UPDATE",update);
        if(update){
            intent.putExtra("UPDATE_FORM",updateForm);
        }
    }
}
