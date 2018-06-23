package ar.edu.uns.cs.trabajosocialform.Database;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import ar.edu.uns.cs.trabajosocialform.configuracion.Configuracion;

/**
 * Access the local storage to get files
 */
public class StorageAccess {

    /**
     * Access the local storage to find the Configuration file and transform it in a Json String
     * @param act The activity who needs the Configuration file
     * @return String representing the configuration with Json format
     */
    public String getConfigurationJson(Activity act){
        try
        {
            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    act.openFileInput("config.txt")));

            /*Read the json string from file*/
            String json = fin.readLine();

            return json;
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
            ex.printStackTrace();
        }
        return null;
    }

    public Configuracion getConfigurationFile(Activity act){
        try
        {
            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    act.openFileInput("config.txt")));

            /*Leo el String json del archivo de configuracion y hago la deserializacion en la clase configuracion con la
             librería GSON*/
            String json = fin.readLine();
            if(json !=null){
                Configuracion config = (new Gson()).fromJson(json, Configuracion.class);
                return config;
            }
            else{
                return null;
            }
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
            ex.printStackTrace();
        }
        return null;
    }

    public boolean existsConfigurationFile(Activity act){
        try
        {
            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    act.openFileInput("config.txt")));
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

}
