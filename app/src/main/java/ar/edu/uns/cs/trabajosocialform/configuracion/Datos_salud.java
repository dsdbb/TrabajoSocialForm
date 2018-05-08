package ar.edu.uns.cs.trabajosocialform.configuracion;

import java.io.Serializable;

/**
 * Created by Kevin (User) on 5/4/2018.
 */

public class Datos_salud implements Serializable {

    private boolean cobertura;
    private boolean embarazo;
    private boolean discapacidad;
    private boolean enfermedad_cronica;
    private boolean independencia_funcional;

    public Datos_salud() {
    }

    public boolean isCobertura() {
        return cobertura;
    }

    public void setCobertura(boolean cobertura) {
        this.cobertura = cobertura;
    }

    public boolean isEmbarazo() {
        return embarazo;
    }

    public void setEmbarazo(boolean embarazo) {
        this.embarazo = embarazo;
    }

    public boolean isDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(boolean discapacidad) {
        this.discapacidad = discapacidad;
    }

    public boolean isEnfermedad_cronica() {
        return enfermedad_cronica;
    }

    public void setEnfermedad_cronica(boolean enfermedad_cronica) {
        this.enfermedad_cronica = enfermedad_cronica;
    }

    public boolean isIndependencia_funcional() {
        return independencia_funcional;
    }

    public void setIndependencia_funcional(boolean independencia_funcional) {
        this.independencia_funcional = independencia_funcional;
    }
}