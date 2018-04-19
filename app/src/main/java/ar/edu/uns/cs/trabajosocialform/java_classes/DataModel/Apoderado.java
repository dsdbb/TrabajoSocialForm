package ar.edu.uns.cs.trabajosocialform.java_classes.DataModel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by Kevin (User) on 17/4/2018.
 */

@Entity(tableName = "apoderado")
public class Apoderado {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "nombres")
    private String nombres;
    @ColumnInfo(name = "apellidos")
    private String apellidos;
    @ColumnInfo(name = "cuil")
    private Integer cuil;
    @ColumnInfo(name = "telefono")
    private String telefono;
    @ColumnInfo(name = "fecha_nacimiento")
    private Date fecha_nacimiento;
    @ColumnInfo(name = "motivos_poder")
    private String motivos_poder;

    @Ignore
    public Apoderado() {
    }

    public Apoderado(String nombres, String apellidos, Integer cuil, String telefono, Date fecha_nacimiento, String motivos_poder) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cuil = cuil;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
        this.motivos_poder = motivos_poder;
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

    public Integer getCuil() {
        return cuil;
    }

    public void setCuil(Integer cuil) {
        this.cuil = cuil;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getMotivos_poder() {
        return motivos_poder;
    }

    public void setMotivos_poder(String motivos_poder) {
        this.motivos_poder = motivos_poder;
    }
}

