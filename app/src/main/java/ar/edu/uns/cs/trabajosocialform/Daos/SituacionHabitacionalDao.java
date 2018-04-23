package ar.edu.uns.cs.trabajosocialform.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import ar.edu.uns.cs.trabajosocialform.DataModel.SituacionHabitacional;

/**
 * Created by Kevin (User) on 18/4/2018.
 */
@Dao
public interface SituacionHabitacionalDao {


    @Insert
    void insert(SituacionHabitacional... situaciones);

    @Update
    void update(SituacionHabitacional... situaciones);

    @Delete
    void delete(SituacionHabitacional... situaciones);
}
