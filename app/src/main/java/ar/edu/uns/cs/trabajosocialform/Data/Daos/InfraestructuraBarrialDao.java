package ar.edu.uns.cs.trabajosocialform.Data.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.InfraestructuraBarrial;

/**
 * Contains database methods related to InfraestructuraBarrial
 */

@Dao
public interface InfraestructuraBarrialDao {



    @Insert
    long insert(InfraestructuraBarrial infraestructura);

    @Update
    void update(InfraestructuraBarrial... infraestructuras);

    @Delete
    void delete(InfraestructuraBarrial... infraestructuras);

    @Query("DELETE FROM infraestructura_barrial WHERE id = :id")
    void delete(int id);

    @Query("SELECT * FROM infraestructura_barrial WHERE id = :infraestructuraBarrialId")
    InfraestructuraBarrial getInfraestructuraBarrial(int infraestructuraBarrialId);
}
