package ar.edu.uns.cs.trabajosocialform.Data.asyncTasks;

import android.content.Context;
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
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.FormularioFamiliarJoin;
import ar.edu.uns.cs.trabajosocialform.Data.Database.AppDatabase;
import ar.edu.uns.cs.trabajosocialform.Data.Transactions.Transaction;
import ar.edu.uns.cs.trabajosocialform.Data.Transactions.TransactionOptions;
import io.reactivex.observers.DisposableObserver;

public class InsertAsyncTask extends AsyncTask<Object,Void,DisposableObserver<Boolean>> {



    @Override
    protected DisposableObserver<Boolean> doInBackground(Object[] objects) {
        AppDatabase mDb = AppDatabase.getAppDatabase((Context)objects[0]); // Get an Instance of Database class

        Formulario form = (Formulario)objects[1];
        /*Save the different sections of the form and then the Formulario object referencing to re correspondent id's*/

        /*Solicitante*/
        SolicitanteDao solDao = mDb.solicitanteDao();
        Long solicitanteId = null;
        if (form.getSolicitante() != null)
            solicitanteId = solDao.insert(form.getSolicitante());
        /*Apoderado*/
        ApoderadoDao apoderadoDao = mDb.apoderadoDao();
        Long apoderadoId = null;
        if (form.getApoderado() != null)
            apoderadoId = apoderadoDao.insert(form.getApoderado());
        /*Domicilio*/
        DomicilioDao domicilioDao = mDb.domicilioDao();
        Long domicilioId = null;
        if (form.getDomicilio() != null)
            domicilioId = domicilioDao.insert(form.getDomicilio());
        /*Situacion habitacional*/
        SituacionHabitacionalDao situacionHabitacionalDao = mDb.situacionHabitacionalDao();
        Long situacionHabitacionalId = null;
        if (form.getSituacionHabitacional() != null)
            situacionHabitacionalId = situacionHabitacionalDao.insert(form.getSituacionHabitacional());
        /*Características vivienda*/
        CaracteristicasViviendaDao caracteristicasViviendaDao = mDb.caracteristicasViviendaDao();
        Long caracteristicasViviendaId = null;
        if (form.getCaracteristicasVivienda() != null)
            caracteristicasViviendaId = caracteristicasViviendaDao.insert(form.getCaracteristicasVivienda());
        /*Infraestructura barrial*/
        InfraestructuraBarrialDao infraestructuraBarrialDao = mDb.infraestructuraBarrialDao();
        Long infraestructuraBarrialId = null;
        if (form.getInfraestructuraBarrial() != null)
            infraestructuraBarrialId = infraestructuraBarrialDao.insert(form.getInfraestructuraBarrial());

        /*Add to the form the id's of the objects saved recently*/
        if (solicitanteId != null) form.setSolicitanteId(solicitanteId.intValue());
        if (apoderadoId != null) form.setApoderadoId(apoderadoId.intValue());
        if (domicilioId != null) form.setDomicilioId(domicilioId.intValue());
        if (situacionHabitacionalId != null)
            form.setSituacionHabitacionalId(situacionHabitacionalId.intValue());
        if (caracteristicasViviendaId != null)
            form.setCaracteristicasViviendaId(caracteristicasViviendaId.intValue());
        if (infraestructuraBarrialId != null)
            form.setInfraestructuraBarrialId(infraestructuraBarrialId.intValue());

        /*Now that the form is completed it can be saved*/
        FormularioDao formularioDao = mDb.formularioDao();
        Long formularioId = formularioDao.insert(form);

        /*Once the form is saved is time to family because each new family member will be related with form id*/
        List<Familiar> familiares = form.getFamiliares();
        if (familiares != null) {
            for (int i = 0; i < familiares.size(); i++) {
                Familiar familiar = familiares.get(i);

                /*Ocupacion*/
                OcupacionDao ocupacionDao = mDb.ocupacionDao();
                long ocupacionId = ocupacionDao.insert(familiar.getOcupacion());
                /*Ingresos*/
                IngresoDao ingresoDao = mDb.ingresoDao();
                long ingresoId = ingresoDao.insert(familiar.getIngreso());
                /*Salud*/
                SaludDao saludDao = mDb.saludDao();
                long saludId = saludDao.insert(familiar.getSalud());

                /*Complete Familiar object with corresponding Ocupacion, Ingresos and Salud id*/
                familiar.setOcupacionId((int) ocupacionId);
                familiar.setIngresoId((int) ingresoId);
                familiar.setSaludId((int) saludId);

                /*Once the Familiar object is completed it can be saved*/
                FamiliarDao familiarDao = mDb.familiarDao();
                long familiarId = familiarDao.insert(familiar);

                /*Having the form and the family member we have to relate it by id using a join table*/
                FormularioFamiliarJoin ffj = new FormularioFamiliarJoin(formularioId.intValue(), (int) familiarId);
                FormularioFamiliarDao formularioFamiliarDao = mDb.formularioFamiliarDao();
                formularioFamiliarDao.insert(ffj);
            }

        }

        Transaction transaction = new Transaction(TransactionOptions.INSERT.getValue(), formularioId.intValue());
        mDb.transactionDao().insert(transaction);
        return (DisposableObserver<Boolean>)objects[2];
    }

    @Override
    protected void onPostExecute(DisposableObserver<Boolean> booleanDisposableObserver) {
        booleanDisposableObserver.onNext(true);
    }
}
