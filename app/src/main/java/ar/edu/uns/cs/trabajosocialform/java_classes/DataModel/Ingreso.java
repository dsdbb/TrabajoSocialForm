package ar.edu.uns.cs.trabajosocialform.java_classes.DataModel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Kevin (User) on 17/4/2018.
 */

@Entity(tableName = "ingreso",foreignKeys = @ForeignKey(entity=IngresoNoLaboral.class,
                                                        parentColumns = "id",
                                                        childColumns = "ingresosNoLaboralesId",
                                                        onDelete = CASCADE))
public class Ingreso {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "ingresos_laborales")
    private int ingresos_laborales;
    // @ColumnInfo(name = "programas_sociales")
    // private List<String> programas_sociales_sti;

    @ColumnInfo(name = "ingresos_no_laborales_id")
    private int ingresosNoLaboralesId;


    @Ignore
    public Ingreso() {
    }


    public Ingreso(int id, int ingresos_laborales, int ingresosNoLaboralesId) {
        this.id = id;
        this.ingresos_laborales = ingresos_laborales;
        this.ingresosNoLaboralesId = ingresosNoLaboralesId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIngresos_laborales() {
        return ingresos_laborales;
    }

    public void setIngresos_laborales(int ingresos_laborales) {
        this.ingresos_laborales = ingresos_laborales;
    }

    public int getIngresosNoLaboralesId() {
        return ingresosNoLaboralesId;
    }

    public void setIngresosNoLaboralesId(int ingresosNoLaboralesId) {
        this.ingresosNoLaboralesId = ingresosNoLaboralesId;
    }
}