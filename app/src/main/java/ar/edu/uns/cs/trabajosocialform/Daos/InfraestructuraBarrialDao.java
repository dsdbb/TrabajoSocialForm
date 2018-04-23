package ar.edu.uns.cs.trabajosocialform.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import ar.edu.uns.cs.trabajosocialform.DataModel.InfraestructuraBarrial;

/**
 * Created by Kevin (User) on 18/4/2018.
 */

@Dao
public interface InfraestructuraBarrialDao {



    @Insert
    void insert(InfraestructuraBarrial... infraestructuras);

    @Update
    void update(InfraestructuraBarrial... infraestructuras);

    @Delete
    void delete(InfraestructuraBarrial... infraestructuras);
}
