package ar.edu.uns.cs.trabajosocialform.java_classes.configuracion;

/**
 * Created by Kevin (User) on 5/4/2018.
 */

public class Datos_solicitante {

    /*Datos del solicitante*/
    private boolean apellidos_solicitante;
    private boolean nombres_solicitante;
    private boolean cuil_solicitante;
    private boolean telefono_principal_solicitante;
    private boolean otro_telefono;


    public Datos_solicitante() {}

    public boolean isApellidos_solicitante() {
        return apellidos_solicitante;
    }

    public void setApellidos_solicitante(boolean apellidos_solicitante) {
        this.apellidos_solicitante = apellidos_solicitante;
    }

    public boolean isNombres_solicitante() {
        return nombres_solicitante;
    }

    public void setNombres_solicitante(boolean nombres_solicitante) {
        this.nombres_solicitante = nombres_solicitante;
    }

    public boolean isCuil_solicitante() {
        return cuil_solicitante;
    }

    public void setCuil_solicitante(boolean cuil_solicitante) {
        this.cuil_solicitante = cuil_solicitante;
    }

    public boolean isTelefono_principal_solicitante() {
        return telefono_principal_solicitante;
    }

    public void setTelefono_principal_solicitante(boolean telefono_principal_solicitante) {
        this.telefono_principal_solicitante = telefono_principal_solicitante;
    }

    public boolean isOtro_telefono() {
        return otro_telefono;
    }

    public void setOtro_telefono(boolean otro_telefono) {
        this.otro_telefono = otro_telefono;
    }
}
