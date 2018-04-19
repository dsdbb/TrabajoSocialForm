package ar.edu.uns.cs.trabajosocialform.java_classes.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ar.edu.uns.cs.trabajosocialform.java_classes.DataModel.Formulario;

/**
 * Created by Kevin (User) on 18/4/2018.
 */

@Dao
public interface FormularioDao {

    @Insert
    void insert(Formulario... forms);

    @Update
    void update(Formulario... forms);

    @Delete
    void delete(Formulario... forms);

    @Query("SELECT * FROM formularios")
    List<Formulario> getAllForms();


}
