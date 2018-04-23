package ar.edu.uns.cs.trabajosocialform.configuracion;

import java.io.Serializable;

/**
 * Created by Kevin (User) on 5/4/2018.
 */

public class Configuracion implements Serializable {

    private boolean datos_generales;
    private boolean req_datos_solicitante;
    private Datos_solicitante datos_solicitante;
    private boolean req_domicilio;
    private Datos_domicilio datos_domicilio;
    private boolean req_datos_apoderado;
    private Datos_apoderado datos_apoderado;
    private boolean req_grupo_familiar;
    private Datos_grupoFamiliar datos_grupo_familiar;
    private boolean req_situacion_habitacional;
    private Datos_situacionHabitacional datos_situacion_habitacional;
    private boolean req_caracteristicas_vivienda;
    private Datos_caracteristicasVivienda datos_caracteristicas_vivienda;
    private boolean req_infraestructura_barrial;
    private Datos_InfraestructuraBarrial datos_infraestructura_barrial;
    private boolean req_observaciones;


    public Configuracion() {
        datos_solicitante = new Datos_solicitante();
        datos_domicilio = new Datos_domicilio();
        datos_apoderado = new Datos_apoderado();
        datos_grupo_familiar = new Datos_grupoFamiliar();
        datos_situacion_habitacional = new Datos_situacionHabitacional();
        datos_caracteristicas_vivienda = new Datos_caracteristicasVivienda();
        datos_infraestructura_barrial = new Datos_InfraestructuraBarrial();
    }

    public boolean isDatos_generales() {
        return datos_generales;
    }

    public void setDatos_generales(boolean datos_generales) {
        this.datos_generales = datos_generales;
    }

    public boolean isReq_datos_solicitante() {
        return req_datos_solicitante;
    }

    public void setReq_datos_solicitante(boolean req_datos_solicitante) {
        this.req_datos_solicitante = req_datos_solicitante;
    }

    public Datos_solicitante getDatos_solicitante() {
        return datos_solicitante;
    }

    public void setDatos_solicitante(Datos_solicitante datos_solicitante) {
        this.datos_solicitante = datos_solicitante;
    }

    public boolean isReq_domicilio() {
        return req_domicilio;
    }

    public void setReq_domicilio(boolean req_domicilio) {
        this.req_domicilio = req_domicilio;
    }

    public Datos_domicilio getDatos_domicilio() {
        return datos_domicilio;
    }

    public void setDatos_domicilio(Datos_domicilio datos_domicilio) {
        this.datos_domicilio = datos_domicilio;
    }

    public boolean isReq_datos_apoderado() {
        return req_datos_apoderado;
    }

    public void setReq_datos_apoderado(boolean req_datos_apoderado) {
        this.req_datos_apoderado = req_datos_apoderado;
    }

    public Datos_apoderado getDatos_apoderado() {
        return datos_apoderado;
    }

    public void setDatos_apoderado(Datos_apoderado datos_apoderado) {
        this.datos_apoderado = datos_apoderado;
    }

    public boolean isReq_grupo_familiar() {
        return req_grupo_familiar;
    }

    public void setReq_grupo_familiar(boolean req_grupo_familiar) {
        this.req_grupo_familiar = req_grupo_familiar;
    }

    public Datos_grupoFamiliar getDatos_grupo_familiar() {
        return datos_grupo_familiar;
    }

    public void setDatos_grupo_familiar(Datos_grupoFamiliar datos_grupo_familiar) {
        this.datos_grupo_familiar = datos_grupo_familiar;
    }

    public boolean isReq_situacion_habitacional() {
        return req_situacion_habitacional;
    }

    public void setReq_situacion_habitacional(boolean req_situacion_habitacional) {
        this.req_situacion_habitacional = req_situacion_habitacional;
    }

    public Datos_situacionHabitacional getDatos_situacion_habitacional() {
        return datos_situacion_habitacional;
    }

    public void setDatos_situacion_habitacional(Datos_situacionHabitacional datos_situacion_habitacional) {
        this.datos_situacion_habitacional = datos_situacion_habitacional;
    }

    public boolean isReq_caracteristicas_vivienda() {
        return req_caracteristicas_vivienda;
    }

    public void setReq_caracteristicas_vivienda(boolean req_caracteristicas_vivienda) {
        this.req_caracteristicas_vivienda = req_caracteristicas_vivienda;
    }

    public Datos_caracteristicasVivienda getDatos_caracteristicas_vivienda() {
        return datos_caracteristicas_vivienda;
    }

    public void setDatos_caracteristicas_vivienda(Datos_caracteristicasVivienda datos_caracteristicas_vivienda) {
        this.datos_caracteristicas_vivienda = datos_caracteristicas_vivienda;
    }

    public boolean isReq_infraestructura_barrial() {
        return req_infraestructura_barrial;
    }

    public void setReq_infraestructura_barrial(boolean req_infraestructura_barrial) {
        this.req_infraestructura_barrial = req_infraestructura_barrial;
    }

    public Datos_InfraestructuraBarrial getDatos_infraestructura_barrial() {
        return datos_infraestructura_barrial;
    }

    public void setDatos_infraestructura_barrial(Datos_InfraestructuraBarrial datos_infraestructura_barrial) {
        this.datos_infraestructura_barrial = datos_infraestructura_barrial;
    }

    public boolean isReq_observaciones() {
        return req_observaciones;
    }

    public void setReq_observaciones(boolean req_observaciones) {
        this.req_observaciones = req_observaciones;
    }
}
