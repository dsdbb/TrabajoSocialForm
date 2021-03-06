package ar.edu.uns.cs.trabajosocialform.Data.configuracion;

import java.io.Serializable;

/**
 * This Class specifies if fields belonging to InfraestructuraBarrial are needed in the form
 */

public class Datos_InfraestructuraBarrial implements Serializable {

    private boolean calles;
    private boolean iluminacion;
    private boolean inundacion;
    private boolean recoleccion_basura;
    private boolean distancias;


    public Datos_InfraestructuraBarrial() {
    }

    public boolean isCalles() {
        return calles;
    }

    public void setCalles(boolean calles) {
        this.calles = calles;
    }

    public boolean isIluminacion() {
        return iluminacion;
    }

    public void setIluminacion(boolean iluminacion) {
        this.iluminacion = iluminacion;
    }

    public boolean isInundacion() {
        return inundacion;
    }

    public void setInundacion(boolean inundacion) {
        this.inundacion = inundacion;
    }

    public boolean isRecoleccion_basura() {
        return recoleccion_basura;
    }

    public void setRecoleccion_basura(boolean recoleccion_basura) {
        this.recoleccion_basura = recoleccion_basura;
    }

    public boolean isDistancias() {
        return distancias;
    }

    public void setDistancias(boolean distancias) {
        this.distancias = distancias;
    }

    public boolean required(){
        return isCalles() || isDistancias() || isIluminacion() || isInundacion() || isRecoleccion_basura();
    }
}
