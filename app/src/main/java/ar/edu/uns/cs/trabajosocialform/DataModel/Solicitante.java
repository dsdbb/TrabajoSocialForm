package ar.edu.uns.cs.trabajosocialform.DataModel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;

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
    private Long cuil;
    @ColumnInfo(name = "telefono")
    private String telefono;
    @ColumnInfo(name = "otro_telefono")
    private String otro_telefono;
    @ColumnInfo(name = "foto")
    private String foto;

    @Ignore
    public Solicitante(){}

    public Solicitante(String nombres, String apellidos, Long cuil, String telefono, String otro_telefono, String foto) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cuil = cuil;
        this.telefono = telefono;
        this.otro_telefono = otro_telefono;
        this.foto = foto;
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

    public Long getCuil() {
        return cuil;
    }

    public void setCuil(Long cuil) {
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}

