package ar.edu.uns.cs.trabajosocialform.java_classes.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import ar.edu.uns.cs.trabajosocialform.java_classes.DataModel.Solicitante;

/**
 * Created by Kevin (User) on 18/4/2018.
 */
@Dao
public interface SolicitanteDao {

    @Insert
    void insert(Solicitante... solicitantes);

    @Update
    void update(Solicitante... solicitantes);

    @Delete
    void delete(Solicitante... solicitantes);
}
