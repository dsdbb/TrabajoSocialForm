package ar.edu.uns.cs.trabajosocialform.Data.Database;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
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
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Ingreso;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Apoderado;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.CaracteristicasVivienda;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Domicilio;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.FormularioFamiliarJoin;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.InfraestructuraBarrial;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Ocupacion;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Salud;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.SituacionHabitacional;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Solicitante;
import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.Data.Transactions.Transaction;
import ar.edu.uns.cs.trabajosocialform.Data.Transactions.TransactionDao;
import ar.edu.uns.cs.trabajosocialform.Data.Transactions.TransactionOptions;

/**
 * Class that provides the methods to access the local Database
 */
public class DatabaseAcces {

    /**
     * Save a form in the local database
     * @param act Activity where the method is called
     * @param form Formulario object to be saved
     */
    public void saveInDatabase(Activity act, final Formulario form){
        final Context context = act;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase mDb = AppDatabase.getAppDatabase(context); // Get an Instance of Database class

                /*Save the different sections of the form and then the Formulario object referencing to re correspondent id's*/

                /*Solicitante*/
                SolicitanteDao solDao = mDb.solicitanteDao();
                long solicitanteId = solDao.insert(form.getSolicitante());
                /*Apoderado*/
                ApoderadoDao apoderadoDao = mDb.apoderadoDao();
                long apoderadoId = apoderadoDao.insert(form.getApoderado());
                /*Domicilio*/
                DomicilioDao domicilioDao = mDb.domicilioDao();
                long domicilioId = domicilioDao.insert(form.getDomicilio());
                /*Situacion habitacional*/
                SituacionHabitacionalDao situacionHabitacionalDao = mDb.situacionHabitacionalDao();
                long situacionHabitacionalId = situacionHabitacionalDao.insert(form.getSituacionHabitacional());
                /*Características vivienda*/
                CaracteristicasViviendaDao caracteristicasViviendaDao = mDb.caracteristicasViviendaDao();
                long caracteristicasViviendaId = caracteristicasViviendaDao.insert(form.getCaracteristicasVivienda());
                /*Infraestructura barrial*/
                InfraestructuraBarrialDao infraestructuraBarrialDao = mDb.infraestructuraBarrialDao();
                long infraestructuraBarrialId = infraestructuraBarrialDao.insert(form.getInfraestructuraBarrial());

                /*Add to the form the id's of the objects saved recently*/
                form.setSolicitanteId((int)solicitanteId);
                form.setApoderadoId((int)apoderadoId);
                form.setDomicilioId((int)domicilioId);
                form.setSituacionHabitacionalId((int)situacionHabitacionalId);
                form.setCaracteristicasViviendaId((int)caracteristicasViviendaId);
                form.setInfraestructuraBarrialId((int)infraestructuraBarrialId);

                /*Now that the form is completed it can be saved*/
                FormularioDao formularioDao = mDb.formularioDao();
                long formularioId = formularioDao.insert(form);

                /*Once the form is saved is time to family because each new family member will be related with form id*/
                List<Familiar> familiares = form.getFamiliares();
                for(int i=0; i<familiares.size(); i++){
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
                    familiar.setOcupacionId((int)ocupacionId);
                    familiar.setIngresoId((int)ingresoId);
                    familiar.setSaludId((int)saludId);

                    /*Once the Familiar object is completed it can be saved*/
                    FamiliarDao familiarDao = mDb.familiarDao();
                    long familiarId = familiarDao.insert(familiar);

                    /*Having the form and the family member we have to relate it by id using a join table*/
                    FormularioFamiliarJoin ffj = new FormularioFamiliarJoin((int)formularioId,(int)familiarId);
                    FormularioFamiliarDao formularioFamiliarDao = mDb.formularioFamiliarDao();
                    formularioFamiliarDao.insert(ffj);
                }

                Transaction transaction = new Transaction(TransactionOptions.INSERT.getValue(),(int)formularioId);
                mDb.transactionDao().insert(transaction);

            }
        });
        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Update the form that has been modified
     * @param act Activity where the method was called
     * @param newForm Form with the modifications
     * @param oldForm Old Form that has no modifications
     */
    public void updateDatabase(final Activity act, Formulario newForm,Formulario oldForm){
        /*Delete the old form and add the modificated form*/
        delete(act,oldForm);
        saveInDatabase(act,newForm);
    }

    /**
     * Having a form only with references to IDs, this method complete the form with the different objects related
     * to that IDs
     * @param act Activity where the method was called
     * @param form Formulario with the ids
     * @return Formulario with objects related to the IDs
     */
    public Formulario getCompleteForm(final Activity act, Formulario form){
        form.setSolicitante(getSolicitante(act,form.getSolicitanteId()));
        form.setApoderado(getApoderado(act,form.getApoderadoId()));
        form.setDomicilio(getDomicilio(act,form.getDomicilioId()));
        form.setSituacionHabitacional(getSituacionHabitacional(act,form.getSituacionHabitacionalId()));
        form.setCaracteristicasVivienda(getCaracteristicasVivienda(act, form.getCaracteristicasViviendaId()));
        form.setInfraestructuraBarrial(getInfraestructuraBarrial(act,form.getInfraestructuraBarrialId()));

        List<Familiar> familiares = getFamiliares(act, form.getId());
        for(int i=0; i<familiares.size();i++){
            Familiar familiar = familiares.get(i);
            familiar.setOcupacion(getOcupacion(act,familiar.getOcupacionId()));
            familiar.setIngreso(getIngreso(act,familiar.getIngresoId()));
            familiar.setSalud(getSalud(act,familiar.getSaludId()));
        }

        form.setFamiliares(familiares);

        return form;
    }

    /**
     * Get all forms (Formulario) of the database
     * @param act Activity where the method is called
     * @return List with all the Formulario objects in the database
     */
    public List<Formulario> getFormularios(final Activity act){
        final List<Formulario> forms = new ArrayList<Formulario>();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase mDb = AppDatabase.getAppDatabase(act);// Get an Instance of Database class
                FormularioDao formDao = mDb.formularioDao();
                List<Formulario> lista = formDao.getAllForms();
                for(int i =0; i<lista.size();i++){
                    forms.add(lista.get(i));
                }
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return forms;
    }

    /**
     * Get a Solicitante from the ID
     * @param act Activity where the method was called
     * @param solicitanteId ID of the wanted Solicitante
     * @return Solicitante
     */
    public Solicitante getSolicitante(final Activity act, final int solicitanteId){
        final List<Solicitante> solicitante = new ArrayList<Solicitante>();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase mDb = AppDatabase.getAppDatabase(act);// Get an Instance of Database class
                SolicitanteDao solDao = mDb.solicitanteDao();
                Solicitante sol  = solDao.getSolicitante(solicitanteId);
                solicitante.add(sol);
            }
        });

        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return solicitante.get(0);
    }

    /**
     * Get an Apoderado from the ID
     * @param act Activity where the method was called
     * @param apoderadoId ID of the wanted Apoderado
     * @return Apoderado
     */
    public Apoderado getApoderado(final Activity act, final int apoderadoId){
        final List<Apoderado> apoderadoList = new ArrayList<Apoderado>();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase mDb = AppDatabase.getAppDatabase(act);// Get an Instance of Database class
                ApoderadoDao apoderadoDao = mDb.apoderadoDao();
                Apoderado apoderado  = apoderadoDao.getApoderado(apoderadoId);
                apoderadoList.add(apoderado);
            }
        });

        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return apoderadoList.get(0);
    }

    /**
     * Get a Domicilio from the ID
     * @param act Activity where the method was called
     * @param domicilioId ID of the wanted Domicilio
     * @return Domicilio
     */
    public Domicilio getDomicilio(final Activity act, final int domicilioId){
        final List<Domicilio> domicilioList = new ArrayList<Domicilio>();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase mDb = AppDatabase.getAppDatabase(act);// Get an Instance of Database class
                DomicilioDao domicilioDao = mDb.domicilioDao();
                Domicilio domicilio  = domicilioDao.getDomicilio(domicilioId);
                domicilioList.add(domicilio);
            }
        });

        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return domicilioList.get(0);
    }

    /**
     * Get a List of Familiar objects related to a form
     * @param act the Activity where the method was called
     * @param formId the ID of the form
     * @return a List with family members related to the form
     */
    public List<Familiar> getFamiliares(final Activity act, final int formId){
        final List<Familiar> familiares = new ArrayList<Familiar>();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase mDb = AppDatabase.getAppDatabase(act);// Get an Instance of Database class
                FormularioFamiliarDao formDao = mDb.formularioFamiliarDao();
                FamiliarDao familiarDao = mDb.familiarDao();
                List<Integer> listaId = formDao.getFamiliaresIdJoin(formId);
                for(int i =0; i<listaId.size();i++){
                    Familiar familiar = familiarDao.getFamiliar(listaId.get(i));
                    familiares.add(familiar);
                }
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return familiares;
    }

    /**
     * Get a SituacionHabitacional object from the ID
     * @param act Activity where the method was called
     * @param situacionHabitacionalId ID of the wanted SituacionHabitacional object
     * @return SituacionHabitacional
     */
    public SituacionHabitacional getSituacionHabitacional(final Activity act, final int situacionHabitacionalId){
        final List<SituacionHabitacional> situacionList = new ArrayList<SituacionHabitacional>();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase mDb = AppDatabase.getAppDatabase(act);// Get an Instance of Database class
                SituacionHabitacionalDao situacionHabitacionalDao = mDb.situacionHabitacionalDao();
                SituacionHabitacional situacionHabitacional  = situacionHabitacionalDao.getSituacionHabitacional(situacionHabitacionalId);
                situacionList.add(situacionHabitacional);
            }
        });

        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return situacionList.get(0);
    }

    /**
     * Get a CaracteristicasVivienda object from the ID
     * @param act Activity where the method was called
     * @param caracteristicasViviendaId ID of the wanted CaracteristicasVivienda object
     * @return CaracteristicasVivienda
     */
    public CaracteristicasVivienda getCaracteristicasVivienda(final Activity act, final int caracteristicasViviendaId){
        final List<CaracteristicasVivienda> caracteristicasList = new ArrayList<CaracteristicasVivienda>();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase mDb = AppDatabase.getAppDatabase(act);// Get an Instance of Database class
                CaracteristicasViviendaDao caracteristicasViviendaDao = mDb.caracteristicasViviendaDao();
                CaracteristicasVivienda caracteristicasVivienda  = caracteristicasViviendaDao.getCaracteristicasVivienda(caracteristicasViviendaId);
                caracteristicasList.add(caracteristicasVivienda);
            }
        });

        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return caracteristicasList.get(0);
    }

    /**
     * Get an InfraestructuraBarrial object from the ID
     * @param act Activity where the method was called
     * @param infraestructuraBarrialId ID of the wanted InfraestructuraBarrial object
     * @return InfraestructuraBarrial
     */
    public InfraestructuraBarrial getInfraestructuraBarrial(final Activity act, final int infraestructuraBarrialId){
        final List<InfraestructuraBarrial> infraestructuraList = new ArrayList<InfraestructuraBarrial>();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase mDb = AppDatabase.getAppDatabase(act);// Get an Instance of Database class
                InfraestructuraBarrialDao infraestructuraBarrialDao = mDb.infraestructuraBarrialDao();
                InfraestructuraBarrial infraestructuraBarrial  = infraestructuraBarrialDao.getInfraestructuraBarrial(infraestructuraBarrialId);
                infraestructuraList.add(infraestructuraBarrial);
            }
        });

        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return infraestructuraList.get(0);
    }

    /**
     * Delete a form from database
     * @param act Activity where the method was called
     * @param form Formulario object containing the ids to delete all the references
     */
    public void delete(final Activity act, final Formulario form){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase mDb = AppDatabase.getAppDatabase(act);// Get an Instance of Database class

                /*Delete joins between Fomulario and Familiar*/
                FormularioFamiliarDao formularioFamiliarDao = mDb.formularioFamiliarDao();
                List<Integer> familiaresId = formularioFamiliarDao.getFamiliares(form.getId());
                formularioFamiliarDao.deleteJoinsWithForm(form.getId());

                FamiliarDao familiarDao = mDb.familiarDao();
                /*Delete Familiar objects*/
                for(int i=0;i<familiaresId.size();i++){
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

                if(transaction!=null){
                    transactionDao.delete(transaction);
                }
                else{
                    /*If there is no insert for the form in the transaction log, it means that the form is already uploaded
                    * so the delete must be inserted in the log to delete from server later*/
                    transactionDao.insert(new Transaction(TransactionOptions.DELETE.getValue(),form.getId()));
                }


            }
        });

        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Retrieve an Ocupacion object from database
     * @param act Activity where the method was called
     * @param ocupacionId The id of the wanted Ocupacion
     * @return Ocupacion
     */
    public Ocupacion getOcupacion(final Activity act, final int ocupacionId){
        final List<Ocupacion> ocupaciones = new ArrayList<Ocupacion>();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase mDb = AppDatabase.getAppDatabase(act);// Get an Instance of Database class
                OcupacionDao ocupacionDao = mDb.ocupacionDao();
                Ocupacion ocupacion = ocupacionDao.getOcupacion(ocupacionId);
                ocupaciones.add(ocupacion);
            }
        });

        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ocupaciones.get(0);
    }


    /**
     * Retrieve an Ingreso object from database
     * @param act Activity where the method was called
     * @param ingresoId The id of the wanted Ingreso
     * @return Ingreso
     */
    public Ingreso getIngreso(final Activity act, final int ingresoId){
        final List<Ingreso> ingresos = new ArrayList<Ingreso>();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase mDb = AppDatabase.getAppDatabase(act);// Get an Instance of Database class
                IngresoDao ingresoDao = mDb.ingresoDao();
                Ingreso ingreso = ingresoDao.getIngreso(ingresoId);
                ingresos.add(ingreso);
            }
        });

        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ingresos.get(0);
    }

    /**
     * Retrieve a Salud object from database
     * @param act Activity where the method was called
     * @param saludId The id of the wanted Salud
     * @return Salud
     */
    public Salud getSalud(final Activity act, final int saludId){
        final List<Salud> salud = new ArrayList<Salud>();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase mDb = AppDatabase.getAppDatabase(act);// Get an Instance of Database class
                SaludDao saludDao = mDb.saludDao();
                Salud saludAux = saludDao.getSalud(saludId);
                salud.add(saludAux);
            }
        });

        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return salud.get(0);
    }

    /**
     * Get all the Transactions in the database
     * @param act Activity where the method was called
     * @return a List with all the Transaction objects
     */
    public List<Transaction> getTransactions(final Activity act){
        final List<Transaction> transactions = new ArrayList<Transaction>();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase mDb = AppDatabase.getAppDatabase(act);// Get an Instance of Database class
                TransactionDao transactionDao = mDb.transactionDao();
                List<Transaction> list = transactionDao.getTransactions();
                for(int i =0; i<list.size();i++){
                    transactions.add(list.get(i));
                }
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    /**
     * Retrieve from database the form with the solicitated id
     * @param act Activity where the method was called
     * @param id Id of the wanted Form
     * @return The Formulario object associated to the received id
     */
    public Formulario getFormulario(final Activity act, final int id){
        final List<Formulario> form = new ArrayList<Formulario>();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase mDb = AppDatabase.getAppDatabase(act);// Get an Instance of Database class
                FormularioDao formularioDao = mDb.formularioDao();
                Formulario formulario = formularioDao.getForm(id);
                form.add(formulario);
            }
        });

        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Formulario formulario = getCompleteForm(act,form.get(0));
        return formulario;
    }

    /**
     * Delete a Transaction from database
     * @param act Activity where the method was called
     * @param transaction Transaction object wanted to be deleted from database
     */
    public void deleteTransaction(final Activity act, final Transaction transaction){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase mDb = AppDatabase.getAppDatabase(act);// Get an Instance of Database class
                TransactionDao transactionDao = mDb.transactionDao();
                transactionDao.delete(transaction);
            }
        });

        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
