package ar.edu.uns.cs.trabajosocialform.Data.DataModel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * This Class represents the "Situacion Habitacional" section of the form
 */

@Entity(tableName = "situacion_habitacional")
public class  SituacionHabitacional implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "tipo_vivienda")
    private String tipo_vivienda;
    @ColumnInfo(name = "tenencia_vivienda_terreno")
    private String tenencia_vivienda_terreno;
    @ColumnInfo(name = "tiempo_ocupacion")
    private Integer tiempo_ocupacion;
    @ColumnInfo(name = "cantidad_hogares_en_vivienda")
    private Integer cantidad_hogares_vivienda;
    @ColumnInfo(name = "cantidad_cuartos_uso_exclusivo")
    private Integer cantidad_cuartos_ue;

    @Ignore
    public SituacionHabitacional() {
    }

    public SituacionHabitacional(String tipo_vivienda, String tenencia_vivienda_terreno, Integer tiempo_ocupacion, Integer cantidad_hogares_vivienda, Integer cantidad_cuartos_ue) {
        this.tipo_vivienda = tipo_vivienda;
        this.tenencia_vivienda_terreno = tenencia_vivienda_terreno;
        this.tiempo_ocupacion = tiempo_ocupacion;
        this.cantidad_hogares_vivienda = cantidad_hogares_vivienda;
        this.cantidad_cuartos_ue = cantidad_cuartos_ue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo_vivienda() {
        return tipo_vivienda;
    }

    public void setTipo_vivienda(String tipo_vivienda) {
        this.tipo_vivienda = tipo_vivienda;
    }

    public String getTenencia_vivienda_terreno() {
        return tenencia_vivienda_terreno;
    }

    public void setTenencia_vivienda_terreno(String tenencia_vivienda_terreno) {
        this.tenencia_vivienda_terreno = tenencia_vivienda_terreno;
    }

    public Integer getTiempo_ocupacion() {
        return tiempo_ocupacion;
    }

    public void setTiempo_ocupacion(Integer tiempo_ocupacion) {
        this.tiempo_ocupacion = tiempo_ocupacion;
    }

    public Integer getCantidad_hogares_vivienda() {
        return cantidad_hogares_vivienda;
    }

    public void setCantidad_hogares_vivienda(Integer cantidad_hogares_vivienda) {
        this.cantidad_hogares_vivienda = cantidad_hogares_vivienda;
    }

    public Integer getCantidad_cuartos_ue() {
        return cantidad_cuartos_ue;
    }

    public void setCantidad_cuartos_ue(Integer cantidad_cuartos_ue) {
        this.cantidad_cuartos_ue = cantidad_cuartos_ue;
    }
}
