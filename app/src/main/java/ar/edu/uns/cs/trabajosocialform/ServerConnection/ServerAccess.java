package ar.edu.uns.cs.trabajosocialform.ServerConnection;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

import ar.edu.uns.cs.trabajosocialform.MainActivity;
import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;
import ar.edu.uns.cs.trabajosocialform.configuracion.Configuracion;

import static junit.framework.Assert.assertEquals;

public class ServerAccess {

    public void getConfigurationFromServer(final Activity act){
        String url = "http://192.168.43.45:80/example/getconfig.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Response: ",response);

                        if(response.equals("false")){
                            Toast.makeText(act,act.getResources().getString(R.string.error_fichero), Toast.LENGTH_SHORT).show();
                        }
                        else{
                            /*Compruebo si el json recuperado del servidor es el mismo que el que ya tengo*/
                            Utils utils = new Utils(act);
                            Configuracion confActual = utils.getConfigurationFile();
                            String jsonActual = (new Gson()).toJson(confActual);

                            Configuracion configServer = (new Gson()).fromJson(response,Configuracion.class);
                            String jsonServer = (new Gson()).toJson(configServer);

                            JsonParser parser = new JsonParser();
                            JsonElement o1 = parser.parse(jsonActual);
                            JsonElement o2 = parser.parse(jsonServer);
                            Log.i("JsonActual",jsonActual);
                            Log.i("jsonServer",jsonServer);
                            Log.i("Son iguales?",o1.equals(o2)+"");


                            if(confActual==null || !o1.equals(o2)){
                                /*Si no son iguales o no existe ningun archivo local aun
                                guardo el string recuperado en el archivo local*/
                                Log.i("Encontre: ", "un nuevo archivo o no habia ninguno");

                                String filename = "config.txt";
                                File file = new File(act.getFilesDir(), filename);
                                FileOutputStream outputStream;

                                try {
                                    outputStream = act.openFileOutput(filename, act.MODE_PRIVATE);
                                    outputStream.write(response.getBytes());
                                    outputStream.close();
                                    Toast.makeText(act,act.getResources().getString(R.string.nueva_configuracion), Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(act,act.getResources().getString(R.string.error_servidor), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }){
    };
        ServerSingleton.getInstance(act).addToRequestQueue(stringRequest);

    }
}
