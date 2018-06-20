package ar.edu.uns.cs.trabajosocialform.Database;

import android.app.Activity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import ar.edu.uns.cs.trabajosocialform.FormularioActivity;
import ar.edu.uns.cs.trabajosocialform.MainActivity;

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
}
