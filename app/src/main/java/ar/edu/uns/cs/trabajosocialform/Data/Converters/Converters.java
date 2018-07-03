package ar.edu.uns.cs.trabajosocialform.Data.Converters;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.IngresoNoLaboral;

/**
 * This Class contains converters methods necessary for Room implementation because Room cannot save
 * Lists or Dates, so they must be converted from and to String when saved and retrieved to and from database
 */
public class Converters{

    /**
     * Convert a String into a Java Date if the String follows the format "dd/MM/yyyy"
     * @param fecha  the String date
     * @return a Date object
     */
    @TypeConverter
    public static Date toDate(String fecha){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        try {
            if(fecha != null)
                return formato.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Convert a Java Date into a String with the format dd/MM/yyyy
     * @param fecha the Date object to be converted to String
     * @return the String date
     */
    @TypeConverter
    public static String toString(Date fecha){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        if(fecha!=null)
            return formato.format(fecha);

        return null;
    }

    /**
     * Converts a List<String> into a String with JSON format
     * @param lista the java List to convert
     * @return the String object with json format of the List
     */
    @TypeConverter
    public static String toString(List<String> lista){
        return new Gson().toJson(lista);
    }

    /**
     * Converts a String in json format into a java List<String>
     * @param json the String with json format to be converted
     * @return  a List<Strng>
     */
    @TypeConverter
    public static List<String> toList(String json){
       return (new Gson()).fromJson(json, new TypeToken<List<String>>(){}.getType());
    }

    /**
     * Converts a String in json format to a java List<IngresoNoLaboral>
     * @param data String with json format
     * @return a List<IngresoNoLaboral>
     */
    @TypeConverter
    public static List<IngresoNoLaboral> stringToSomeObjectList(String data) {
        if (data == null) {
            return new ArrayList<IngresoNoLaboral>();
        }

        Type listType = new TypeToken<List<IngresoNoLaboral>>() {}.getType();

        return (new Gson()).fromJson(data, listType);
    }

    /**
     * Converts a java List<IngresoNoLaboral> into a String with json format
     * @param ingresosNoLaborales the list to be converted
     * @return String object in json format of the List<IngresoNoLaboral>
     */
    @TypeConverter
    public static String someObjectListToString(List<IngresoNoLaboral> ingresosNoLaborales) {
        return (new Gson()).toJson(ingresosNoLaborales);
    }



}
