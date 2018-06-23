package ar.edu.uns.cs.trabajosocialform.DataModel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * This Class represents the "Ocupacion" fields of the form related to a family member
 */

@Entity(tableName = "ocupacion")
public class Ocupacion implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="condicion_actividad")
    private String condicion_actividad;
    @ColumnInfo(name="puesto_trabajo")
    private String puesto_trabajo;
    @ColumnInfo(name="aporte_jubilatorio")
    private String aporte_jubilatorio;
    @ColumnInfo(name="duracion")
    private String duracion;
    @ColumnInfo(name="tipo_actividad")
    private String tipo_actividad;
    @ColumnInfo(name="calificacion")
    private String calificacion;

    @Ignore
    public Ocupacion() {
    }

    public Ocupacion(String condicion_actividad, String puesto_trabajo, String aporte_jubilatorio, String duracion, String tipo_actividad, String calificacion) {
        this.condicion_actividad = condicion_actividad;
        this.puesto_trabajo = puesto_trabajo;
        this.aporte_jubilatorio = aporte_jubilatorio;
        this.duracion = duracion;
        this.tipo_actividad = tipo_actividad;
        this.calificacion = calificacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCondicion_actividad() {
        return condicion_actividad;
    }

    public void setCondicion_actividad(String condicion_actividad) {
        this.condicion_actividad = condicion_actividad;
    }

    public String getPuesto_trabajo() {
        return puesto_trabajo;
    }

    public void setPuesto_trabajo(String puesto_trabajo) {
        this.puesto_trabajo = puesto_trabajo;
    }

    public String getAporte_jubilatorio() {
        return aporte_jubilatorio;
    }

    public void setAporte_jubilatorio(String aporte_jubilatorio) {
        this.aporte_jubilatorio = aporte_jubilatorio;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getTipo_actividad() {
        return tipo_actividad;
    }

    public void setTipo_actividad(String tipo_actividad) {
        this.tipo_actividad = tipo_actividad;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }
}
