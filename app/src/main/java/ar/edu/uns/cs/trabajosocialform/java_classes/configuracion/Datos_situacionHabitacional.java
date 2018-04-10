package ar.edu.uns.cs.trabajosocialform.java_classes.configuracion;

import android.app.Activity;

/**
 * Created by Kevin (User) on 5/4/2018.
 */

public class Datos_situacionHabitacional {

    private boolean tipo_de_vivienda;
    private boolean tenencia_vivienda_terreno;
    private boolean tiempo_de_ocupacion;
    private boolean cantidad_de_hogares_en_vivienda;
    /*cantidad de cuartos de uso exclusivo*/
    private boolean cantidad_de_cuartos_UE;

    public Datos_situacionHabitacional() {
    }

    public boolean isTipo_de_vivienda() {
        return tipo_de_vivienda;
    }

    public void setTipo_de_vivienda(boolean tipo_de_vivienda) {
        this.tipo_de_vivienda = tipo_de_vivienda;
    }

    public boolean isTenencia_vivienda_terreno() {
        return tenencia_vivienda_terreno;
    }

    public void setTenencia_vivienda_terreno(boolean tenencia_vivienda_terreno) {
        this.tenencia_vivienda_terreno = tenencia_vivienda_terreno;
    }

    public boolean isTiempo_de_ocupacion() {
        return tiempo_de_ocupacion;
    }

    public void setTiempo_de_ocupacion(boolean tiempo_de_ocupacion) {
        this.tiempo_de_ocupacion = tiempo_de_ocupacion;
    }

    public boolean isCantidad_de_hogares_en_vivienda() {
        return cantidad_de_hogares_en_vivienda;
    }

    public void setCantidad_de_hogares_en_vivienda(boolean cantidad_de_hogares_en_vivienda) {
        this.cantidad_de_hogares_en_vivienda = cantidad_de_hogares_en_vivienda;
    }

    public boolean isCantidad_de_cuartos_UE() {
        return cantidad_de_cuartos_UE;
    }

    public void setCantidad_de_cuartos_UE(boolean cantidad_de_cuartos_UE) {
        this.cantidad_de_cuartos_UE = cantidad_de_cuartos_UE;
    }
}
