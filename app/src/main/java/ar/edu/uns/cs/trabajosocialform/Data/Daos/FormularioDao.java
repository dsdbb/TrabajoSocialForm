package ar.edu.uns.cs.trabajosocialform.Data.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;

/**
 * Contains database methods related to the froms
 */

@Dao
public interface FormularioDao {

    @Insert
    long insert(Formulario form);

    @Update
    void update(Formulario... forms);

    @Delete
    void delete(Formulario... forms);

    @Query("DELETE FROM formularios WHERE id = :id")
    void delete(int id);


    @Query("SELECT * FROM formularios")
    List<Formulario> getAllForms();

    @Query("DELETE FROM formularios WHERE id = :formId")
    void deleteForm(int formId);

    @Query("SELECT * FROM formularios WHERE id= :formId")
    Formulario getForm(int formId);

}
