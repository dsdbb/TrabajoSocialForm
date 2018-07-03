package ar.edu.uns.cs.trabajosocialform.Data.DataModel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * This Class represents the complete form, having the reference to id of the other sections
 */
@Entity(tableName = "formularios",foreignKeys = {@ForeignKey(entity = Solicitante.class,
                                                                        parentColumns = "id",
                                                                        childColumns = "solicitante_id",
                                                                        onDelete = CASCADE),
                                                                @ForeignKey(entity = Apoderado.class,
                                                                        parentColumns = "id",
                                                                        childColumns = "apoderado_id",
                                                                        onDelete = CASCADE),
                                                                @ForeignKey(entity = Domicilio.class,
                                                                        parentColumns = "id",
                                                                        childColumns = "domicilio_id",
                                                                        onDelete = CASCADE),
                                                                @ForeignKey(entity = InfraestructuraBarrial.class,
                                                                        parentColumns = "id",
                                                                        childColumns = "infraestructura_barrial_id",
                                                                        onDelete = CASCADE),
                                                                @ForeignKey(entity = CaracteristicasVivienda.class,
                                                                        parentColumns = "id",
                                                                        childColumns = "caracteristicas_vivienda_id",
                                                                        onDelete = CASCADE),
                                                                @ForeignKey(entity = SituacionHabitacional.class,
                                                                        parentColumns = "id",
                                                                        childColumns = "situacion_habitacional_id",
                                                                        onDelete = CASCADE)})
public class Formulario implements Serializable{

    @PrimaryKey(autoGenerate = true)
    private int id;

    /*DATOS GENERALES DEL FORMULARIO*/
    @ColumnInfo(name = "fecha_inscripcion")
    private Date fecha_inscripcion;
    @ColumnInfo(name = "fecha_ingreso_sistema")
    private Date fecha_ingreso_sistema;
    @ColumnInfo(name = "programas_sociales_solicitados")
    private List<String> programasSocialesSolicitados;
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
    @ColumnInfo(name = "infraestructura_barrial_id")
    private int infraestructuraBarrialId;
    @ColumnInfo(name = "caracteristicas_vivienda_id")
    private int caracteristicasViviendaId;
    @ColumnInfo(name = "situacion_habitacional_id")
    private int situacionHabitacionalId;


    @Ignore
    private Solicitante solicitante;
    @Ignore
    private Apoderado apoderado;
    @Ignore
    private Domicilio domicilio;
    @Ignore
    private List<Familiar> familiares;
    @Ignore
    private InfraestructuraBarrial infraestructuraBarrial;
    @Ignore
    private SituacionHabitacional situacionHabitacional;
    @Ignore
    private CaracteristicasVivienda caracteristicasVivienda;

    @Ignore
    public Formulario(){
        familiares = new ArrayList<Familiar>();
        programasSocialesSolicitados = new ArrayList<String>();
    }


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
        familiares = new ArrayList<Familiar>();
        programasSocialesSolicitados = new ArrayList<String>();

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

    public List<String> getProgramasSocialesSolicitados() {
        return programasSocialesSolicitados;
    }

    public void setProgramasSocialesSolicitados(List<String> programasSocialesSolicitados) {
        this.programasSocialesSolicitados = programasSocialesSolicitados;
    }

    public List<Familiar> getFamiliares() {
        return familiares;
    }

    public void setFamiliares(List<Familiar> familiares) {
        this.familiares = familiares;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }

    public Apoderado getApoderado() {
        return apoderado;
    }

    public void setApoderado(Apoderado apoderado) {
        this.apoderado = apoderado;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public InfraestructuraBarrial getInfraestructuraBarrial() {
        return infraestructuraBarrial;
    }

    public void setInfraestructuraBarrial(InfraestructuraBarrial infraestructuraBarrial) {
        this.infraestructuraBarrial = infraestructuraBarrial;
    }

    public SituacionHabitacional getSituacionHabitacional() {
        return situacionHabitacional;
    }

    public void setSituacionHabitacional(SituacionHabitacional situacionHabitacional) {
        this.situacionHabitacional = situacionHabitacional;
    }

    public CaracteristicasVivienda getCaracteristicasVivienda() {
        return caracteristicasVivienda;
    }

    public void setCaracteristicasVivienda(CaracteristicasVivienda caracteristicasVivienda) {
        this.caracteristicasVivienda = caracteristicasVivienda;
    }

    public void addPlanSocialSolicitado(String plan){
        programasSocialesSolicitados.add(plan);
    }
}
