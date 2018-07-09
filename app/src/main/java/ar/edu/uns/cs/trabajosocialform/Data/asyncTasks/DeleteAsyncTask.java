package ar.edu.uns.cs.trabajosocialform.Data.asyncTasks;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.List;

import ar.edu.uns.cs.trabajosocialform.Data.Daos.ApoderadoDao;
import ar.edu.uns.cs.trabajosocialform.Data.Daos.CaracteristicasViviendaDao;
import ar.edu.uns.cs.trabajosocialform.Data.Daos.DomicilioDao;
import ar.edu.uns.cs.trabajosocialform.Data.Daos.FamiliarDao;
import ar.edu.uns.cs.trabajosocialform.Data.Daos.FormularioDao;
import ar.edu.uns.cs.trabajosocialform.Data.Daos.FormularioFamiliarDao;
import ar.edu.uns.cs.trabajosocialform.Data.Daos.InfraestructuraBarrialDao;
import ar.edu.uns.cs.trabajosocialform.Data.Daos.IngresoDao;
import ar.edu.uns.cs.trabajosocialform.Data.Daos.OcupacionDao;
import ar.edu.uns.cs.trabajosocialform.Data.Daos.SaludDao;
import ar.edu.uns.cs.trabajosocialform.Data.Daos.SituacionHabitacionalDao;
import ar.edu.uns.cs.trabajosocialform.Data.Daos.SolicitanteDao;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Familiar;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.Database.AppDatabase;
import ar.edu.uns.cs.trabajosocialform.Data.Transactions.Transaction;
import ar.edu.uns.cs.trabajosocialform.Data.Transactions.TransactionDao;
import ar.edu.uns.cs.trabajosocialform.Data.Transactions.TransactionOptions;
import io.reactivex.observers.DisposableObserver;

public class DeleteAsyncTask extends AsyncTask<Object,Void,DisposableObserver<Boolean>> {


    @Override
    protected DisposableObserver<Boolean> doInBackground(Object... objects) {
        Activity act = (Activity) objects[0];
        Formulario form = (Formulario)objects[1];
        DisposableObserver<Boolean> observer = (DisposableObserver<Boolean>)objects[2];

        AppDatabase mDb = AppDatabase.getAppDatabase(act);// Get an Instance of Database class

        /*Delete joins between Fomulario and Familiar*/
        FormularioFamiliarDao formularioFamiliarDao = mDb.formularioFamiliarDao();
        List<Integer> familiaresId = formularioFamiliarDao.getFamiliares(form.getId());
        formularioFamiliarDao.deleteJoinsWithForm(form.getId());

        FamiliarDao familiarDao = mDb.familiarDao();
        /*Delete Familiar objects*/
        for (int i = 0; i < familiaresId.size(); i++) {
            Familiar familiar = familiarDao.getFamiliar(familiaresId.get(i));

            OcupacionDao ocupacionDao = mDb.ocupacionDao();
            ocupacionDao.delete(familiar.getOcupacionId());

            IngresoDao ingresoDao = mDb.ingresoDao();
            ingresoDao.delete(familiar.getIngresoId());

            SaludDao saludDao = mDb.saludDao();
            saludDao.delete(familiar.getSaludId());

            familiarDao.delete(familiar.getId());
        }

        /*Solicitante*/
        SolicitanteDao solDao = mDb.solicitanteDao();
        solDao.delete(form.getSolicitanteId());
        /*Apoderado*/
        ApoderadoDao apoderadoDao = mDb.apoderadoDao();
        apoderadoDao.delete(form.getApoderadoId());
        /*Domicilio*/
        DomicilioDao domicilioDao = mDb.domicilioDao();
        domicilioDao.delete(form.getDomicilioId());
        /*Situacion habitacional*/
        SituacionHabitacionalDao situacionHabitacionalDao = mDb.situacionHabitacionalDao();
        situacionHabitacionalDao.delete(form.getSituacionHabitacionalId());
        /*Características vivienda*/
        CaracteristicasViviendaDao caracteristicasViviendaDao = mDb.caracteristicasViviendaDao();
        caracteristicasViviendaDao.delete(form.getCaracteristicasViviendaId());
        /*Infraestructura barrial*/
        InfraestructuraBarrialDao infraestructuraBarrialDao = mDb.infraestructuraBarrialDao();
        infraestructuraBarrialDao.delete(form.getInfraestructuraBarrialId());

        FormularioDao formularioDao = mDb.formularioDao();
        formularioDao.delete(form.getId());

        /*If the insert of the form exist in the transaction Log it is deleted to avoid inserting and deleting from
         * server needlessly*/
        TransactionDao transactionDao = mDb.transactionDao();
        Transaction transaction = transactionDao.findTransaction(TransactionOptions.INSERT.getValue(), form.getId());

        if (transaction != null) {
            transactionDao.delete(transaction);
        } else {
            /*If there is no insert for the form in the transaction log, it means that the form is already uploaded
             * so the delete must be inserted in the log to delete from server later*/
            transactionDao.insert(new Transaction(TransactionOptions.DELETE.getValue(), form.getId()));
        }
        return observer;
    }

    @Override
    protected void onPostExecute(DisposableObserver<Boolean> booleanDisposableObserver) {
        booleanDisposableObserver.onNext(true);
    }
}
