package ar.edu.uns.cs.trabajosocialform.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import ar.edu.uns.cs.trabajosocialform.Converters.Converters;
import ar.edu.uns.cs.trabajosocialform.DataModel.Ingreso;

/**
 * Created by Kevin (User) on 18/4/2018.
 */
@Dao
@TypeConverters({Converters.class})
public interface IngresoDao {


    @Insert
    long insert(Ingreso ingreso);

    @Update
    void update(Ingreso... ingresos);

    @Delete
    void delete(Ingreso... ingresos);

    @Query("SELECT * FROM ingreso WHERE id = :ingresoId")
    Ingreso getIngreso(int ingresoId);
}
