package ar.edu.uns.cs.trabajosocialform.Data.DataModel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * This Class represents the "Infraestructura Barrial" section of the form
 */

@Entity(tableName = "infraestructura_barrial")
public class InfraestructuraBarrial implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "infraestructura_calles")
    private String infraestructura_calles;
    @ColumnInfo(name = "iluminacion")
    private String iluminacion;
    @ColumnInfo(name = "inundacion_12_meses")
    private boolean inundacion;
    @ColumnInfo(name = "recoleccion_residuos")
    private boolean recoleccion_residuos;
    @ColumnInfo(name = "distancia_educacion")
    private String distancia_educacion;
    @ColumnInfo(name = "distancia_salud")
    private String distancia_salud;
    @ColumnInfo(name = "distancia_transporte")
    private String distancia_transporte;

    @Ignore
    public InfraestructuraBarrial() {
    }

    public InfraestructuraBarrial(String infraestructura_calles, String iluminacion, boolean inundacion, boolean recoleccion_residuos, String distancia_educacion, String distancia_salud, String distancia_transporte) {
        this.infraestructura_calles = infraestructura_calles;
        this.iluminacion = iluminacion;
        this.inundacion = inundacion;
        this.recoleccion_residuos = recoleccion_residuos;
        this.distancia_educacion = distancia_educacion;
        this.distancia_salud = distancia_salud;
        this.distancia_transporte = distancia_transporte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfraestructura_calles() {
        return infraestructura_calles;
    }

    public void setInfraestructura_calles(String infraestructura_calles) {
        this.infraestructura_calles = infraestructura_calles;
    }

    public String getIluminacion() {
        return iluminacion;
    }

    public void setIluminacion(String iluminacion) {
        this.iluminacion = iluminacion;
    }

    public boolean isInundacion() {
        return inundacion;
    }

    public void setInundacion(boolean inundacion) {
        this.inundacion = inundacion;
    }

    public boolean isRecoleccion_residuos() {
        return recoleccion_residuos;
    }

    public void setRecoleccion_residuos(boolean recoleccion_residuos) {
        this.recoleccion_residuos = recoleccion_residuos;
    }

    public String getDistancia_educacion() {
        return distancia_educacion;
    }

    public void setDistancia_educacion(String distancia_educacion) {
        this.distancia_educacion = distancia_educacion;
    }

    public String getDistancia_salud() {
        return distancia_salud;
    }

    public void setDistancia_salud(String distancia_salud) {
        this.distancia_salud = distancia_salud;
    }

    public String getDistancia_transporte() {
        return distancia_transporte;
    }

    public void setDistancia_transporte(String distancia_transporte) {
        this.distancia_transporte = distancia_transporte;
    }
}
