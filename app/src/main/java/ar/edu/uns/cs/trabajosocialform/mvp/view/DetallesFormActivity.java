package ar.edu.uns.cs.trabajosocialform.mvp.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.Date;
import java.util.List;

import ar.edu.uns.cs.trabajosocialform.DataModel.Apoderado;
import ar.edu.uns.cs.trabajosocialform.DataModel.CaracteristicasVivienda;
import ar.edu.uns.cs.trabajosocialform.DataModel.Domicilio;
import ar.edu.uns.cs.trabajosocialform.DataModel.Familiar;
import ar.edu.uns.cs.trabajosocialform.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.DataModel.InfraestructuraBarrial;
import ar.edu.uns.cs.trabajosocialform.DataModel.SituacionHabitacional;
import ar.edu.uns.cs.trabajosocialform.DataModel.Solicitante;
import ar.edu.uns.cs.trabajosocialform.Database.DatabaseAcces;
import ar.edu.uns.cs.trabajosocialform.Database.StorageAccess;
import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;
import ar.edu.uns.cs.trabajosocialform.ViewAdapter.DetailsViewAdapter;
import ar.edu.uns.cs.trabajosocialform.configuracion.Configuracion;

public class DetallesFormActivity extends AppCompatActivity {

    Formulario form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_form);

        form = (Formulario)getIntent().getSerializableExtra("FORM");

        inicializarGui();

        String jsonConfig = (new StorageAccess()).getConfigurationJson(this);
        Configuracion config = (new Gson()).fromJson(jsonConfig,Configuracion.class);
        DetailsViewAdapter adapter = new DetailsViewAdapter(config,this);
        adapter.adaptDetails();
    }

    private void inicializarGui(){
        DatabaseAcces db = new DatabaseAcces();
        Utils utils = new Utils(this);

        /*Titulo detalles*/
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.titulo_detalles);

        /*Datos del solicitante*/
        Solicitante solicitante = db.getSolicitante(this,form.getSolicitanteId());
        utils.setTitleValue(R.id.titulo_detalles_solicitante, R.string.titulo_detalles_solicitante);
        utils.setDetailValues(R.id.detalle_nombre_solicitante, R.string.nombres_solicitante, solicitante.getNombres());
        utils.setDetailValues(R.id.detalle_apellido_solicitante, R.string.apellidos_solicitante,solicitante.getApellidos());
        utils.setDetailValues(R.id.detalle_cuil_solicitante,R.string.cuil_solicitante, solicitante.getCuil()+"");
        utils.setDetailValues(R.id.detalle_telefono_principal_solicitante, R.string.telefono_principal_solicitante, solicitante.getTelefono());
        utils.setDetailValues(R.id.detalle_otro_telefono_solicitante, R.string.otro_telefono_solicitante, solicitante.getOtro_telefono());
        Bitmap foto = utils.stringToBitmap(solicitante.getFoto());
        ((ImageView)findViewById(R.id.imageView)).setImageBitmap(foto);

        /*Datos del apoderado*/
        Apoderado apoderado = db.getApoderado(this,form.getApoderadoId());
        utils.setTitleValue(R.id.titulo_detalles_apoderado, R.string.titulo_detalles_apoderado);
        utils.setDetailValues(R.id.detalle_nombre_apoderado, R.string.nombres_apoderado, apoderado.getNombres());
        utils.setDetailValues(R.id.detalle_apellido_apoderado, R.string.apellidos_apoderado, apoderado.getApellidos());
        utils.setDetailValues(R.id.detalle_cuil_apoderado, R.string.cuil_apoderado, apoderado.getCuil()+"");
        utils.setDetailValues(R.id.detalle_telefono_principal_apoderado, R.string.telefono_apoderado, apoderado.getTelefono());
        Date fecha = apoderado.getFecha_nacimiento();
        String fechaNac = utils.getStringFromDate(fecha);
        utils.setDetailValues(R.id.detalle_fecha_nacimiento_apoderado, R.string.fecha_nac_apoderado, fechaNac);
        utils.setDetailValues(R.id.detalle_motivos_poder_apoderado, R.string.motivos_de_poder, apoderado.getMotivos_poder());

        /*Datos del domicilio*/
        Domicilio domicilio = db.getDomicilio(this,form.getDomicilioId());
        utils.setTitleValue(R.id.titulo_detalles_domicilio, R.string.titulo_detalles_domicilio);
        utils.setDetailValues(R.id.detalle_calle, R.string.calle, domicilio.getCalle());
        utils.setDetailValues(R.id.detalle_numero, R.string.numero, domicilio.getNumero()+"");
        utils.setDetailValues(R.id.detalle_manzana, R.string.manzana, domicilio.getManzana()+"");
        utils.setDetailValues(R.id.detalle_monoblock_torre, R.string.monoblock_torre, domicilio.getMonoblock_torre()+"");
        utils.setDetailValues(R.id.detalle_piso, R.string.piso, domicilio.getPiso()+"");
        utils.setDetailValues(R.id.detalle_acc_int, R.string.acc_int, domicilio.getAcc_int()+"");
        utils.setDetailValues(R.id.detalle_casa_dpto, R.string.casa_dpto, domicilio.getCasa_dpto()+"");
        utils.setDetailValues(R.id.detalle_entre_calle1, R.string.entre_calle1, domicilio.getEntre_calle1());
        utils.setDetailValues(R.id.detalle_entre_calle2, R.string.entre_calle2, domicilio.getEntre_calle2());
        utils.setDetailValues(R.id.detalle_barrio, R.string.barrio, domicilio.getBarrio());
        utils.setDetailValues(R.id.detalle_localidad, R.string.localidad, domicilio.getLocalidad());
        utils.setDetailValues(R.id.detalle_delegacion, R.string.delegacion, domicilio.getDelegacion());

        /*Familiares*/
        utils.setTitleValue(R.id.titulo_familiares, R.string.titulo_detalles_familiares);
        List<Familiar> familiares = db.getFamiliares(this, form.getId());
        Log.i("FAMILIARES SIZE: ",familiares.size()+"");
        addFamiliares(familiares);

        /*Situacion Habitacional*/
        SituacionHabitacional situacionHabitacional = db.getSituacionHabitacional(this, form.getSituacionHabitacionalId());
        utils.setTitleValue(R.id.titulo_detalles_situacion_habitacional, R.string.titulo_detalles_situacion_habitacional);
        utils.setDetailValues(R.id.detalle_tipo_vivienda,R.string.tipo_de_vivienda,situacionHabitacional.getTipo_vivienda());
        utils.setDetailValues(R.id.detalle_tenencia_vivienda_terreno, R.string.tenencia_vivienda_terreno, situacionHabitacional.getTenencia_vivienda_terreno());
        utils.setDetailValues(R.id.detalle_tiempo_ocupacion, R.string.tiempo_de_ocupacion, situacionHabitacional.getTiempo_ocupacion()+"");
        utils.setDetailValues(R.id.detalle_cantidad_hogares_vivienda, R.string.cantidad_de_hogares_en_vivienda, situacionHabitacional.getCantidad_hogares_vivienda()+"");
        utils.setDetailValues(R.id.detalle_cantidad_cuartos_ue, R.string.cantidad_de_cuartos_UE, situacionHabitacional.getCantidad_cuartos_ue()+"");

        /*Caracteristicas de la vivienda*/
        CaracteristicasVivienda caracteristicasVivienda = db.getCaracteristicasVivienda(this, form.getCaracteristicasViviendaId());
        utils.setTitleValue(R.id.titulo_detalles_caracteristicas_vivienda, R.string.titulo_detalles_caracteristicas_vivienda);
        utils.setDetailValues(R.id.detalle_techo, R.string.techo_caracteristicas, caracteristicasVivienda.getMaterial_techo());
        String revestTecho = utils.getStringFromBoolean(caracteristicasVivienda.isRevestimiento_techo());
        utils.setDetailValues(R.id.detalle_revestimiento_techo, R.string.titulo_revestimiento_techo, revestTecho);
        utils.setDetailValues(R.id.detalle_paredes, R.string.paredes_caracteristicas, caracteristicasVivienda.getMaterial_paredes());
        String revestParedes = utils.getStringFromBoolean(caracteristicasVivienda.isRevestimiento_paredes());
        utils.setDetailValues(R.id.detalle_pisos, R.string.pisos_caracteristicas, caracteristicasVivienda.getMaterial_pisos());
        utils.setDetailValues(R.id.detalle_agua, R.string.titulo_agua, caracteristicasVivienda.getAgua());
        utils.setDetailValues(R.id.detalle_fuente_agua, R.string.titulo_fuente_agua, caracteristicasVivienda.getFuente_agua());
        utils.setDetailValues(R.id.detalle_baño, R.string.titulo_baño, caracteristicasVivienda.getBanio());
        utils.setDetailValues(R.id.detalle_baño_tiene, R.string.titulo_baño_tiene, caracteristicasVivienda.getBanio_tiene());
        utils.setDetailValues(R.id.detalle_desague, R.string.titulo_desague, caracteristicasVivienda.getDesague());
        String cocina = utils.getStringFromBoolean(caracteristicasVivienda.isCocina());
        utils.setDetailValues(R.id.detalle_cocina, R.string.titulo_cocina, cocina);
        utils.setDetailValues(R.id.detalle_combustible_cocina, R.string.titulo_combustible_cocina, caracteristicasVivienda.getCombustible_cocina());

        /*Infraestructura barrial*/
        InfraestructuraBarrial infraestructuraBarrial = db.getInfraestructuraBarrial(this,form.getInfraestructuraBarrialId());
        utils.setTitleValue(R.id.titulo_detalles_infraestructura_barrial, R.string.titulo_detalles_infraestructura_barrial);
        utils.setDetailValues(R.id.detalle_infraestructura_calles,R.string.titulo_infraestructura_calles, infraestructuraBarrial.getInfraestructura_calles());
        utils.setDetailValues(R.id.detalle_iluminacion_publica, R.string.titulo_iluminacion, infraestructuraBarrial.getIluminacion());
        String recoleccion = utils.getStringFromBoolean(infraestructuraBarrial.isRecoleccion_residuos());
        utils.setDetailValues(R.id.detalle_recoleccion_residuos, R.string.titulo_recoleccion, recoleccion);
        String inundacion = utils.getStringFromBoolean(infraestructuraBarrial.isInundacion());
        utils.setDetailValues(R.id.detalle_inundacion, R.string.titulo_inundacion, inundacion);
        utils.setDetailValues(R.id.detalle_distancia_salud, R.string.titulo_distancia_salud, infraestructuraBarrial.getDistancia_salud());
        utils.setDetailValues(R.id.detalle_distancia_educacion, R.string.titulo_distancia_educacion, infraestructuraBarrial.getDistancia_educacion());
        utils.setDetailValues(R.id.detalle_distancia_transporte, R.string.titulo_distancia_transporte, infraestructuraBarrial.getDistancia_transporte());

    }



    private void addFamiliares(final List<Familiar> familiares){
        final ListView listview = (ListView) findViewById(R.id.familiares_list_view);
        listview.setAdapter(new customAdapter(this,familiares));
        (new Utils(this)).setListViewHeightBasedOnChildren(listview);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = new Intent(DetallesFormActivity.this,DetallesFamiliarActivity.class);
                Familiar familiar = familiares.get(arg2);
                intent.putExtra("FAMILIAR",familiar);
                startActivity(intent);
            }
        });
    }

    public class customAdapter extends BaseAdapter {

        private Context context;
        private List<Familiar> familiares;
        private int position;

        public customAdapter(Context context, List<Familiar> familiares){
            this.context = context;
            this.familiares = familiares;
        }

        public View getView(int position, View convertView, ViewGroup container) {
            this.position = position;

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_familiar, container, false);
            }
            Familiar familiar = familiares.get(position);
            ((TextView) convertView.findViewById(R.id.label)).setText(familiar.getNombres() + " " + familiar.getApellidos());
            return convertView;

        }

        public int getPosition(){
            return position;
        }
        public final int getCount() {
            return familiares.size();
        }

        public Object getItem(int position) {
            return familiares.get(position);
        }

        public long getItemId(int position) {
            return familiares.get(position).getId();
        }

    }


}
