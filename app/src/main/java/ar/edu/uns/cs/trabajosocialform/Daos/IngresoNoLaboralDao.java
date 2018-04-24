package ar.edu.uns.cs.trabajosocialform.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import ar.edu.uns.cs.trabajosocialform.DataModel.IngresoNoLaboral;

/**
 * Created by Kevin (User) on 18/4/2018.
 */
@Dao
public interface IngresoNoLaboralDao {


    @Insert
    long insert(IngresoNoLaboral ingreso);

    @Update
    void update(IngresoNoLaboral... ingresos);

    @Delete
    void delete(IngresoNoLaboral... ingresos);
}
