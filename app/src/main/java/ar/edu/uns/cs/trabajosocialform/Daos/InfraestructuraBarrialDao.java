package ar.edu.uns.cs.trabajosocialform.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import ar.edu.uns.cs.trabajosocialform.DataModel.InfraestructuraBarrial;

/**
 * Created by Kevin (User) on 18/4/2018.
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
