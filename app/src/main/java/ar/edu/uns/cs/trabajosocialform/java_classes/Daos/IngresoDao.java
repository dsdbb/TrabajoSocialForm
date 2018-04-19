package ar.edu.uns.cs.trabajosocialform.java_classes.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import ar.edu.uns.cs.trabajosocialform.java_classes.DataModel.Ingreso;

/**
 * Created by Kevin (User) on 18/4/2018.
 */
@Dao
public interface IngresoDao {


    @Insert
    void insert(Ingreso... ingresos);

    @Update
    void update(Ingreso... ingresos);

    @Delete
    void delete(Ingreso... ingresos);
}
