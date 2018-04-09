package ar.edu.uns.cs.trabajosocialform.java_classes.configuracion;

/**
 * Created by Kevin (User) on 5/4/2018.
 */

public class Datos_caracteristicasVivienda {

    private boolean techo;
    private boolean pisos;
    private boolean baño;
    private boolean paredes;
    private boolean servicios;

    public Datos_caracteristicasVivienda() {
    }

    public boolean isTecho() {
        return techo;
    }

    public void setTecho(boolean techo) {
        this.techo = techo;
    }

    public boolean isPisos() {
        return pisos;
    }

    public void setPisos(boolean pisos) {
        this.pisos = pisos;
    }

    public boolean isBaño() {
        return baño;
    }

    public void setBaño(boolean baño) {
        this.baño = baño;
    }

    public boolean isParedes() {
        return paredes;
    }

    public void setParedes(boolean paredes) {
        this.paredes = paredes;
    }

    public boolean isServicios() {
        return servicios;
    }

    public void setServicios(boolean servicios) {
        this.servicios = servicios;
    }
}
