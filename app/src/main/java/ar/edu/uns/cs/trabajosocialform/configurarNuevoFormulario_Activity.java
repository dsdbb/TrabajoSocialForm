package ar.edu.uns.cs.trabajosocialform;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;

import ar.edu.uns.cs.trabajosocialform.configuracion.Configuracion;

public class configurarNuevoFormulario_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_nuevo_formulario_);

        CheckBox seleccionar_todo = (CheckBox) findViewById(R.id.seleccionar_todo);
        /*Tomo el layout donde se hacen los include de los diferentes archivos de checkboxes. Luego dentro de cada uno de esos
        * ConstraintLayout busco los checkboxes para cambiar su valor de acuerdo a si esta seleccionado o no el checkbox de
        * seleccionar_todos*/
        seleccionar_todo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                   LinearLayout content= (LinearLayout) findViewById(R.id.content);
                   int count = content.getChildCount();
                   for(int i=0;i<count;i++){
                       View v = content.getChildAt(i);
                       if(v instanceof ConstraintLayout){
                           int count2 = ((ConstraintLayout) v).getChildCount();
                           for(int j=0;j<count2;j++){
                               if(((ConstraintLayout) v).getChildAt(j) instanceof CheckBox){
                                   CheckBox check = ((CheckBox)((ConstraintLayout) v).getChildAt(j));
                                   if(isChecked) {
                                       check.setChecked(true);
                                   }
                                   else{
                                       check.setChecked(false);
                                   }
                               }
                           }
                       }
                   }
               }
           }
        );
    }

    /**
     * Creo el objeto JSON que definirá la configuracion del formulario
     * @param view
     */
    public void crearFormulario(View view){
        LinearLayout content= (LinearLayout) findViewById(R.id.content);
        int count = content.getChildCount();
        Configuracion conf = new Configuracion();
        for(int i=0;i<count;i++){
            View v = content.getChildAt(i);
            if(v instanceof ConstraintLayout) {
                Log.i("V: ","Es ConstraintLayout");
                int count2 = ((ConstraintLayout) v).getChildCount();
                for (int j = 0; j < count2; j++) {
                    if (((ConstraintLayout) v).getChildAt(j) instanceof CheckBox) {
                        Log.i("V: ","Es checkbox");
                        CheckBox checkbox = (CheckBox)((ConstraintLayout) v).getChildAt(j);
                        setConfiguration(conf,checkbox);
                    }
                }
            }
        }

        /*Almaceno el archivo JSON de configuracion en el almacenamiento interno de la aplicación*/
        String json = (new Gson()).toJson(conf);
        Log.i("JSON: ",json);
        String filename = "config.txt";
        File file = new File(this.getFilesDir(), filename);
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, this.MODE_PRIVATE);
            outputStream.write(json.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*Desactivo el boton de crear formulario. Por ahora doy solo la posibilidad de crear solo uno*/
        //findViewById(R.id.crearFormulario_button).setEnabled(false);

       finish();


    }

    private void setConfiguration(Configuracion conf, CheckBox checkbox){
        String id = checkbox.getResources().getResourceEntryName(checkbox.getId());
        if(checkbox.isChecked()){
            Log.i("V: ","IS CHECKED"+id);
            switch (id){
                /*Checkboxes relacionados al Solicitante*/
                case "nombres_solicitante": conf.getDatos_solicitante().setNombres_solicitante(true); break;
                case "apellidos_solicitante":conf.getDatos_solicitante().setApellidos_solicitante(true);break;
                case "cuil_solicitante":conf.getDatos_solicitante().setCuil_solicitante(true);break;
                case "telefono_principal_solicitante":conf.getDatos_solicitante().setTelefono_principal_solicitante(true);break;
                case "otro_telefono_solicitante":conf.getDatos_solicitante().setOtro_telefono(true);break;
                /*Checboxes relacionados al Apoderado*/
                case "nombres_apoderado":conf.getDatos_apoderado().setNombres_apoderado(true);break;
                case "apellidos_apoderado":conf.getDatos_apoderado().setApellidos_apoderado(true);break;
                case "fecha_nac_apoderado":conf.getDatos_apoderado().setFecha_nac_apoderado(true);break;
                case "cuil_apoderado":conf.getDatos_apoderado().setCuil_apoderado(true);break;
                case "telefono_apoderado":conf.getDatos_apoderado().setTelefono_principal_apoderado(true);break;
                case "motivos_de_poder":conf.getDatos_apoderado().setMotivos_del_poder(true);break;
                /*Checkboxes relacionados al domicilio*/
                case "calle":conf.getDatos_domicilio().setCalle(true);break;
                case "numero":conf.getDatos_domicilio().setNumero(true);break;
                case "manzana":conf.getDatos_domicilio().setManzana(true);break;
                case "monoblock_torre":conf.getDatos_domicilio().setMonoblock_torre(true);break;
                case "piso":conf.getDatos_domicilio().setPiso(true);break;
                case "casa_depto":conf.getDatos_domicilio().setCasa_dpto(true);
                case "entre_calles":conf.getDatos_domicilio().setEntre_calles(true);break;
                case "barrio":conf.getDatos_domicilio().setBarrio(true);break;
                case "localidad":conf.getDatos_domicilio().setLocalidad(true);break;
                case "delegacion":conf.getDatos_domicilio().setDelegacion(true);break;
                case "acc_int":conf.getDatos_domicilio().setAcc_int(true);break;
                /*Checkboxes relacionados al grupo familiar*/
                case "nombres_gf":conf.getDatos_grupo_familiar().setNombres(true);break;
                case "apellidos_gf":conf.getDatos_grupo_familiar().setApellidos(true);break;
                case "cuil_gf":conf.getDatos_grupo_familiar().setCuil(true);break;
                case "sexo_gf":conf.getDatos_grupo_familiar().setSexo(true);break;
                case "fecha_nac_gf":conf.getDatos_grupo_familiar().setFecha_nac(true);break;
                case "estado_civil_gf":conf.getDatos_grupo_familiar().setEstado_civil(true);break;
                case "nacion_gf":conf.getDatos_grupo_familiar().setNacion(true);break;
                case "vinculo_gf":conf.getDatos_grupo_familiar().setVinculo(true);break;
                case "educacion_gf":conf.getDatos_grupo_familiar().setEducacion(true);break;
                case "nucleo_gf":conf.getDatos_grupo_familiar().setNucleo(true);break;
                    /*Ocupacion*/
                    case "condicion_actividad_cb":conf.getDatos_grupo_familiar().getDatos_ocupacion().setCondicion_de_actividad(true);
                    case "puesto_trabajo_cb":conf.getDatos_grupo_familiar().getDatos_ocupacion().setPuesto_de_trabajo(true);
                    case "aporte_jubilatorio_cb":conf.getDatos_grupo_familiar().getDatos_ocupacion().setAporte_jubilatorio(true);
                    case "duracion_empleo_cb":conf.getDatos_grupo_familiar().getDatos_ocupacion().setDuracion(true);
                    case "tipo_actividad_cb":conf.getDatos_grupo_familiar().getDatos_ocupacion().setTipo_de_actividad(true);
                    case "calificacion_cb":conf.getDatos_grupo_familiar().getDatos_ocupacion().setCalificacion(true);
                    /*Ingresos*/
                    case "ingresos_laborales_cb":conf.getDatos_grupo_familiar().getDatos_ingresos().setIngresos_laborales(true);
                    case "ingresos_no_laborales_cb":conf.getDatos_grupo_familiar().getDatos_ingresos().setIngresos_no_laborales(true);
                    case "programas_sociales_sti_cb":conf.getDatos_grupo_familiar().getDatos_ingresos().setPrograma_sociales_sti(true);
                    /*Salud*/
                    case "cobertura_cb":conf.getDatos_grupo_familiar().getDatos_salud().setCobertura(true);
                    case "embarazo_cb":conf.getDatos_grupo_familiar().getDatos_salud().setEmbarazo(true);
                    case "discapacidad_cb":conf.getDatos_grupo_familiar().getDatos_salud().setDiscapacidad(true);
                    case "enfermedad_cronica_cb":conf.getDatos_grupo_familiar().getDatos_salud().setEnfermedad_cronica(true);
                    case "independencia_funcional_cb":conf.getDatos_grupo_familiar().getDatos_salud().setIndependencia_funcional(true);
                /*Checkboxes relacionados a la vivienda*/
                case "banio_caracteristicas":conf.getDatos_caracteristicas_vivienda().setBanio(true);break;
                case "paredes_caracteristicas":conf.getDatos_caracteristicas_vivienda().setParedes(true);break;
                case "pisos_caracteristicas":conf.getDatos_caracteristicas_vivienda().setPisos(true);break;
                case "servicios_caracteristicas":conf.getDatos_caracteristicas_vivienda().setServicios(true);break;
                case "techo_caracteristicas":conf.getDatos_caracteristicas_vivienda().setTecho(true);break;
                case "cocina_caracteristicas":conf.getDatos_caracteristicas_vivienda().setCocina(true);break;
                /*Checkboxes relacionados a la situacion habitacional*/
                case "cantidad_de_cuartos_UE":conf.getDatos_situacion_habitacional().setCantidad_de_cuartos_UE(true);break;
                case "cantidad_de_hogares_en_vivienda":conf.getDatos_situacion_habitacional().setCantidad_de_hogares_en_vivienda(true);break;
                case "tenencia_vivienda_terreno":conf.getDatos_situacion_habitacional().setTenencia_vivienda_terreno(true);break;
                case "tiempo_de_ocupacion":conf.getDatos_situacion_habitacional().setTiempo_de_ocupacion(true);break;
                case "tipo_de_vivienda":conf.getDatos_situacion_habitacional().setTipo_de_vivienda(true);break;
                /*Checkboxes relacionados a la infraestructura barrial*/
                case "calles":conf.getDatos_infraestructura_barrial().setCalles(true);break;
                case "distancias":conf.getDatos_infraestructura_barrial().setDistancias(true);break;
                case "iluminacion":conf.getDatos_infraestructura_barrial().setIluminacion(true);break;
                case "inundaciones":conf.getDatos_infraestructura_barrial().setInundacion(true);break;
                case "recoleccion_residuos":conf.getDatos_infraestructura_barrial().setRecoleccion_basura(true);break;



            }
        }

    }

}
