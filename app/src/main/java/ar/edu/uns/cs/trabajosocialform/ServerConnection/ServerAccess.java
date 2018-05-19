package ar.edu.uns.cs.trabajosocialform.ServerConnection;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import ar.edu.uns.cs.trabajosocialform.DataModel.Apoderado;
import ar.edu.uns.cs.trabajosocialform.DataModel.CaracteristicasVivienda;
import ar.edu.uns.cs.trabajosocialform.DataModel.Domicilio;
import ar.edu.uns.cs.trabajosocialform.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.DataModel.InfraestructuraBarrial;
import ar.edu.uns.cs.trabajosocialform.DataModel.SituacionHabitacional;
import ar.edu.uns.cs.trabajosocialform.DataModel.Solicitante;
import ar.edu.uns.cs.trabajosocialform.MainActivity;
import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;
import ar.edu.uns.cs.trabajosocialform.configuracion.Configuracion;

import static junit.framework.Assert.assertEquals;

public class ServerAccess {

    private final Activity act;

    public ServerAccess(Activity act){
        this.act = act;
    }

    public void getConfigurationFileFromServer(){
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

                            if(confActual==null || !o1.equals(o2)){
                                /*Si son distintos o no existe ningun archivo local aun,
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

    public void uploadForm(final Formulario form){
        String server_url = "http://192.168.43.45:80/example/uploadform.php";


        final AlertDialog.Builder builder =  new AlertDialog.Builder(act);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        builder.setTitle("SERVER RESPONSE");
                        builder.setMessage("Response: "+response);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(act,"ERROR",Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return getParamsMap(form);
            }
        };

        ServerSingleton.getInstance(act).addToRequestQueue(stringRequest);
    }

    private Map<String,String> getParamsMap(Formulario form){
        Utils utils = new Utils(act);
        Map<String,String> params = new HashMap<String, String>();

        /*Datos del solitante*/
        Solicitante solicitante = form.getSolicitante();
        params.put("id_solicitante",solicitante.getId()+"");
        params.put("nombres_solicitante",solicitante.getNombres());
        params.put("apellidos_solicitante",solicitante.getApellidos());
        params.put("cuil_solicitante",solicitante.getCuil()+"");
        params.put("telefono_principal_solicitante",solicitante.getTelefono());
        params.put("otro_telefono",solicitante.getOtro_telefono());

        /*Datos del apoderado*/
        Apoderado apoderado = form.getApoderado();
        params.put("id_apoderado",apoderado.getId()+"");
        params.put("nombres_apoderado",apoderado.getNombres());
        params.put("apellidos_apoderado",apoderado.getApellidos());
        params.put("cuil_apoderado",apoderado.getCuil()+"");
        params.put("telefono_apoderado",apoderado.getTelefono());
        String fecha_nac = utils.getStringFromDate(apoderado.getFecha_nacimiento());
        String fecha = "12/05/1955";
        params.put("fecha_nacimiento_apoderado",fecha);
        params.put("motivos_poder",apoderado.getMotivos_poder());

        /*Datos del domicilio*/
        Domicilio domicilio = form.getDomicilio();
        params.put("id_domicilio",domicilio.getId()+"");
        params.put("calle",domicilio.getCalle());
        params.put("numero",domicilio.getNumero()+"");
        params.put("manzana",domicilio.getManzana()+"");
        params.put("monoblock_torre",domicilio.getMonoblock_torre()+"");
        params.put("piso",domicilio.getPiso()+"");
        params.put("acc_int",domicilio.getAcc_int()+"");
        params.put("casa_dpto",domicilio.getCasa_dpto()+"");
        params.put("entre_calle1",domicilio.getEntre_calle1());
        params.put("entre_calle2",domicilio.getEntre_calle2());
        params.put("barrio",domicilio.getBarrio());
        params.put("localidad",domicilio.getLocalidad());
        params.put("delegacion",domicilio.getDelegacion());

        /*Familiares*/
        /*Convierto la lista de familiares a Json y la envio para luego deserializarla en el servicio web*/
        Gson gson = new GsonBuilder().serializeNulls().create();
        String familiaresJson = gson.toJson(form.getFamiliares());
        params.put("familiares",familiaresJson);
        Log.i("FAMILIARES",familiaresJson);

        /*Datos de la situacion habitacional*/
        SituacionHabitacional situacionHabitacional = form.getSituacionHabitacional();
        params.put("id_situacion_habitacional",situacionHabitacional.getId()+"");
        params.put("tipo_vivienda",situacionHabitacional.getTipo_vivienda());
        params.put("tenencia_vivienda_terreno",situacionHabitacional.getTenencia_vivienda_terreno());
        params.put("tiempo_ocupacion",situacionHabitacional.getTiempo_ocupacion()+"");
        params.put("cantidad_hogares_vivienda",situacionHabitacional.getCantidad_hogares_vivienda()+"");
        params.put("cantidad_cuartos_ue",situacionHabitacional.getCantidad_cuartos_ue()+"");

        /*Caracteristicas de la vivienda*/
        CaracteristicasVivienda caracteristicasVivienda = form.getCaracteristicasVivienda();
        params.put("id_caracteristicas_vivienda",caracteristicasVivienda.getId()+"");
        params.put("techo",caracteristicasVivienda.getMaterial_techo());
        String revestimientoTecho = utils.getStringFromBoolean(caracteristicasVivienda.isRevestimiento_techo());
        params.put("revestimiento_techo",revestimientoTecho);
        params.put("paredes",caracteristicasVivienda.getMaterial_paredes());
        String revestimientoParedes = utils.getStringFromBoolean(caracteristicasVivienda.isRevestimiento_paredes());
        params.put("revestimiento_paredes",revestimientoParedes);
        params.put("pisos",caracteristicasVivienda.getMaterial_pisos());
        params.put("agua",caracteristicasVivienda.getAgua());
        params.put("fuente_agua",caracteristicasVivienda.getFuente_agua());
        params.put("banio",caracteristicasVivienda.getBanio());
        params.put("banio_tiene",caracteristicasVivienda.getBanio_tiene());
        params.put("desague",caracteristicasVivienda.getDesague());
        String cocina = utils.getStringFromBoolean(caracteristicasVivienda.isCocina());
        params.put("cocina",cocina);
        params.put("combustible_cocina",caracteristicasVivienda.getCombustible_cocina());

        /*Datos de Infraestructura Barrial*/
        InfraestructuraBarrial infraestructuraBarrial = form.getInfraestructuraBarrial();
        params.put("id_infraestructura_barrial",infraestructuraBarrial.getId()+"");
        params.put("calles",infraestructuraBarrial.getInfraestructura_calles());
        params.put("iluminacion",infraestructuraBarrial.getIluminacion());
        String inundacion = utils.getStringFromBoolean(infraestructuraBarrial.isInundacion());
        params.put("inundacion",inundacion);
        String recoleccion = utils.getStringFromBoolean(infraestructuraBarrial.isRecoleccion_residuos());
        params.put("recoleccion_residuos",recoleccion);
        params.put("distancia_educacion",infraestructuraBarrial.getDistancia_educacion());
        params.put("distancia_salud",infraestructuraBarrial.getDistancia_salud());
        params.put("distancia_transporte",infraestructuraBarrial.getDistancia_transporte());

        /*id del formulario*/
        params.put("id_formulario",form.getId()+"");

        return params;
    }
}
