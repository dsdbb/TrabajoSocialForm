package ar.edu.uns.cs.trabajosocialform.java_classes.configuracion;

import java.io.Serializable;

/**
 * Created by Kevin (User) on 5/4/2018.
 */

public class Datos_apoderado  implements Serializable {

    /*Datos para el apoderado*/
    private boolean apellidos_apoderado;
    private boolean nombres_apoderado;
    private boolean cuil_apoderado;
    private boolean fecha_nac_apoderado;
    private boolean telefono_principal_apoderado;
    private boolean motivos_del_poder;

    public Datos_apoderado() {
    }

    public boolean isApellidos_apoderado() {
        return apellidos_apoderado;
    }

    public void setApellidos_apoderado(boolean apellidos_apoderado) {
        this.apellidos_apoderado = apellidos_apoderado;
    }

    public boolean isNombres_apoderado() {
        return nombres_apoderado;
    }

    public void setNombres_apoderado(boolean nombres_apoderado) {
        this.nombres_apoderado = nombres_apoderado;
    }

    public boolean isCuil_apoderado() {
        return cuil_apoderado;
    }

    public void setCuil_apoderado(boolean cuil_apoderado) {
        this.cuil_apoderado = cuil_apoderado;
    }

    public boolean isFecha_nac_apoderado() {
        return fecha_nac_apoderado;
    }

    public void setFecha_nac_apoderado(boolean fecha_nac_apoderado) {
        this.fecha_nac_apoderado = fecha_nac_apoderado;
    }

    public boolean isTelefono_principal_apoderado() {
        return telefono_principal_apoderado;
    }

    public void setTelefono_principal_apoderado(boolean telefono_principal_apoderado) {
        this.telefono_principal_apoderado = telefono_principal_apoderado;
    }

    public boolean isMotivos_del_poder() {
        return motivos_del_poder;
    }

    public void setMotivos_del_poder(boolean motivos_del_poder) {
        this.motivos_del_poder = motivos_del_poder;
    }
}
