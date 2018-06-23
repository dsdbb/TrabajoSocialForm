package ar.edu.uns.cs.trabajosocialform.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import ar.edu.uns.cs.trabajosocialform.DataModel.Domicilio;
import ar.edu.uns.cs.trabajosocialform.DataModel.Solicitante;

/**
 * Contains database methods related to Domicilio
 */

@Dao
public interface DomicilioDao {


    @Insert
    long insert(Domicilio domicilio);

    @Update
    void update(Domicilio... domicilios);

    @Delete
    void delete(Domicilio... domicilios);

    @Query("DELETE FROM domicilio WHERE id = :id")
    void delete(int id);

    @Query("SELECT * FROM domicilio WHERE id = :domicilioId")
    Domicilio getDomicilio(int domicilioId);
}
