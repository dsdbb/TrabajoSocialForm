package ar.edu.uns.cs.trabajosocialform.DataModel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by Kevin (User) on 17/4/2018.
 */

@Entity(tableName = "solicitante")
public class Solicitante implements Serializable {

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
    @ColumnInfo(name = "otro_telefono")
    private String otro_telefono;

    @Ignore
    public Solicitante(){}

    public Solicitante(String nombres, String apellidos, Integer cuil, String telefono, String otro_telefono) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cuil = cuil;
        this.telefono = telefono;
        this.otro_telefono = otro_telefono;
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

    public String getOtro_telefono() {
        return otro_telefono;
    }

    public void setOtro_telefono(String otro_telefono) {
        this.otro_telefono = otro_telefono;
    }
}

