package ar.edu.uns.cs.trabajosocialform.DataModel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * This Class represents the "DomiciliO" section of the form
 */
@Entity(tableName = "domicilio")
public class Domicilio implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "calle")
    private String calle;
    @ColumnInfo(name = "numero")
    private Integer numero;
    @ColumnInfo(name = "manzana")
    private Integer manzana;
    @ColumnInfo(name = "monoblock/torre")
    private Integer monoblock_torre;
    @ColumnInfo(name = "piso")
    private Integer piso;
    @ColumnInfo(name = "acc/int")
    private Integer acc_int;
    @ColumnInfo(name = "casa/depto")
    private Integer casa_dpto;
    @ColumnInfo(name = "entre_calle")
    private String entre_calle1;
    @ColumnInfo(name = "y_calle")
    private String entre_calle2;
    @ColumnInfo(name = "barrio")
    private String barrio;
    @ColumnInfo(name = "delegacion")
    private String delegacion;
    @ColumnInfo(name = "localidad")
    private String localidad;

    @Ignore
    public Domicilio() {
    }


    public Domicilio(String calle, Integer numero, Integer manzana, Integer monoblock_torre, Integer piso, Integer acc_int, Integer casa_dpto, String entre_calle1, String entre_calle2, String barrio, String delegacion, String localidad) {
        this.calle = calle;
        this.numero = numero;
        this.manzana = manzana;
        this.monoblock_torre = monoblock_torre;
        this.piso = piso;
        this.acc_int = acc_int;
        this.casa_dpto = casa_dpto;
        this.entre_calle1 = entre_calle1;
        this.entre_calle2 = entre_calle2;
        this.barrio = barrio;
        this.delegacion = delegacion;
        this.localidad = localidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getManzana() {
        return manzana;
    }

    public void setManzana(Integer manzana) {
        this.manzana = manzana;
    }

    public Integer getMonoblock_torre() {
        return monoblock_torre;
    }

    public void setMonoblock_torre(Integer monoblock_torre) {
        this.monoblock_torre = monoblock_torre;
    }

    public Integer getPiso() {
        return piso;
    }

    public void setPiso(Integer piso) {
        this.piso = piso;
    }

    public Integer getAcc_int() {
        return acc_int;
    }

    public void setAcc_int(Integer acc_int) {
        this.acc_int = acc_int;
    }

    public Integer getCasa_dpto() {
        return casa_dpto;
    }

    public void setCasa_dpto(Integer casa_dpto) {
        this.casa_dpto = casa_dpto;
    }

    public String getEntre_calle1() {
        return entre_calle1;
    }

    public void setEntre_calle1(String entre_calle1) {
        this.entre_calle1 = entre_calle1;
    }

    public String getEntre_calle2() {
        return entre_calle2;
    }

    public void setEntre_calle2(String entre_calle2) {
        this.entre_calle2 = entre_calle2;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getDelegacion() {
        return delegacion;
    }

    public void setDelegacion(String delegacion) {
        this.delegacion = delegacion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
}