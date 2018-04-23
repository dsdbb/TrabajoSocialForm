package ar.edu.uns.cs.trabajosocialform.DataModel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Kevin (User) on 17/4/2018.
 */
@Entity(tableName = "familiar",foreignKeys = {@ForeignKey(entity = Ocupacion.class,
                                                        parentColumns = "id",
                                                        childColumns = "ocupacion_id",
                                                        onDelete = CASCADE),
                                            @ForeignKey(entity = Ingreso.class,
                                                    parentColumns = "id",
                                                    childColumns = "ingreso_id",
                                                    onDelete = CASCADE),
                                            @ForeignKey(entity = Salud.class,
                                                    parentColumns = "id",
                                                    childColumns = "salud_id",
                                                    onDelete = CASCADE)})
public class Familiar  implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "nombres")
    private String nombres;
    @ColumnInfo(name = "apellidos")
    private String apellidos;
    @ColumnInfo(name = "sexo")
    private String sexo;
    @ColumnInfo(name = "nacion")
    private String nacion;
    @ColumnInfo(name = "cuil")
    private Integer cuil;
    @ColumnInfo(name = "fecha_nacimiento")
    private Date fecha_nacimiento;
    @ColumnInfo(name = "estado_civil")
    private String estado_civil;
    @ColumnInfo(name = "vinculo")
    private String vinculo;
    @ColumnInfo(name = "nucleo")
    private String nucleo;
    @ColumnInfo(name = "nivel_educativo")
    private String nivel_educativo;
    @ColumnInfo(name = "capacitacion")
    private String capacitacion;
    @ColumnInfo(name = "ocupacion_id")
    private int ocupacionId;
    @ColumnInfo(name = "ingreso_id")
    private int ingresoId;
    @ColumnInfo(name = "salud_id")
    private int saludId;

    @Ignore
    private Ocupacion ocupacion;
    @Ignore
    private Ingreso ingreso;
    @Ignore
    private Salud salud;


    @Ignore
    public Familiar() {
    }
    @Ignore
    public Familiar(String nombres, String apellidos, String sexo, String nacion, Integer cuil, Date fecha_nacimiento, String estado_civil, String vinculo, String nucleo, String nivel_educativo, String capacitacion, Ocupacion ocupacion, Ingreso ingreso, Salud salud) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.nacion = nacion;
        this.cuil = cuil;
        this.fecha_nacimiento = fecha_nacimiento;
        this.estado_civil = estado_civil;
        this.vinculo = vinculo;
        this.nucleo = nucleo;
        this.nivel_educativo = nivel_educativo;
        this.capacitacion = capacitacion;
        this.ocupacion = ocupacion;
        this.ingreso = ingreso;
        this.salud = salud;
    }


    public Familiar(String nombres, String apellidos, String sexo, String nacion, Integer cuil, Date fecha_nacimiento, String estado_civil, String vinculo, String nucleo, String nivel_educativo, String capacitacion, int ocupacionId, int ingresoId, int saludId) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.nacion = nacion;
        this.cuil = cuil;
        this.fecha_nacimiento = fecha_nacimiento;
        this.estado_civil = estado_civil;
        this.vinculo = vinculo;
        this.nucleo = nucleo;
        this.nivel_educativo = nivel_educativo;
        this.capacitacion = capacitacion;
        this.ocupacionId = ocupacionId;
        this.ingresoId = ingresoId;
        this.saludId = saludId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNacion() {
        return nacion;
    }

    public void setNacion(String nacion) {
        this.nacion = nacion;
    }

    public Integer getCuil() {
        return cuil;
    }

    public void setCuil(Integer cuil) {
        this.cuil = cuil;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getVinculo() {
        return vinculo;
    }

    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }

    public String getNucleo() {
        return nucleo;
    }

    public void setNucleo(String nucleo) {
        this.nucleo = nucleo;
    }

    public String getNivel_educativo() {
        return nivel_educativo;
    }

    public void setNivel_educativo(String nivel_educativo) {
        this.nivel_educativo = nivel_educativo;
    }

    public String getCapacitacion() {
        return capacitacion;
    }

    public void setCapacitacion(String capacitacion) {
        this.capacitacion = capacitacion;
    }

    public int getOcupacionId() {
        return ocupacionId;
    }

    public void setOcupacionId(int ocupacionId) {
        this.ocupacionId = ocupacionId;
    }

    public int getIngresoId() {
        return ingresoId;
    }

    public void setIngresoId(int ingresoId) {
        this.ingresoId = ingresoId;
    }

    public int getSaludId() {
        return saludId;
    }

    public void setSaludId(int saludId) {
        this.saludId = saludId;
    }
}