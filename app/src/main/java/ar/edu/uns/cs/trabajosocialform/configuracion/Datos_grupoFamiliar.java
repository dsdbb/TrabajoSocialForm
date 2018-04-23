package ar.edu.uns.cs.trabajosocialform.configuracion;

import java.io.Serializable;

/**
 * Created by Kevin (User) on 5/4/2018.
 */

public class Datos_grupoFamiliar implements Serializable {

    /*Datos para el grupo familiar (cada uno)*/
    private boolean apellidos;
    private boolean nombres;
    private boolean sexo;
    private boolean nacion;
    private boolean cuil;
    private boolean req_ocupacion;
    private Datos_ocupacion datos_ocupacion;
    private boolean req_ingresos;
    private Datos_ingresos datos_ingresos;
    private boolean fecha_nac;
    private boolean estado_civil;
    private boolean vinculo;
    private boolean nucleo;
    private boolean educacion;
    private boolean req_salud;
    private Datos_salud datos_salud;


    public Datos_grupoFamiliar() {
        datos_ocupacion = new Datos_ocupacion();
        datos_ingresos = new Datos_ingresos();
        datos_salud = new Datos_salud();
    }

    public boolean isApellidos() {
        return apellidos;
    }

    public void setApellidos(boolean apellidos) {
        this.apellidos = apellidos;
    }

    public boolean isNombres() {
        return nombres;
    }

    public void setNombres(boolean nombres) {
        this.nombres = nombres;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public boolean isNacion() {
        return nacion;
    }

    public void setNacion(boolean nacion) {
        this.nacion = nacion;
    }

    public boolean isCuil() {
        return cuil;
    }

    public void setCuil(boolean cuil) {
        this.cuil = cuil;
    }

    public boolean isReq_ocupacion() {
        return req_ocupacion;
    }

    public void setReq_ocupacion(boolean req_ocupacion) {
        this.req_ocupacion = req_ocupacion;
    }

    public Datos_ocupacion getDatos_ocupacion() {
        return datos_ocupacion;
    }

    public void setDatos_ocupacion(Datos_ocupacion datos_ocupacion) {
        this.datos_ocupacion = datos_ocupacion;
    }

    public boolean isReq_ingresos() {
        return req_ingresos;
    }

    public void setReq_ingresos(boolean req_ingresos) {
        this.req_ingresos = req_ingresos;
    }

    public Datos_ingresos getDatos_ingresos() {
        return datos_ingresos;
    }

    public void setDatos_ingresos(Datos_ingresos datos_ingresos) {
        this.datos_ingresos = datos_ingresos;
    }

    public boolean isFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(boolean fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public boolean isEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(boolean estado_civil) {
        this.estado_civil = estado_civil;
    }

    public boolean isVinculo() {
        return vinculo;
    }

    public void setVinculo(boolean vinculo) {
        this.vinculo = vinculo;
    }

    public boolean isNucleo() {
        return nucleo;
    }

    public void setNucleo(boolean nucleo) {
        this.nucleo = nucleo;
    }

    public boolean isEducacion() {
        return educacion;
    }

    public void setEducacion(boolean educacion) {
        this.educacion = educacion;
    }

    public boolean isReq_salud() {
        return req_salud;
    }

    public void setReq_salud(boolean req_salud) {
        this.req_salud = req_salud;
    }

    public Datos_salud getDatos_salud() {
        return datos_salud;
    }

    public void setDatos_salud(Datos_salud datos_salud) {
        this.datos_salud = datos_salud;
    }
}