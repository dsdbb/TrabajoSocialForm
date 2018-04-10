package ar.edu.uns.cs.trabajosocialform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ar.edu.uns.cs.trabajosocialform.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.java_classes.configuracion.Configuracion;

public class FormularioActivity extends AppCompatActivity {

    private List<String> formularios;
    private int contador_formularios;
    private LinearLayout contenedor;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        /*Creo la lista de Strings de los distintos sets de formularios a completar*/
        contador_formularios = 0;
        formularios = new ArrayList<String>();
        formularios.add("form_solicitante");
        formularios.add("form_apoderado");

        contenedor = (LinearLayout) findViewById(R.id.content_formulario);
        inflater = LayoutInflater.from(this);

        /*Agrego a la pantalla de la actividad el primer set del formulario a completar*/
        View laViewInflada = inflater.inflate(R.layout.form_solicitante, contenedor, false);
        contenedor.addView(laViewInflada);
        contador_formularios++;

         /*Adapto el la seccion*/
        adaptarSeccion("form_solicitante");

    }

    public void continuar(View view){
        if(contador_formularios<formularios.size()){
            /*Encuentro el id de la proxima seccion que debo mostrar*/
            String form = formularios.get(contador_formularios);
            int resourceId = this.getResources().getIdentifier(form, "layout", this.getPackageName());

            /*Elimino la view de la seccion anterior y pongo la nueva*/
            contenedor.removeAllViews();
            View laViewInflada = inflater.inflate(resourceId, contenedor, false);
            contenedor.addView(laViewInflada);
            contador_formularios++;

            adaptarSeccion(form);

            if(contador_formularios == formularios.size()){
                Button siguiente = findViewById(R.id.siguiente_button);
                siguiente.setText("Guardar");
            }

        }
        else{
            /*guardar formulario*/
            Log.i("I:", "Se guarda el form");
        }
    }

    public void adaptarSeccion(String seccion){
        try
        {
            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    openFileInput("config.txt")));

            /*Leo el String json del archivo de configuracion y hago la deserializacion en la clase configuracion con la
             librería GSON*/
            String json = fin.readLine();
            Configuracion config = (new Gson()).fromJson(json, Configuracion.class);

            Log.i("JSON: ",json);

            /*Creo una instancia de ViewAdapter para poder realizar la adaptacion del formulario de acuerdo al archivo
            * JSON de configuracion*/
            ViewAdapter va = new ViewAdapter(config,this);

            /*Llamo al metodo de adaptacion de acuerdo a la seccion que quiero mostrar*/
           switch(seccion){
                case "form_solicitante":va.adaptarSolicitante();break;
                case "form_apoderado":va.adaptarApoderado();break;
           }

            //fin.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
            ex.printStackTrace();
        }
    }





}
