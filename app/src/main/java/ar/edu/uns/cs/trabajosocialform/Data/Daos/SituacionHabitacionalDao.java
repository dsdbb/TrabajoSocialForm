package ar.edu.uns.cs.trabajosocialform.Data.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.SituacionHabitacional;

/**
 * Contains database methods related to SituacionHabitacional
 */
@Dao
public interface SituacionHabitacionalDao {


    @Insert
    long insert(SituacionHabitacional situacion);

    @Update
    void update(SituacionHabitacional... situaciones);

    @Delete
    void delete(SituacionHabitacional... situaciones);

    @Query("DELETE FROM situacion_habitacional WHERE id = :id")
    void delete(int id);

    @Query("SELECT * FROM situacion_habitacional WHERE id = :situacionHabitacionalId")
    SituacionHabitacional getSituacionHabitacional(int situacionHabitacionalId);
}
