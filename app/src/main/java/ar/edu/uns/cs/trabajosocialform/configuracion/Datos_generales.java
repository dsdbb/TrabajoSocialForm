package ar.edu.uns.cs.trabajosocialform.configuracion;

import java.io.Serializable;

/**
 * Created by Kevin (User) on 5/4/2018.
 */

public class Datos_generales implements Serializable {

    /*Datos generales del formulario*/
    private boolean fecha_inscripcion;
    private boolean id_formulario;
    private boolean Fecha_ingreso_al_sistema;
    private boolean programas_sociales_solicitados;
    private boolean entrevistador;

    public Datos_generales() {}

    public boolean isFecha_inscripcion() {
        return fecha_inscripcion;
    }

    public void setFecha_inscripcion(boolean fecha_inscripcion) {
        this.fecha_inscripcion = fecha_inscripcion;
    }

    public boolean isId_formulario() {
        return id_formulario;
    }

    public void setId_formulario(boolean id_formulario) {
        this.id_formulario = id_formulario;
    }

    public boolean isFecha_ingreso_al_sistema() {
        return Fecha_ingreso_al_sistema;
    }

    public void setFecha_ingreso_al_sistema(boolean fecha_ingreso_al_sistema) {
        Fecha_ingreso_al_sistema = fecha_ingreso_al_sistema;
    }

    public boolean isProgramas_sociales_solicitados() {
        return programas_sociales_solicitados;
    }

    public void setProgramas_sociales_solicitados(boolean programas_sociales_solicitados) {
        this.programas_sociales_solicitados = programas_sociales_solicitados;
    }

    public boolean isEntrevistador() {
        return entrevistador;
    }

    public void setEntrevistador(boolean entrevistador) {
        this.entrevistador = entrevistador;
    }
}
