package ar.edu.uns.cs.trabajosocialform.DataModel;

import java.io.Serializable;

/**
 * This Class represents the "Ingreso no laboral" fields of the form related to a family member
 */

public class IngresoNoLaboral implements Serializable {

    private int id;
    private String origen;
    private Integer monto;

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

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }
}
