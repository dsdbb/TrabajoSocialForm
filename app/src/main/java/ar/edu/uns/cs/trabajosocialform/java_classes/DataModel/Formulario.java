package ar.edu.uns.cs.trabajosocialform.java_classes.DataModel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;
import java.util.List;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Kevin (User) on 17/4/2018.
 */
@Entity(tableName = "datos_generales",foreignKeys = {@ForeignKey(entity = Solicitante.class,
                                                                        parentColumns = "id",
                                                                        childColumns = "solicitanteId",
                                                                        onDelete = CASCADE),
                                                                @ForeignKey(entity = Apoderado.class,
                                                                        parentColumns = "id",
                                                                        childColumns = "apoderadoId",
                                                                        onDelete = CASCADE),
                                                                @ForeignKey(entity = Domicilio.class,
                                                                        parentColumns = "id",
                                                                        childColumns = "domicilioId",
                                                                        onDelete = CASCADE),
                                                                @ForeignKey(entity = InfraestructuraBarrial.class,
                                                                        parentColumns = "id",
                                                                        childColumns = "infraestructuraBarrialId",
                                                                        onDelete = CASCADE),
                                                                @ForeignKey(entity = CaracteristicasVivienda.class,
                                                                        parentColumns = "id",
                                                                        childColumns = "caracteristicasViviendaId",
                                                                        onDelete = CASCADE),
                                                                @ForeignKey(entity = SituacionHabitacional.class,
                                                                        parentColumns = "id",
                                                                        childColumns = "situacionHabitacionalId",
                                                                        onDelete = CASCADE)})
public class Formulario {

    @PrimaryKey(autoGenerate = true)
    private int id;

    /*DATOS GENERALES DEL FORMULARIO*/
    @ColumnInfo(name = "fecha_inscripcion")
    private Date fecha_inscripcion;
    @ColumnInfo(name = "fecha_ingreso_sistema")
    private Date fecha_ingreso_sistema;
    //@ColumnInfo(name = "programas_sociales_solicitados_id")
    // private int programasSocialesSolicitadosid;
    @ColumnInfo(name = "observaciones")
    private String observaciones;

    @ColumnInfo(name = "nombre_entrevistador")
    private String nombreEntrevistador;
    @ColumnInfo(name = "apellido_entrevistador")
    private String apellidoEntrevistador;

    @ColumnInfo(name = "solicitante_id")
    private int solicitanteId;
    @ColumnInfo(name = "apoderado_id")
    private int apoderadoId;
    @ColumnInfo(name = "domicilio_id")
    private int domicilioId;
    //private int List<Familiar> familiares;
    @ColumnInfo(name = "infraestructura_barrial_id")
    private int infraestructuraBarrialId;
    @ColumnInfo(name = "caracteristicas_vivienda_id")
    private int caracteristicasViviendaId;
    @ColumnInfo(name = "situacion_habitacional_id")
    private int situacionHabitacionalId;

    public Formulario(int id, Date fecha_inscripcion, Date fecha_ingreso_sistema, String observaciones, String nombreEntrevistador, String apellidoEntrevistador, int solicitanteId, int apoderadoId, int domicilioId, int infraestructuraBarrialId, int caracteristicasViviendaId, int situacionHabitacionalId) {
        this.id = id;
        this.fecha_inscripcion = fecha_inscripcion;
        this.fecha_ingreso_sistema = fecha_ingreso_sistema;
        this.observaciones = observaciones;
        this.nombreEntrevistador = nombreEntrevistador;
        this.apellidoEntrevistador = apellidoEntrevistador;
        this.solicitanteId = solicitanteId;
        this.apoderadoId = apoderadoId;
        this.domicilioId = domicilioId;
        this.infraestructuraBarrialId = infraestructuraBarrialId;
        this.caracteristicasViviendaId = caracteristicasViviendaId;
        this.situacionHabitacionalId = situacionHabitacionalId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    public void setFecha_inscripcion(Date fecha_inscripcion) {
        this.fecha_inscripcion = fecha_inscripcion;
    }

    public Date getFecha_ingreso_sistema() {
        return fecha_ingreso_sistema;
    }

    public void setFecha_ingreso_sistema(Date fecha_ingreso_sistema) {
        this.fecha_ingreso_sistema = fecha_ingreso_sistema;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getNombreEntrevistador() {
        return nombreEntrevistador;
    }

    public void setNombreEntrevistador(String nombreEntrevistador) {
        this.nombreEntrevistador = nombreEntrevistador;
    }

    public String getApellidoEntrevistador() {
        return apellidoEntrevistador;
    }

    public void setApellidoEntrevistador(String apellidoEntrevistador) {
        this.apellidoEntrevistador = apellidoEntrevistador;
    }

    public int getSolicitanteId() {
        return solicitanteId;
    }

    public void setSolicitanteId(int solicitanteId) {
        this.solicitanteId = solicitanteId;
    }

    public int getApoderadoId() {
        return apoderadoId;
    }

    public void setApoderadoId(int apoderadoId) {
        this.apoderadoId = apoderadoId;
    }

    public int getDomicilioId() {
        return domicilioId;
    }

    public void setDomicilioId(int domicilioId) {
        this.domicilioId = domicilioId;
    }

    public int getInfraestructuraBarrialId() {
        return infraestructuraBarrialId;
    }

    public void setInfraestructuraBarrialId(int infraestructuraBarrialId) {
        this.infraestructuraBarrialId = infraestructuraBarrialId;
    }

    public int getCaracteristicasViviendaId() {
        return caracteristicasViviendaId;
    }

    public void setCaracteristicasViviendaId(int caracteristicasViviendaId) {
        this.caracteristicasViviendaId = caracteristicasViviendaId;
    }

    public int getSituacionHabitacionalId() {
        return situacionHabitacionalId;
    }

    public void setSituacionHabitacionalId(int situacionHabitacionalId) {
        this.situacionHabitacionalId = situacionHabitacionalId;
    }
}
