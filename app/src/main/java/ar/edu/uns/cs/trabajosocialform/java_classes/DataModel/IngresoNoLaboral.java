package ar.edu.uns.cs.trabajosocialform.java_classes.DataModel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Kevin (User) on 17/4/2018.
 */

@Entity(tableName = "ingreso_no_laboral")
public class IngresoNoLaboral {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="origen")
    private String origen;
    @ColumnInfo(name="origen")
    private int monto;

    @Ignore
    public IngresoNoLaboral() {
    }

    public IngresoNoLaboral(String origen, int monto) {
        this.origen = origen;
        this.monto = monto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }
}
