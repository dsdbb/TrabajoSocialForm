package ar.edu.uns.cs.trabajosocialform.Database;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import ar.edu.uns.cs.trabajosocialform.Daos.ApoderadoDao;
import ar.edu.uns.cs.trabajosocialform.Daos.DomicilioDao;
import ar.edu.uns.cs.trabajosocialform.Daos.SolicitanteDao;
import ar.edu.uns.cs.trabajosocialform.DataModel.Domicilio;
import ar.edu.uns.cs.trabajosocialform.DataModel.Formulario;

public class DatabaseSave {

    public void saveInDatabase(Activity act, final Formulario form){
        final Context context = act;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("Estado: ","Ejecutando el run");
                AppDatabase mDb = AppDatabase.getAppDatabase(context); // Get an Instance of Database class
                /*Guardo cada seccion del formulario en su correspondiente tabla para luego guardar el formuarlio con todos
                * los respectivos ID*/

                /*Solicitante*/
                SolicitanteDao solDao = mDb.solicitanteDao();
                solDao.insert(form.getSolicitante());
                /*Apoderado*/
                ApoderadoDao apoderadoDao = mDb.apoderadoDao();
                apoderadoDao.insert(form.getApoderado());
                /*Domicilio*/
                DomicilioDao domicilioDao = mDb.domicilioDao();
                domicilioDao.insert(form.getDomicilio());
            }
        }) .start();
    }
}
