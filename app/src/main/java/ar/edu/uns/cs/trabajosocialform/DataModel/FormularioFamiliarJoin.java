package ar.edu.uns.cs.trabajosocialform.DataModel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import java.io.Serializable;

/**
 * This Class represents the join between a form and a family member
 */

@Entity(tableName = "formulario_familiar_join",
        primaryKeys = { "formulario_id", "familiar_id" },
        foreignKeys = {
                @ForeignKey(entity = Formulario.class,
                        parentColumns = "id",
                        childColumns = "formulario_id"),
                @ForeignKey(entity = Familiar.class,
                        parentColumns = "id",
                        childColumns = "familiar_id")
        })
public class FormularioFamiliarJoin implements Serializable {

    @ColumnInfo(name = "formulario_id")
    private int formularioId;
    @ColumnInfo(name = "familiar_id")
    private int familiarId;

    public FormularioFamiliarJoin(int formularioId, int familiarId) {
        this.formularioId = formularioId;
        this.familiarId = familiarId;
    }

    public int getFormularioId() {
        return formularioId;
    }

    public void setFormularioId(int formularioId) {
        this.formularioId = formularioId;
    }

    public int getFamiliarId() {
        return familiarId;
    }

    public void setFamiliarId(int familiarId) {
        this.familiarId = familiarId;
    }
}
