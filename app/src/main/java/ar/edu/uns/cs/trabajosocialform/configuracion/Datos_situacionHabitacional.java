package ar.edu.uns.cs.trabajosocialform.configuracion;

import java.io.Serializable;

/**
 * Created by Kevin (User) on 5/4/2018.
 */

public class Datos_situacionHabitacional implements Serializable {

    private boolean tipo_vivienda;
    private boolean tenencia_vivienda_terreno;
    private boolean tiempo_ocupacion;
    private boolean cantidad_hogares_vivienda;
    /*cantidad de cuartos de uso exclusivo*/
    private boolean cantidad_cuartos_ue;

    public Datos_situacionHabitacional() {
    }

    public boolean isTipo_vivienda() {
        return tipo_vivienda;
    }

    public void setTipo_vivienda(boolean tipo_vivienda) {
        this.tipo_vivienda = tipo_vivienda;
    }

    public boolean isTenencia_vivienda_terreno() {
        return tenencia_vivienda_terreno;
    }

    public void setTenencia_vivienda_terreno(boolean tenencia_vivienda_terreno) {
        this.tenencia_vivienda_terreno = tenencia_vivienda_terreno;
    }

    public boolean isTiempo_ocupacion() {
        return tiempo_ocupacion;
    }

    public void setTiempo_ocupacion(boolean tiempo_ocupacion) {
        this.tiempo_ocupacion = tiempo_ocupacion;
    }

    public boolean isCantidad_hogares_vivienda() {
        return cantidad_hogares_vivienda;
    }

    public void setCantidad_hogares_vivienda(boolean cantidad_hogares_vivienda) {
        this.cantidad_hogares_vivienda = cantidad_hogares_vivienda;
    }

    public boolean isCantidad_cuartos_ue() {
        return cantidad_cuartos_ue;
    }

    public void setCantidad_cuartos_ue(boolean cantidad_cuartos_ue) {
        this.cantidad_cuartos_ue = cantidad_cuartos_ue;
    }
}
