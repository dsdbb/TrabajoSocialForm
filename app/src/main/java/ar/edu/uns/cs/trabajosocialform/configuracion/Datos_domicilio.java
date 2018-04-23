package ar.edu.uns.cs.trabajosocialform.configuracion;

import java.io.Serializable;

/**
 * Created by Kevin (User) on 5/4/2018.
 */

public class Datos_domicilio implements Serializable {

    /*Datos del domicilio*/
    private boolean calle;
    private boolean numero;
    private boolean manzana;
    private boolean monoblock_torre;
    private boolean piso;
    private boolean acc_int;
    private boolean casa_dpto;
    private boolean entre_calles;
    private boolean barrio;
    private boolean delegacion;
    private boolean localidad;

    public Datos_domicilio() {
    }

    public boolean isCalle() {
        return calle;
    }

    public void setCalle(boolean calle) {
        this.calle = calle;
    }

    public boolean isNumero() {
        return numero;
    }

    public void setNumero(boolean numero) {
        this.numero = numero;
    }

    public boolean isManzana() {
        return manzana;
    }

    public void setManzana(boolean manzana) {
        this.manzana = manzana;
    }

    public boolean isMonoblock_torre() {
        return monoblock_torre;
    }

    public void setMonoblock_torre(boolean monoblock_torre) {
        this.monoblock_torre = monoblock_torre;
    }

    public boolean isPiso() {
        return piso;
    }

    public void setPiso(boolean piso) {
        this.piso = piso;
    }

    public boolean isAcc_int() {
        return acc_int;
    }

    public void setAcc_int(boolean acc_int) {
        this.acc_int = acc_int;
    }

    public boolean isCasa_dpto() {
        return casa_dpto;
    }

    public void setCasa_dpto(boolean casa_dpto) {
        this.casa_dpto = casa_dpto;
    }

    public boolean isEntre_calles() {
        return entre_calles;
    }

    public void setEntre_calles(boolean entre_calles) {
        this.entre_calles = entre_calles;
    }

    public boolean isBarrio() {
        return barrio;
    }

    public void setBarrio(boolean barrio) {
        this.barrio = barrio;
    }

    public boolean isDelegacion() {
        return delegacion;
    }

    public void setDelegacion(boolean delegacion) {
        this.delegacion = delegacion;
    }

    public boolean isLocalidad() {
        return localidad;
    }

    public void setLocalidad(boolean localidad) {
        this.localidad = localidad;
    }
}
