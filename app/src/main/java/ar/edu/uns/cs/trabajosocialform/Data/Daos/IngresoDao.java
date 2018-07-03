package ar.edu.uns.cs.trabajosocialform.Data.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import ar.edu.uns.cs.trabajosocialform.Data.Converters.Converters;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Ingreso;

/**
 * Contains database methods related to Ingreso
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

    @Query("DELETE FROM ingreso WHERE id = :id")
    void delete(int id);

    @Query("SELECT * FROM ingreso WHERE id = :ingresoId")
    Ingreso getIngreso(int ingresoId);
}
