package ar.edu.uns.cs.trabajosocialform.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import ar.edu.uns.cs.trabajosocialform.DataModel.Familiar;
import ar.edu.uns.cs.trabajosocialform.DataModel.Solicitante;

/**
 * Contains database methods related to Familiar
 */

@Dao
public interface FamiliarDao {

    @Insert
    long insert(Familiar familiar);

    @Update
    void update(Familiar... familiares);

    @Delete
    void delete(Familiar... familiares);

    @Query("DELETE FROM familiar WHERE id = :id")
    void delete(int id);

    @Query("SELECT * FROM familiar WHERE id = :familiarId")
    Familiar getFamiliar(int familiarId);


}
