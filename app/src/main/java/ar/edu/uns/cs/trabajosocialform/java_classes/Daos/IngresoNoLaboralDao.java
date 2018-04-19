package ar.edu.uns.cs.trabajosocialform.java_classes.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import ar.edu.uns.cs.trabajosocialform.java_classes.DataModel.IngresoNoLaboral;

/**
 * Created by Kevin (User) on 18/4/2018.
 */
@Dao
public interface IngresoNoLaboralDao {


    @Insert
    void insert(IngresoNoLaboral... ingresos);

    @Update
    void update(IngresoNoLaboral... ingresos);

    @Delete
    void delete(IngresoNoLaboral... ingresos);
}
