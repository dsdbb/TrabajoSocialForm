package ar.edu.uns.cs.trabajosocialform.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import ar.edu.uns.cs.trabajosocialform.Converters.Converters;
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
import ar.edu.uns.cs.trabajosocialform.Transactions.Transaction;
import ar.edu.uns.cs.trabajosocialform.Transactions.TransactionDao;

/**
 * Setup of ROOM implementation for database access
 */
@Database(entities={Formulario.class,Solicitante.class,Apoderado.class, SituacionHabitacional.class, Domicilio.class,
Familiar.class, InfraestructuraBarrial.class, CaracteristicasVivienda.class, Ocupacion.class,Ingreso.class,
        Salud.class, FormularioFamiliarJoin.class, Transaction.class},version=1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase{

    private static AppDatabase INSTANCE;

    public abstract FormularioDao formularioDao();
    public abstract SolicitanteDao solicitanteDao();
    @TypeConverters({Converters.class})
    public abstract ApoderadoDao apoderadoDao();
    public abstract DomicilioDao domicilioDao();
    public abstract FamiliarDao familiarDao();
    public abstract OcupacionDao ocupacionDao();
    @TypeConverters({Converters.class})
    public abstract IngresoDao ingresoDao();
    @TypeConverters({Converters.class})
    public abstract SaludDao saludDao();
    public abstract SituacionHabitacionalDao situacionHabitacionalDao();
    public abstract CaracteristicasViviendaDao caracteristicasViviendaDao();
    public abstract InfraestructuraBarrialDao infraestructuraBarrialDao();
    public abstract FormularioFamiliarDao formularioFamiliarDao();

    public abstract TransactionDao transactionDao();



    public static AppDatabase getAppDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "formularios")
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;

    }

    public static void destroyInstance(){
        INSTANCE = null;
    }
}
