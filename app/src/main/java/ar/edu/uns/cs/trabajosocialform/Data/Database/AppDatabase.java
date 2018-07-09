package ar.edu.uns.cs.trabajosocialform.Data.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import ar.edu.uns.cs.trabajosocialform.Data.Converters.Converters;
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
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Apoderado;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Familiar;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Ingreso;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Solicitante;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.CaracteristicasVivienda;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Domicilio;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.FormularioFamiliarJoin;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.InfraestructuraBarrial;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Ocupacion;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Salud;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.SituacionHabitacional;
import ar.edu.uns.cs.trabajosocialform.Data.Transactions.Transaction;
import ar.edu.uns.cs.trabajosocialform.Data.Transactions.TransactionDao;

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

    static final Migration MIGRATION = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE formularios");

        }
    };

    public static AppDatabase getAppDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "formularios")
                            .addMigrations(MIGRATION)
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }
}
