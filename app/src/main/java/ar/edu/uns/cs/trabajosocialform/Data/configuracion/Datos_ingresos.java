package ar.edu.uns.cs.trabajosocialform.Data.configuracion;

import java.io.Serializable;

/**
 * This Class specifies if fields belonging to Ingresos are needed in the form
 */
public class Datos_ingresos implements Serializable {

    private boolean ingresos_laborales;
    /*ingresos no laborales o programas sociales con transferencia de ingresos*/
    private boolean ingresos_no_laborales;
    /*programas sociales sin transferencia de ingresos*/
    private boolean programas_sociales_sti;

    public Datos_ingresos() {
    }

    public boolean isIngresos_laborales() {
        return ingresos_laborales;
    }

    public void setIngresos_laborales(boolean ingresos_laborales) {
        this.ingresos_laborales = ingresos_laborales;
    }

    public boolean isIngresos_no_laborales() {
        return ingresos_no_laborales;
    }

    public void setIngresos_no_laborales(boolean ingresos_no_laborales) {
        this.ingresos_no_laborales = ingresos_no_laborales;
    }

    public boolean isPrograma_sociales_sti() {
        return programas_sociales_sti;
    }

    public void setPrograma_sociales_sti(boolean programas_sociales_sti) {
        this.programas_sociales_sti = programas_sociales_sti;
    }

    public boolean required(){
        return isIngresos_laborales() || isIngresos_no_laborales() || isPrograma_sociales_sti();
    }
}
