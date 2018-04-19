package ar.edu.uns.cs.trabajosocialform.java_classes.DataModel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Kevin (User) on 17/4/2018.
 */

@Entity(tableName = "domicilio")
public class Domicilio {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "calle")
    private String calle;
    @ColumnInfo(name = "numero")
    private int numero;
    @ColumnInfo(name = "manzana")
    private int manzana;
    @ColumnInfo(name = "monoblock/torre")
    private int monoblock_torre;
    @ColumnInfo(name = "piso")
    private int piso;
    @ColumnInfo(name = "acc/int")
    private int acc_int;
    @ColumnInfo(name = "entre_calle")
    private String entre_calle1;
    @ColumnInfo(name = "y_calle")
    private String entre_calle2;
    @ColumnInfo(name = "barrio")
    private String barrio;
    @ColumnInfo(name = "localidad")
    private String localidad;

    @Ignore
    public Domicilio() {
    }

    public Domicilio(String calle, int numero, int manzana, int monoblock_torre, int piso, int acc_int, String entre_calle1, String entre_calle2, String barrio, String localidad) {
        this.calle = calle;
        this.numero = numero;
        this.manzana = manzana;
        this.monoblock_torre = monoblock_torre;
        this.piso = piso;
        this.acc_int = acc_int;
        this.entre_calle1 = entre_calle1;
        this.entre_calle2 = entre_calle2;
        this.barrio = barrio;
        localidad = localidad;
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getManzana() {
        return manzana;
    }

    public void setManzana(int manzana) {
        this.manzana = manzana;
    }

    public int getMonoblock_torre() {
        return monoblock_torre;
    }

    public void setMonoblock_torre(int monoblock_torre) {
        this.monoblock_torre = monoblock_torre;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getAcc_int() {
        return acc_int;
    }

    public void setAcc_int(int acc_int) {
        this.acc_int = acc_int;
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

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        localidad = localidad;
    }
}

