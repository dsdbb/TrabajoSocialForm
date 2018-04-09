package ar.edu.uns.cs.trabajosocialform.java_classes.configuracion;

/**
 * Created by Kevin (User) on 5/4/2018.
 */

public class Datos_ingresos {

    private boolean ingresos_laborales;
    /*ingresos no laborales o programas sociales con transferencia de ingresos*/
    private boolean ingresos_no_laborales;
    /*programas sociales sin transferencia de ingresos*/
    private boolean programa_sociales_sti;

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
        return programa_sociales_sti;
    }

    public void setPrograma_sociales_sti(boolean programa_sociales_sti) {
        this.programa_sociales_sti = programa_sociales_sti;
    }
}
