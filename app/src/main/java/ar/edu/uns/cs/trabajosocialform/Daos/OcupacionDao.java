package ar.edu.uns.cs.trabajosocialform.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import ar.edu.uns.cs.trabajosocialform.DataModel.Ocupacion;

/**
 * Contains database methods related to Ocupacion
 */
@Dao
public interface OcupacionDao {


    @Insert
    long insert(Ocupacion ocupacion);

    @Update
    void update(Ocupacion... ocupaciones);

    @Delete
    void delete(Ocupacion... ocupaciones);

    @Query("DELETE FROM ocupacion WHERE id = :id")
    void delete(int id);

    @Query("SELECT * FROM ocupacion WHERE id = :ocupacionId")
    Ocupacion getOcupacion(int ocupacionId);
}
