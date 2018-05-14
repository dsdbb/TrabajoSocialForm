package ar.edu.uns.cs.trabajosocialform.DataModel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.edu.uns.cs.trabajosocialform.Converters.Converters;

/**
 * Created by Kevin (User) on 17/4/2018.
 */

@Entity(tableName = "salud")
public class Salud implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="cobertura")
    private String cobertura;
    @ColumnInfo(name="embarazo")
    private boolean embarazo;
    @ColumnInfo(name="fecha_estimada_embarazo")
    private Date fecha_estimada_embarazo;
    @ColumnInfo(name="discapacidades")
    @TypeConverters({Converters.class})
    private List<String> discapacidades;
    @ColumnInfo(name="enfermedades_cronicas")
    @TypeConverters({Converters.class})
    private List<String> enfermedadesCronicas;
    @ColumnInfo(name="independencia_funcional")
    private String independencia_funcional;


    @Ignore
    public Salud() {
        discapacidades = new ArrayList<String>();
        enfermedadesCronicas = new ArrayList<String>();
    }


    public Salud(String cobertura, boolean embarazo, Date fecha_estimada_embarazo, List<String> discapacidades, List<String> enfermedadesCronicas, String independencia_funcional) {
        this.cobertura = cobertura;
        this.embarazo = embarazo;
        this.fecha_estimada_embarazo = fecha_estimada_embarazo;
        this.discapacidades = new ArrayList<String>();
        this.enfermedadesCronicas = new ArrayList<String>();
        this.independencia_funcional = independencia_funcional;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCobertura() {
        return cobertura;
    }

    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }

    public boolean isEmbarazo() {
        return embarazo;
    }

    public void setEmbarazo(boolean embarazo) {
        this.embarazo = embarazo;
    }

    public Date getFecha_estimada_embarazo() {
        return fecha_estimada_embarazo;
    }

    public void setFecha_estimada_embarazo(Date fecha_estimada_embarazo) {
        this.fecha_estimada_embarazo = fecha_estimada_embarazo;
    }

    @TypeConverters({Converters.class})
    public List<String> getDiscapacidades() {
        return discapacidades;
    }

    @TypeConverters({Converters.class})
    public void setDiscapacidades(List<String> discapacidades) {
        this.discapacidades = discapacidades;
    }

    @TypeConverters({Converters.class})
    public List<String> getEnfermedadesCronicas() {
        return enfermedadesCronicas;
    }

    @TypeConverters({Converters.class})
    public void setEnfermedadesCronicas(List<String> enfermedadesCronicas) {
        this.enfermedadesCronicas = enfermedadesCronicas;
    }

    public String getIndependencia_funcional() {
        return independencia_funcional;
    }

    public void setIndependencia_funcional(String independencia_funcional) {
        this.independencia_funcional = independencia_funcional;
    }
}
