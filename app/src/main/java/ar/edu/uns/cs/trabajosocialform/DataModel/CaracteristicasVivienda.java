package ar.edu.uns.cs.trabajosocialform.DataModel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * This Class represents the "Caracteristicas de la vivienda" section of the form
 */

@Entity(tableName = "caracteristicas_vivienda")
public class CaracteristicasVivienda implements Serializable{

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "material_techo")
    private String material_techo;
    @ColumnInfo(name = "revestimiento_techo")
    private boolean revestimiento_techo;
    @ColumnInfo(name = "material_pared")
    private String material_paredes;
    @ColumnInfo(name = "revestimiento_pared")
    private boolean revestimiento_paredes;
    @ColumnInfo(name = "material_pisos")
    private String material_pisos;
    @ColumnInfo(name = "agua")
    private String agua;
    @ColumnInfo(name = "fuente_agua")
    private String fuente_agua;
    @ColumnInfo(name = "banio")
    private String banio;
    @ColumnInfo(name = "banio_tiene")
    private String banio_tiene;
    @ColumnInfo(name = "desague")
    private String desague;
    @ColumnInfo(name = "cocina")
    private boolean cocina;
    @ColumnInfo(name = "electricidad")
    private String electricidad;
    @ColumnInfo(name = "combustible_cocina")
    private String combustible_cocina;

    @Ignore
    public CaracteristicasVivienda() {
    }

    public CaracteristicasVivienda(String material_techo, boolean revestimiento_techo, String material_paredes, boolean revestimiento_paredes, String material_pisos, String agua, String fuente_agua, String banio, String banio_tiene, String desague, boolean cocina, String electricidad, String combustible_cocina) {
        this.material_techo = material_techo;
        this.revestimiento_techo = revestimiento_techo;
        this.material_paredes = material_paredes;
        this.revestimiento_paredes = revestimiento_paredes;
        this.material_pisos = material_pisos;
        this.agua = agua;
        this.fuente_agua = fuente_agua;
        this.banio = banio;
        this.banio_tiene = banio_tiene;
        this.desague = desague;
        this.cocina = cocina;
        this.electricidad = electricidad;
        this.combustible_cocina = combustible_cocina;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaterial_techo() {
        return material_techo;
    }

    public void setMaterial_techo(String material_techo) {
        this.material_techo = material_techo;
    }

    public boolean isRevestimiento_techo() {
        return revestimiento_techo;
    }

    public void setRevestimiento_techo(boolean revestimiento_techo) {
        this.revestimiento_techo = revestimiento_techo;
    }

    public String getMaterial_paredes() {
        return material_paredes;
    }

    public void setMaterial_paredes(String material_pareded) {
        this.material_paredes = material_pareded;
    }

    public boolean isRevestimiento_paredes() {
        return revestimiento_paredes;
    }

    public void setRevestimiento_paredes(boolean revestimiento_paredes) {
        this.revestimiento_paredes = revestimiento_paredes;
    }

    public String getMaterial_pisos() {
        return material_pisos;
    }

    public void setMaterial_pisos(String material_pisos) {
        this.material_pisos = material_pisos;
    }

    public String getAgua() {
        return agua;
    }

    public void setAgua(String agua) {
        this.agua = agua;
    }

    public String getFuente_agua() {
        return fuente_agua;
    }

    public void setFuente_agua(String fuente_agua) {
        this.fuente_agua = fuente_agua;
    }

    public String getBanio() {
        return banio;
    }

    public void setBanio(String banio) {
        this.banio = banio;
    }

    public String getBanio_tiene() {
        return banio_tiene;
    }

    public void setBanio_tiene(String banio_tiene) {
        this.banio_tiene = banio_tiene;
    }

    public String getDesague() {
        return desague;
    }

    public void setDesague(String desague) {
        this.desague = desague;
    }

    public boolean isCocina() {
        return cocina;
    }

    public void setCocina(boolean cocina) {
        this.cocina = cocina;
    }

    public String getElectricidad() {
        return electricidad;
    }

    public void setElectricidad(String electricidad) {
        this.electricidad = electricidad;
    }

    public String getCombustible_cocina() {
        return combustible_cocina;
    }

    public void setCombustible_cocina(String combustible_cocina) {
        this.combustible_cocina = combustible_cocina;
    }


}
