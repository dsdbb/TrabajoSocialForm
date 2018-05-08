package ar.edu.uns.cs.trabajosocialform.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import ar.edu.uns.cs.trabajosocialform.DataModel.Ocupacion;

/**
 * Created by Kevin (User) on 18/4/2018.
 */
@Dao
public interface OcupacionDao {


    @Insert
    long insert(Ocupacion ocupacion);

    @Update
    void update(Ocupacion... ocupaciones);

    @Delete
    void delete(Ocupacion... ocupaciones);

    @Query("SELECT * FROM ocupacion WHERE id = :ocupacionId")
    Ocupacion getOcupacion(int ocupacionId);
}
