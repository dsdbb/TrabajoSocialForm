package ar.edu.uns.cs.trabajosocialform.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import ar.edu.uns.cs.trabajosocialform.DataModel.SituacionHabitacional;

/**
 * Created by Kevin (User) on 18/4/2018.
 */
@Dao
public interface SituacionHabitacionalDao {


    @Insert
    long insert(SituacionHabitacional situacion);

    @Update
    void update(SituacionHabitacional... situaciones);

    @Delete
    void delete(SituacionHabitacional... situaciones);

    @Query("SELECT * FROM situacion_habitacional WHERE id = :situacionHabitacionalId")
    SituacionHabitacional getSituacionHabitacional(int situacionHabitacionalId);
}