package ar.edu.uns.cs.trabajosocialform.Database;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ar.edu.uns.cs.trabajosocialform.Daos.ApoderadoDao;
import ar.edu.uns.cs.trabajosocialform.Daos.CaracteristicasViviendaDao;
import ar.edu.uns.cs.trabajosocialform.Daos.DomicilioDao;
import ar.edu.uns.cs.trabajosocialform.Daos.FamiliarDao;
import ar.edu.uns.cs.trabajosocialform.Daos.FormularioDao;
import ar.edu.uns.cs.trabajosocialform.Daos.FormularioFamiliarDao;
import ar.edu.uns.cs.trabajosocialform.Daos.InfraestructuraBarrialDao;
import ar.edu.uns.cs.trabajosocialform.Daos.IngresoDao;
import ar.edu.uns.cs.trabajosocialform.Daos.OcupacionDao;
import ar.edu.uns.cs.trabajosocialform.Daos.SaludDao;
import ar.edu.uns.cs.trabajosocialform.Daos.SituacionHabitacionalDao;
import ar.edu.uns.cs.trabajosocialform.Daos.SolicitanteDao;
import ar.edu.uns.cs.trabajosocialform.DataModel.Apoderado;
import ar.edu.uns.cs.trabajosocialform.DataModel.CaracteristicasVivienda;
import ar.edu.uns.cs.trabajosocialform.DataModel.Domicilio;
import ar.edu.uns.cs.trabajosocialform.DataModel.Familiar;
import ar.edu.uns.cs.trabajosocialform.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.DataModel.FormularioFamiliarJoin;
import ar.edu.uns.cs.trabajosocialform.DataModel.InfraestructuraBarrial;
import ar.edu.uns.cs.trabajosocialform.DataModel.Ingreso;
import ar.edu.uns.cs.trabajosocialform.DataModel.Ocupacion;
import ar.edu.uns.cs.trabajosocialform.DataModel.Salud;
import ar.edu.uns.cs.trabajosocialform.DataModel.SituacionHabitacional;
import ar.edu.uns.cs.trabajosocialform.DataModel.Solicitante;
import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.Transactions.Transaction;
import ar.edu.uns.cs.trabajosocialform.Transactions.TransactionDao;
import ar.edu.uns.cs.trabajosocialform.Transactions.TransactionOptions;

public class DatabaseAcces {

    public void saveInDatabase(Activity act, final Formulario form, boolean showMessage){
        final Context context = act;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("Estado: ","Ejecutando el run");
                AppDatabase mDb = AppDatabase.getAppDatabase(context); // Get an Instance of Database class
                /*Guardo cada seccion del formulario en su correspondiente tabla para luego guardar el formuarlio con todos
                * los respectivos ID*/

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

                /*Agrego al objeto Formulario todos los id de las secciones guardardas en la base de datos recientemente*/
                form.setSolicitanteId((int)solicitanteId);
                form.setApoderadoId((int)apoderadoId);
                form.setDomicilioId((int)domicilioId);
                form.setSituacionHabitacionalId((int)situacionHabitacionalId);
                form.setCaracteristicasViviendaId((int)caracteristicasViviendaId);
                form.setInfraestructuraBarrialId((int)infraestructuraBarrialId);

                /*Ahora que el objeto formulario está completo puedo agregarlo a la base de datos (Familiares va despues)*/
                FormularioDao formularioDao = mDb.formularioDao();
                long formularioId = formularioDao.insert(form);

                /*Ahora debo guardar todos los familiares con sus respectivos ingresos, ocupaciones y salud*/
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

                    /*Ahora completo el familiar con los id de las entidades creadas recientemente*/
                    familiar.setOcupacionId((int)ocupacionId);
                    familiar.setIngresoId((int)ingresoId);
                    familiar.setSaludId((int)saludId);

                    /*Una vez completo el familiar lo inserto en la BD y luego hago la inserción de la tabla que une el
                    * formulario con el familiar*/
                    FamiliarDao familiarDao = mDb.familiarDao();
                    long familiarId = familiarDao.insert(familiar);

                    /*Creo la tabla Join con los id y la inserto*/
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

        if(showMessage){
            Toast toast = Toast.makeText(act, R.string.insert_correcto,Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void updateDatabase(final Activity act, Formulario newForm,Formulario oldForm, boolean showMessage){
        /*Update se toma como eliminar lo viejo y agregar un nuevo formulario*/
        delete(act,oldForm,false);
        saveInDatabase(act,newForm,false);

        if(showMessage){
            Toast toast = Toast.makeText(act,R.string.update_correcto,Toast.LENGTH_LONG);
            toast.show();
        }
    }


    public Formulario getCompleteForm(final Activity act, Formulario form){
        form.setSolicitante(getSolicitante(act,form.getSolicitanteId()));
        form.setApoderado(getApoderado(act,form.getApoderadoId()));
        form.setDomicilio(getDomicilio(act,form.getDomicilioId()));
        form.setSituacionHabitacional(getSituacionHabitacional(act,form.getSituacionHabitacionalId()));
        form.setCaracteristicasVivienda(getCaracteristicasVivienda(act, form.getCaracteristicasViviendaId()));
        form.setInfraestructuraBarrial(getInfraestructuraBarrial(act,form.getInfraestructuraBarrialId()));

        List<Familiar> familiares = getFamiliares(act, form.getId());
        Log.i("FAMILIARES ANTES :",familiares.size() +"");
        for(int i=0; i<familiares.size();i++){
            Familiar familiar = familiares.get(i);
            familiar.setOcupacion(getOcupacion(act,familiar.getOcupacionId()));
            Log.i("INGRESO ID: ",familiar.getIngresoId()+"");
            familiar.setIngreso(getIngreso(act,familiar.getIngresoId()));
            familiar.setSalud(getSalud(act,familiar.getSaludId()));
        }

        form.setFamiliares(familiares);

        return form;
    }

    public List<String> getNombresSolicitantes(final Activity act){

        final List<String> nombres = new ArrayList<String>();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase mDb = AppDatabase.getAppDatabase(act);// Get an Instance of Database class
                SolicitanteDao solDao = mDb.solicitanteDao();
                List<Solicitante> lista = solDao.getAllSolicitantes();

                Log.i("SIZE: ",""+lista.size());
                for(int i =0; i<lista.size();i++){
                    Log.i("Nombre "+i,lista.get(i).getNombres());
                    Log.i("ID SOLICITANTE: ",lista.get(i).getId()+"");
                    nombres.add(lista.get(i).getNombres());
                }
            }
        });

        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return nombres;
    }

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

