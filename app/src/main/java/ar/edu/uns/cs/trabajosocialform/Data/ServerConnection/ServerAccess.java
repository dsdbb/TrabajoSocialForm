package ar.edu.uns.cs.trabajosocialform.Data.ServerConnection;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Apoderado;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Solicitante;
import ar.edu.uns.cs.trabajosocialform.Data.Database.DatabaseAcces;
import ar.edu.uns.cs.trabajosocialform.Data.Database.StorageAccess;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.CaracteristicasVivienda;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Domicilio;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.InfraestructuraBarrial;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.SituacionHabitacional;
import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.Data.Transactions.Transaction;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;
import io.reactivex.Observer;

import static junit.framework.Assert.assertEquals;

/**
 * Class that provides methods to comunicate with web service
 */
public class ServerAccess {

    private final Activity act;
    private final String ip = "192.168.43.45:80";
    private final String folder = "trabajo-social";

    public ServerAccess(Activity act){
        this.act = act;
    }

    /**
     * Gets the configuration file from the server and update it if there is no Configuration file or
     * it is out of date
     */
    public void getConfigurationFileFromServer(final Observer<Boolean> observer){
        String url = "http://"+ip+"/"+folder+"/getconfig.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equals("false")){
                            observer.onNext(false);
                        }
                        else{
                            /*Checks if the json retrieved from server is the one I already have*/
                            StorageAccess sa = new StorageAccess();
                            String jsonActual = sa.getConfigurationJson(act);

                            if(jsonActual==null){
                                jsonActual="{}";
                            }
                            /*Convert to get the same json format*/
                            Configuracion configServer = (new Gson()).fromJson(response,Configuracion.class);
                            String jsonServer = (new Gson()).toJson(configServer);

                            JsonParser parser = new JsonParser();
                            JsonElement o1 = parser.parse(jsonActual);
                            JsonElement o2 = parser.parse(jsonServer);

                            if(TextUtils.isEmpty(jsonActual)|| !o1.equals(o2)){
                                /*If the configuration json are different or there is no local file I save it*/
                                String filename = "config.txt";
                                File file = new File(act.getFilesDir(), filename);
                                FileOutputStream outputStream;

                                try {
                                    outputStream = act.openFileOutput(filename, act.MODE_PRIVATE);
                                    outputStream.write(response.getBytes());
                                    outputStream.close();
                                    observer.onNext(true);
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
                        observer.onError(error);
                        error.printStackTrace();
                    }
                }){
    };
        ServerSingleton.getInstance(act).addToRequestQueue(stringRequest);

    }

    /**
     * Using a web service upload a form to an external database
     * @param form Formulario wanted to upload
     * @param transaction Transaction associated to the upload (to be deleted if upload is successful)
     */
    public void uploadForm(final Formulario form, final Transaction transaction, final Observer<Boolean> observer){
        String server_url = "http://"+ip+"/"+folder+"/uploadform.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        /*If the query is successful delete the transaction from the pending transactions*/
                        if(response.equals("true")){
                            DatabaseAcces db = new DatabaseAcces();
                            db.deleteTransaction(act,transaction);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        observer.onError(error);
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

    /**
     * Set the parameters needed by the web service to upload the form
     * @param form Form to be uploaded
     * @return Map containing the params (keys and values)
     */
    private Map<String,String> getParamsMap(Formulario form){
        Utils utils = new Utils(act);
        Map<String,String> params = new HashMap<String, String>();

        /*Datos generales*/
        params.put("nombre_entrevistador",form.getNombreEntrevistador());
        params.put("apellido_entrevistador",form.getApellidoEntrevistador());
        params.put("planes_sociales_requeridos",(new Gson()).toJson(form.getProgramasSocialesSolicitados()));
        Log.i("JSON",(new Gson()).toJson(form.getProgramasSocialesSolicitados()));

        /*Datos del solitante*/
        Solicitante solicitante = form.getSolicitante();
        params.put("id_solicitante",solicitante.getId()+"");
        params.put("nombres_solicitante",solicitante.getNombres());
        params.put("apellidos_solicitante",solicitante.getApellidos());
        params.put("cuil_solicitante",solicitante.getCuil()+"");
        params.put("telefono_principal_solicitante",solicitante.getTelefono());
        params.put("otro_telefono",solicitante.getOtro_telefono());
        params.put("foto",solicitante.getFoto());

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
        /*Convert the family list into json String*/
        Gson gson = new GsonBuilder().serializeNulls().create();
        String familiaresJson = gson.toJson(form.getFamiliares());
        params.put("familiares",familiaresJson);

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

    /**
     * Delete a form from the external database
     * @param formId the id of the from to be deleted
     * @param transaction transaction associated to the delete action (to be deleted if successful)
     */
    public void deleteForm(final int formId, final Transaction transaction, final Observer<Boolean> observer){
        String server_url = "http://"+ip+"/"+folder+"/deleteform.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        /*If the query is successful, delete transaction from pending transactions*/
                        if(response.equals("true")){
                            DatabaseAcces db = new DatabaseAcces();
                            db.deleteTransaction(act,transaction);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        observer.onError(error);
                        error.printStackTrace();
                    }
                }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("id_formulario",formId+"");

                return params;
            }
        };

        ServerSingleton.getInstance(act).addToRequestQueue(stringRequest);
    }
}
