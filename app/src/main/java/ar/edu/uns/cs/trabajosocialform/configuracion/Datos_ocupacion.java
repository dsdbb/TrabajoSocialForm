package ar.edu.uns.cs.trabajosocialform.configuracion;

import java.io.Serializable;

/**
 * Created by Kevin (User) on 5/4/2018.
 */

public class Datos_ocupacion implements Serializable {

    private boolean condicion_de_actividad;
    private boolean puesto_de_trabajo;
    private boolean aporte_jubilatorio;
    private boolean duracion;
    private boolean tipo_de_actividad;
    private boolean calificacion;

    public Datos_ocupacion() {
    }

    public boolean isCondicion_de_actividad() {
        return condicion_de_actividad;
    }

    public void setCondicion_de_actividad(boolean condicion_de_actividad) {
        this.condicion_de_actividad = condicion_de_actividad;
    }

    public boolean isPuesto_de_trabajo() {
        return puesto_de_trabajo;
    }

    public void setPuesto_de_trabajo(boolean puesto_de_trabajo) {
        this.puesto_de_trabajo = puesto_de_trabajo;
    }

    public boolean isAporte_jubilatorio() {
        return aporte_jubilatorio;
    }

    public void setAporte_jubilatorio(boolean aporte_jubilatorio) {
        this.aporte_jubilatorio = aporte_jubilatorio;
    }

    public boolean isDuracion() {
        return duracion;
    }

    public void setDuracion(boolean duracion) {
        this.duracion = duracion;
    }

    public boolean isTipo_de_actividad() {
        return tipo_de_actividad;
    }

    public void setTipo_de_actividad(boolean tipo_de_actividad) {
        this.tipo_de_actividad = tipo_de_actividad;
    }

    public boolean isCalificacion() {
        return calificacion;
    }

    public void setCalificacion(boolean calificacion) {
        this.calificacion = calificacion;
    }

    public boolean required(){
        return isCondicion_de_actividad() || isAporte_jubilatorio() || isPuesto_de_trabajo() || isTipo_de_actividad()
                || isCalificacion() || isDuracion();
    }
}