    public List<Familiar> getFamiliares(final Activity act, final int formId){
        final List<Familiar> familiares = new ArrayList<Familiar>();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase mDb = AppDatabase.getAppDatabase(act);// Get an Instance of Database class
                FormularioFamiliarDao formDao = mDb.formularioFamiliarDao();
                FamiliarDao familiarDao = mDb.familiarDao();
                List<Integer> listaId = formDao.getFamiliaresIdJoin(formId);
                Log.i("CANTIADAD DE IDS: ",listaId.size()+"");
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

        Log.i("SIZE DENTRO: ",familiares.size()+"");
        return familiares;
    }

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

    public void delete(final Activity act, final Formulario form, boolean showMessage){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase mDb = AppDatabase.getAppDatabase(act);// Get an Instance of Database class
                /*Elimino joins*/

                FormularioFamiliarDao formularioFamiliarDao = mDb.formularioFamiliarDao();
                List<Integer> familiaresId = formularioFamiliarDao.getFamiliares(form.getId());
                formularioFamiliarDao.deleteJoinsWithForm(form.getId());

                FamiliarDao familiarDao = mDb.familiarDao();
                /*Elimino los familiares*/
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

                /*Busco si hay un insert en el log y si existe lo elimino y no agrego el delete*/
                TransactionDao transactionDao = mDb.transactionDao();
                Transaction transaction = transactionDao.findTransaction(TransactionOptions.INSERT.getValue(), form.getId());

                if(transaction!=null){
                    transactionDao.delete(transaction);
                }
                else{
                    /*Si no existia un insert en el log quiere decir que ya esta subido a internet el form asi
                    * que debo agregar el delete*/
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

        if(showMessage){
            Toast toast = Toast.makeText(act,R.string.delete_correcto,Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void deleteFormulario(final Activity act ,final int formId) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase mDb = AppDatabase.getAppDatabase(act);// Get an Instance of Database class
                FormularioDao formularioDao = mDb.formularioDao();
                formularioDao.deleteForm(formId);
            }
        });

        t.start();

    }
    public void deleteSolicitante(final Activity act ,final int solId) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase mDb = AppDatabase.getAppDatabase(act);// Get an Instance of Database class
                SolicitanteDao solicitanteDao = mDb.solicitanteDao();
                solicitanteDao.deleteSolicitante(solId);
            }
        });

        t.start();

    }

    public void deleteJoins(final Activity act, final int formId){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase mDb = AppDatabase.getAppDatabase(act);// Get an Instance of Database class
                FormularioFamiliarDao formularioFamiliarDao = mDb.formularioFamiliarDao();
                formularioFamiliarDao.deleteJoinsWithForm(formId);
            }
        });

        t.start();
    }

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

    public void deleteAllTransactions(final Activity act, final List<Transaction> transactions){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase mDb = AppDatabase.getAppDatabase(act);// Get an Instance of Database class
                TransactionDao transactionDao = mDb.transactionDao();
                transactionDao.delete(transactions.toArray(new Transaction[transactions.size()]));
            }
        });

        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

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
