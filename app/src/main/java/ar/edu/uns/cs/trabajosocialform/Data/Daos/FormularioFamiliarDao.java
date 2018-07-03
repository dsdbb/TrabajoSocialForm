package ar.edu.uns.cs.trabajosocialform.Data.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.FormularioFamiliarJoin;

/**
 * Contains database methods related to the relation between a form and a family member
 */
@Dao
public interface FormularioFamiliarDao {

    @Insert
    void insert(FormularioFamiliarJoin ffj);

    @Query("SELECT familiar_id FROM formulario_familiar_join WHERE formulario_id=:formularioId")
    List<Integer> getFamiliaresIdJoin(int formularioId);

    @Query("DELETE FROM formulario_familiar_join WHERE formulario_id = :formId")
    void deleteJoinsWithForm(int formId);

    @Query("SELECT familiar_id FROM formulario_familiar_join WHERE formulario_id=:id")
    List<Integer> getFamiliares(int id);
}
