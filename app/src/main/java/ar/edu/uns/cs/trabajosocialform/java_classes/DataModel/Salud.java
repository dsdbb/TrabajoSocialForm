package ar.edu.uns.cs.trabajosocialform.java_classes.DataModel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by Kevin (User) on 17/4/2018.
 */

@Entity(tableName = "salud")
public class Salud {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="cobertura")
    private String cobertura;
    @ColumnInfo(name="embarazo")
    private boolean embarazo;
    @ColumnInfo(name="fecha_estimada_embarazo")
    private Date fecha_estimada_embarazo;
    @ColumnInfo(name="discapacidad")
    private String discapacidad;
    @ColumnInfo(name="enfermedad_cronica")
    private String enfermedad_cronica;
    @ColumnInfo(name="independencia_funcional")
    private String independencia_funcional;


    @Ignore
    public Salud() {
    }

    public Salud(String cobertura, boolean embarazo, Date fecha_estimada_embarazo, String discapacidad, String enfermedad_cronica, String independencia_funcional) {
        this.cobertura = cobertura;
        this.embarazo = embarazo;
        this.fecha_estimada_embarazo = fecha_estimada_embarazo;
        this.discapacidad = discapacidad;
        this.enfermedad_cronica = enfermedad_cronica;
        this.independencia_funcional = independencia_funcional;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCobertura() {
        return cobertura;
    }

    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }

    public boolean isEmbarazo() {
        return embarazo;
    }

    public void setEmbarazo(boolean embarazo) {
        this.embarazo = embarazo;
    }

    public Date getFecha_estimada_embarazo() {
        return fecha_estimada_embarazo;
    }

    public void setFecha_estimada_embarazo(Date fecha_estimada_embarazo) {
        this.fecha_estimada_embarazo = fecha_estimada_embarazo;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
    }

    public String getEnfermedad_cronica() {
        return enfermedad_cronica;
    }

    public void setEnfermedad_cronica(String enfermedad_cronica) {
        this.enfermedad_cronica = enfermedad_cronica;
    }

    public String getIndependencia_funcional() {
        return independencia_funcional;
    }

    public void setIndependencia_funcional(String independencia_funcional) {
        this.independencia_funcional = independencia_funcional;
    }
}
