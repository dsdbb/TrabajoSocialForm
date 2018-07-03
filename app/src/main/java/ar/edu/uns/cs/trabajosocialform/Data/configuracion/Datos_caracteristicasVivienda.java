package ar.edu.uns.cs.trabajosocialform.Data.configuracion;

import java.io.Serializable;

/**
 * This Class specifies if fields belonging to CaracteristicasVivienda are needed in the form
 */

public class Datos_caracteristicasVivienda implements Serializable {

    private boolean techo;
    private boolean pisos;
    private boolean banio;
    private boolean paredes;
    private boolean servicios;
    private boolean cocina;

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

    public boolean isBanio() {
        return banio;
    }

    public void setBanio(boolean banio) {
        this.banio = banio;
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

    public boolean isCocina() {return cocina;}

    public void setCocina(boolean cocina) {this.cocina = cocina; }

    public boolean required(){
        return isTecho() || isParedes() || isPisos() || isBanio() || isCocina() || isServicios();
    }
}
