package ar.edu.uns.cs.trabajosocialform.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ar.edu.uns.cs.trabajosocialform.DataModel.Solicitante;

/**
 * Created by Kevin (User) on 18/4/2018.
 */
@Dao
public interface SolicitanteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Solicitante solicitante);

    @Update
    void update(Solicitante... solicitantes);

    @Delete
    void delete(Solicitante... solicitantes);

    @Query("SELECT * FROM solicitante")
    List<Solicitante> getAllSolicitantes();

    @Query("SELECT * FROM solicitante WHERE id = :solicitanteId")
    Solicitante getSolicitante(int solicitanteId);
}
