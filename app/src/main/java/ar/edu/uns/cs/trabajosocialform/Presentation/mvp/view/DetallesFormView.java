package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.Date;
import java.util.List;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Apoderado;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.CaracteristicasVivienda;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Domicilio;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Familiar;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.InfraestructuraBarrial;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.SituacionHabitacional;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Solicitante;
import ar.edu.uns.cs.trabajosocialform.Data.Database.DatabaseAcces;
import ar.edu.uns.cs.trabajosocialform.Data.Database.StorageAccess;
import ar.edu.uns.cs.trabajosocialform.Presentation.adapters.EntradasRecyclerViewAdapter;
import ar.edu.uns.cs.trabajosocialform.Presentation.adapters.FamiliaresDetallesRecyclerViewAdapter;
import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;
import ar.edu.uns.cs.trabajosocialform.Presentation.ViewAdapter.DetailsViewAdapter;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetallesFormView extends ActivityView {

    Formulario form;
    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.recycler_view)RecyclerView recyclerView;
    @BindView(R.id.detalles_planes_sociales_requeridos)LinearLayout contenedor;
    @BindView(R.id.imageView)ImageView imageView;

    public DetallesFormView(AppCompatActivity activity){
        super(activity);
        ButterKnife.bind(this,activity);
    }


   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_form);

        form = (Formulario)getIntent().getSerializableExtra("FORM");

        inicializarGui();

        String jsonConfig = (new StorageAccess()).getConfigurationJson(this);
        Configuracion config = (new Gson()).fromJson(jsonConfig,Configuracion.class);
        DetailsViewAdapter adapter = new DetailsViewAdapter(config,this);
        adapter.adaptDetails();
    }*/

    public void inicializarGui(Formulario form){
        Utils utils = new Utils(getActivity());

        /*Titulo detalles*/
        toolbar.setTitle(R.string.titulo_detalles);

        /*Datos generales*/
        utils.setTitleValue(R.id.titulo_detalles_general,R.string.titulo_datos_generales);
        utils.setDetailValues(R.id.detalle_nombre_entrevistador,R.string.nombre_entrevistador,form.getNombreEntrevistador());
        utils.setDetailValues(R.id.detalle_apellido_entrevistador, R.string.apellido_entrevistador, form.getApellidoEntrevistador());
        for (String plan:form.getProgramasSocialesSolicitados()) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());

            View inflatedView = inflater.inflate(R.layout.detalles_item, contenedor, false);
            utils.inflarDetalles(R.id.detalles_planes_sociales_requeridos);
            contenedor.addView(inflatedView);
            utils.setDetailValues(inflatedView, R.string.titulo_planes_sociales,plan);
        }

        /*Datos del solicitante*/
        //Solicitante solicitante = db.getSolicitante(this,form.getSolicitanteId());
        Solicitante solicitante = form.getSolicitante();
        utils.setTitleValue(R.id.titulo_detalles_solicitante, R.string.titulo_detalles_solicitante);
        utils.setDetailValues(R.id.detalle_nombre_solicitante, R.string.nombres_solicitante, solicitante.getNombres());
        utils.setDetailValues(R.id.detalle_apellido_solicitante, R.string.apellidos_solicitante,solicitante.getApellidos());
        utils.setDetailValues(R.id.detalle_cuil_solicitante,R.string.cuil_solicitante, solicitante.getCuil()+"");
        utils.setDetailValues(R.id.detalle_telefono_principal_solicitante, R.string.telefono_principal_solicitante, solicitante.getTelefono());
        utils.setDetailValues(R.id.detalle_otro_telefono_solicitante, R.string.otro_telefono_solicitante, solicitante.getOtro_telefono());
        Bitmap foto = utils.stringToBitmap(solicitante.getFoto());
        imageView.setImageBitmap(foto);

        /*Datos del apoderado*/
        //Apoderado apoderado = db.getApoderado(this,form.getApoderadoId());
        Apoderado apoderado = form.getApoderado();
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
        //Domicilio domicilio = db.getDomicilio(this,form.getDomicilioId());
        Domicilio domicilio = form.getDomicilio();
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
        //List<Familiar> familiares = db.getFamiliares(this, form.getId());
        List<Familiar> familiares = form.getFamiliares();
        Log.i("FAMILIARES SIZE: ",familiares.size()+"");
        addFamiliares(familiares);

        /*Situacion Habitacional*/
        //SituacionHabitacional situacionHabitacional = db.getSituacionHabitacional(this, form.getSituacionHabitacionalId());
        SituacionHabitacional situacionHabitacional = form.getSituacionHabitacional();
        utils.setTitleValue(R.id.titulo_detalles_situacion_habitacional, R.string.titulo_detalles_situacion_habitacional);
        utils.setDetailValues(R.id.detalle_tipo_vivienda,R.string.tipo_de_vivienda,situacionHabitacional.getTipo_vivienda());
        utils.setDetailValues(R.id.detalle_tenencia_vivienda_terreno, R.string.tenencia_vivienda_terreno, situacionHabitacional.getTenencia_vivienda_terreno());
        utils.setDetailValues(R.id.detalle_tiempo_ocupacion, R.string.tiempo_de_ocupacion, situacionHabitacional.getTiempo_ocupacion()+"");
        utils.setDetailValues(R.id.detalle_cantidad_hogares_vivienda, R.string.cantidad_de_hogares_en_vivienda, situacionHabitacional.getCantidad_hogares_vivienda()+"");
        utils.setDetailValues(R.id.detalle_cantidad_cuartos_ue, R.string.cantidad_de_cuartos_UE, situacionHabitacional.getCantidad_cuartos_ue()+"");

        /*Caracteristicas de la vivienda*/
        //CaracteristicasVivienda caracteristicasVivienda = db.getCaracteristicasVivienda(this, form.getCaracteristicasViviendaId());
        CaracteristicasVivienda caracteristicasVivienda = form.getCaracteristicasVivienda();
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
        //InfraestructuraBarrial infraestructuraBarrial = db.getInfraestructuraBarrial(this,form.getInfraestructuraBarrialId());
        InfraestructuraBarrial infraestructuraBarrial = form.getInfraestructuraBarrial();
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
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        FamiliaresDetallesRecyclerViewAdapter adapter = new FamiliaresDetallesRecyclerViewAdapter(familiares);
        recyclerView.setAdapter(adapter);

        //(new Utils(this)).setListViewHeightBasedOnChildren(listview);

        /*listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = new Intent(DetallesFormView.this,DetallesFamiliarView.class);
                Familiar familiar = familiares.get(arg2);
                intent.putExtra("FAMILIAR",familiar);
                startActivity(intent);
            }
        });*/
    }



}
