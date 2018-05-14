package ar.edu.uns.cs.trabajosocialform.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import ar.edu.uns.cs.trabajosocialform.DataModel.CaracteristicasVivienda;

/**
 * Created by Kevin (User) on 18/4/2018.
 */

@Dao
public interface CaracteristicasViviendaDao {


    @Insert
    long insert(CaracteristicasVivienda caracteristicas);

    @Update
    void update(CaracteristicasVivienda caracteristicas);

    @Delete
    void delete(CaracteristicasVivienda caracteristicas);

    @Query("DELETE FROM caracteristicas_vivienda WHERE id = :id")
    void delete(int id);

    @Query("SELECT * FROM caracteristicas_vivienda WHERE id = :caracteristicasViviendaId")
    CaracteristicasVivienda getCaracteristicasVivienda(int caracteristicasViviendaId);
}
