package ar.edu.uns.cs.trabajosocialform.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import ar.edu.uns.cs.trabajosocialform.DataModel.Familiar;
import ar.edu.uns.cs.trabajosocialform.DataModel.Solicitante;

/**
 * Created by Kevin (User) on 18/4/2018.
 */

@Dao
public interface FamiliarDao {

    @Insert
    long insert(Familiar familiar);

    @Update
    void update(Familiar... familiares);

    @Delete
    void delete(Familiar... familiares);

    @Query("SELECT * FROM familiar WHERE id = :familiarId")
    Familiar getFamiliar(int familiarId);

}