package ar.edu.uns.cs.trabajosocialform.Converters;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.edu.uns.cs.trabajosocialform.DataModel.IngresoNoLaboral;

public class Converters{

    @TypeConverter
    public static Date toDate(String fecha){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            if(!fecha.equals(""))
                return formato.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String toString(Date fecha){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        if(fecha!=null)
            return formato.format(fecha);

        return null;
    }

    @TypeConverter
    public static String toString(List<String> lista){
        return new Gson().toJson(lista);
    }

    @TypeConverter
    public static List<String> toList(String json){
       return (new Gson()).fromJson(json, List.class);
    }

    @TypeConverter
    public static List<IngresoNoLaboral> stringToSomeObjectList(String data) {
        if (data == null) {
            return new ArrayList<IngresoNoLaboral>();
        }

        Type listType = new TypeToken<List<IngresoNoLaboral>>() {}.getType();

        return (new Gson()).fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<IngresoNoLaboral> ingresosNoLaborales) {
        return (new Gson()).toJson(ingresosNoLaborales);
    }

}
