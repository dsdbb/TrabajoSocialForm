package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view;

import android.view.View;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;

public interface FormActions {

    /**
     * Once the fields of this activity are completed the app continue with the next set of
     * fields to get information. This method prepare all the data that the next activity
     * needs to work properly and inits the Activity.
     */
     void continuar(View view);
    /**
     * Once the user finishes this method take the data that is in the fields and save it in a Solicitante object
     * @return the correspondent Object with the information completed by the user
     */
      Object tomarDatos();

    /**
     * This method is responsible of completing the fields if the action made by the user in an update instead
     * of an insert
     */
      void rellenarCampos(Formulario form);

    /**
     * Complete the generic fields with corresponding information
     */
      void inicializarGui();

    /**
     * Validate fields of the form section
     * @param object Object related to section with completed fields
     * @return boolean representing success or fail of validation
     */
      boolean validate(Object object, Configuracion config);
}
