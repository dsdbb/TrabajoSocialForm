package ar.edu.uns.cs.trabajosocialform.Data.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import ar.edu.uns.cs.trabajosocialform.Data.Converters.Converters;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Apoderado;

/**
 * Contains database methods related to Apoderado
 */
@Dao
@TypeConverters({Converters.class})
public interface ApoderadoDao {


    @Insert
    long insert(Apoderado apoderado);

    @Update
    void update(Apoderado... apoderados);

    @Delete
    void delete(Apoderado... apoderados);

    @Query("DELETE FROM apoderado WHERE id = :id")
    void delete(int id);

    @Query("SELECT * FROM apoderado WHERE id = :apoderadoId")
    Apoderado getApoderado(int apoderadoId);
}
