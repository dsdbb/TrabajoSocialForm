package ar.edu.uns.cs.trabajosocialform.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import ar.edu.uns.cs.trabajosocialform.DataModel.FormularioFamiliarJoin;

@Dao
public interface FormularioFamiliarDao {

    @Insert
    void insert(FormularioFamiliarJoin ffj);


}
