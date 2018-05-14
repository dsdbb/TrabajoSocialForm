package ar.edu.uns.cs.trabajosocialform.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import ar.edu.uns.cs.trabajosocialform.Converters.Converters;
import ar.edu.uns.cs.trabajosocialform.DataModel.Apoderado;
import ar.edu.uns.cs.trabajosocialform.DataModel.Solicitante;

/**
 * Created by Kevin (User) on 18/4/2018.
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
