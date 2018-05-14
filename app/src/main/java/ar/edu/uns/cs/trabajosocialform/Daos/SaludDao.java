package ar.edu.uns.cs.trabajosocialform.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import ar.edu.uns.cs.trabajosocialform.Converters.Converters;
import ar.edu.uns.cs.trabajosocialform.DataModel.Salud;

/**
 * Created by Kevin (User) on 18/4/2018.
 */
@Dao
@TypeConverters({Converters.class})
public interface SaludDao {

    @Insert
    long insert(Salud salud);

    @Update
    void update(Salud... salud);

    @Delete
    void delete(Salud... salud);

    @Query("DELETE FROM salud WHERE id = :id")
    void delete(int id);

    @Query("SELECT * FROM salud WHERE id = :saludId")
    Salud getSalud(int saludId);
}
