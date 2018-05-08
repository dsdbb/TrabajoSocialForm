package ar.edu.uns.cs.trabajosocialform.DataModel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ar.edu.uns.cs.trabajosocialform.Converters.Converters;

/**
 * Created by Kevin (User) on 17/4/2018.
 */

@Entity(tableName = "ingreso")
@TypeConverters({Converters.class})
public class Ingreso implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "ingresos_laborales")
    private Integer ingresos_laborales;
    @ColumnInfo(name = "programas_sociales")
    private List<String> programas_sociales_sti;
    @ColumnInfo(name = "ingresos_no_laborales")
    private List<IngresoNoLaboral> ingresosNoLaborales;

    @Ignore
    public Ingreso() {
        programas_sociales_sti = new ArrayList<String>();
        ingresosNoLaborales = new ArrayList<IngresoNoLaboral>();
    }

    public Ingreso(int ingresos_laborales, List<String> programas_sociales_sti, List<IngresoNoLaboral> ingresosNoLaborales) {
        this.ingresos_laborales = ingresos_laborales;
        this.programas_sociales_sti = new ArrayList<String>();
        this.ingresosNoLaborales = new ArrayList<IngresoNoLaboral>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIngresos_laborales() {
        return ingresos_laborales;
    }

    public void setIngresos_laborales(Integer ingresos_laborales) {
        this.ingresos_laborales = ingresos_laborales;
    }

    public List<String> getProgramas_sociales_sti() {
        return programas_sociales_sti;
    }

    public void setProgramas_sociales_sti(List<String> programas_sociales_sti) {
        this.programas_sociales_sti = programas_sociales_sti;
    }

    public List<IngresoNoLaboral> getIngresosNoLaborales() {
        return ingresosNoLaborales;
    }

    public void setIngresosNoLaborales(List<IngresoNoLaboral> ingresosNoLaborales) {
        this.ingresosNoLaborales = ingresosNoLaborales;
    }
}