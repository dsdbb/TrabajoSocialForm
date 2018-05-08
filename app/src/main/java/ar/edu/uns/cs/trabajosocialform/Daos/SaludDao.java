package ar.edu.uns.cs.trabajosocialform.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import ar.edu.uns.cs.trabajosocialform.DataModel.Salud;

/**
 * Created by Kevin (User) on 18/4/2018.
 */
@Dao
public interface SaludDao {

    @Insert
    long insert(Salud salud);

    @Update
    void update(Salud... salud);

    @Delete
    void delete(Salud... salud);

    @Query("SELECT * FROM salud WHERE id = :saludId")
    Salud getSalud(int saludId);
}
