package ar.edu.uns.cs.trabajosocialform.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import ar.edu.uns.cs.trabajosocialform.DataModel.Domicilio;

/**
 * Created by Kevin (User) on 18/4/2018.
 */

@Dao
public interface DomicilioDao {


    @Insert
    long insert(Domicilio domicilio);

    @Update
    void update(Domicilio... domicilios);

    @Delete
    void delete(Domicilio... domicilios);
}
