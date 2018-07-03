package ar.edu.uns.cs.trabajosocialform.Presentation.ViewAdapter;

import android.app.Activity;
import android.view.View;

import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;


/**
 * Class to adapt the different detail fields according to the configuration files (Hide not corresponding views)
 */
public class DetailsViewAdapter {

    private Configuracion config;
    private Activity act;

    public DetailsViewAdapter(Configuracion config, Activity act){
        this.config = config;
        this.act = act;
    }

    public void adaptDetails(){
        /*Hide not corresponding fields*/
        adaptSolicitante();
        adaptApoderado();
        adaptDomicilio();
        adaptSituacionHabitacional();
        adaptCaracteristicasVivienda();
        adaptInfraestructuraBarrial();
    }

    public void adaptSolicitante(){
        boolean nombres = config.getDatos_solicitante().isNombres_solicitante();
        boolean apellidos = config.getDatos_solicitante().isApellidos_solicitante();
        boolean cuil = config.getDatos_solicitante().isCuil_solicitante();
        boolean telefono_principal = config.getDatos_solicitante().isTelefono_principal_solicitante();
        boolean otro_telefono = config.getDatos_solicitante().isOtro_telefono();

        if(!nombres){
            act.findViewById(R.id.detalle_nombre_solicitante).setVisibility(View.GONE);
        }
        if(!apellidos){
            act.findViewById(R.id.detalle_apellido_solicitante).setVisibility(View.GONE);
        }
        if(!cuil){
            act.findViewById(R.id.detalle_cuil_solicitante).setVisibility(View.GONE);
        }
        if(!telefono_principal){
            act.findViewById(R.id.detalle_telefono_principal_solicitante).setVisibility(View.GONE);
        }
        if(!otro_telefono){
            act.findViewById(R.id.detalle_otro_telefono_solicitante).setVisibility(View.GONE);
        }
    }


    public void adaptApoderado(){

        boolean nombres = config.getDatos_apoderado().isNombres_apoderado();
        boolean apellidos = config.getDatos_apoderado().isApellidos_apoderado();
        boolean cuil = config.getDatos_apoderado().isCuil_apoderado();
        boolean fecha_nac = config.getDatos_apoderado().isFecha_nac_apoderado();
        boolean telefono_principal = config.getDatos_apoderado().isTelefono_principal_apoderado();
        boolean motivos_de_poder = config.getDatos_apoderado().isMotivos_del_poder();

        if(!nombres){
            act.findViewById(R.id.detalle_nombre_apoderado).setVisibility(View.GONE);
        }
        if(!apellidos){
            act.findViewById(R.id.detalle_apellido_apoderado).setVisibility(View.GONE);
        }
        if(!cuil){
            act.findViewById(R.id.detalle_cuil_apoderado).setVisibility(View.GONE);
        }
        if(!telefono_principal){
            act.findViewById(R.id.detalle_telefono_principal_apoderado).setVisibility(View.GONE);
        }
        if(!fecha_nac){
            act.findViewById(R.id.detalle_fecha_nacimiento_apoderado).setVisibility(View.GONE);
        }
        if(!motivos_de_poder){
            act.findViewById(R.id.detalle_motivos_poder_apoderado).setVisibility(View.GONE);
        }

    }

    public void adaptDomicilio(){
        boolean calle = config.getDatos_domicilio().isCalle();
        boolean numero = config.getDatos_domicilio().isNumero();
        boolean manzana = config.getDatos_domicilio().isManzana();
        boolean monoblock_torre = config.getDatos_domicilio().isMonoblock_torre();
        boolean piso = config.getDatos_domicilio().isPiso();
        boolean acc_int = config.getDatos_domicilio().isAcc_int();
        boolean casa_depto = config.getDatos_domicilio().isCasa_dpto();
        boolean entre_calles = config.getDatos_domicilio().isEntre_calles();
        boolean barrio = config.getDatos_domicilio().isBarrio();
        boolean delegacion = config.getDatos_domicilio().isDelegacion();
        boolean localidad = config.getDatos_domicilio().isLocalidad();

        if(!calle){
            act.findViewById(R.id.detalle_calle).setVisibility(View.GONE);
        }
        if(!numero){
            act.findViewById(R.id.detalle_numero).setVisibility(View.GONE);
        }
        if(!manzana){
            act.findViewById(R.id.detalle_manzana).setVisibility(View.GONE);
        }
        if(!monoblock_torre){
            act.findViewById(R.id.detalle_monoblock_torre).setVisibility(View.GONE);
        }
        if(!piso){
            act.findViewById(R.id.detalle_piso).setVisibility(View.GONE);
        }
        if(!acc_int){
            act.findViewById(R.id.detalle_acc_int).setVisibility(View.GONE);
        }
        if(!casa_depto){
            act.findViewById(R.id.detalle_casa_dpto).setVisibility(View.GONE);
        }
        if(!entre_calles){
            act.findViewById(R.id.detalle_entre_calle1).setVisibility(View.GONE);
            act.findViewById(R.id.detalle_entre_calle2).setVisibility(View.GONE);
        }
        if(!barrio){
            act.findViewById(R.id.detalle_barrio).setVisibility(View.GONE);
        }
        if(!delegacion){
            act.findViewById(R.id.detalle_delegacion).setVisibility(View.GONE);
        }

        if(!localidad){
            act.findViewById(R.id.detalle_localidad).setVisibility(View.GONE);
        }

    }

    public void adaptCaracteristicasVivienda(){

        boolean techo = config.getDatos_caracteristicas_vivienda().isTecho();
        boolean paredes = config.getDatos_caracteristicas_vivienda().isParedes();
        boolean piso = config.getDatos_caracteristicas_vivienda().isPisos();
        boolean servicios = config.getDatos_caracteristicas_vivienda().isServicios();
        boolean banio = config.getDatos_caracteristicas_vivienda().isBanio();
        boolean cocina = config.getDatos_caracteristicas_vivienda().isCocina();


        if(!techo){
            act.findViewById(R.id.detalle_techo).setVisibility(View.GONE);
            act.findViewById(R.id.detalle_revestimiento_techo).setVisibility(View.GONE);
        }
        if(!paredes){
            act.findViewById(R.id.detalle_paredes).setVisibility(View.GONE);
            act.findViewById(R.id.detalle_revestimiento_paredes).setVisibility(View.GONE);
        }
        if(!piso){
            act.findViewById(R.id.detalle_piso).setVisibility(View.GONE);
        }
        if(!servicios){
            act.findViewById(R.id.detalle_agua).setVisibility(View.GONE);
            act.findViewById(R.id.detalle_fuente_agua).setVisibility(View.GONE);
            act.findViewById(R.id.detalle_desague).setVisibility(View.GONE);
            act.findViewById(R.id.detalle_electricidad).setVisibility(View.GONE);
            act.findViewById(R.id.detalle_combustible_cocina).setVisibility(View.GONE);
        }
        if(!banio){
            act.findViewById(R.id.detalle_baño).setVisibility(View.GONE);
            act.findViewById(R.id.detalle_baño_tiene).setVisibility(View.GONE);

        }
        if(!cocina){
            act.findViewById(R.id.detalle_cocina).setVisibility(View.GONE);
        }

    }

    public void adaptSituacionHabitacional() {

        boolean tipo_vivienda = config.getDatos_situacion_habitacional().isTipo_vivienda();
        boolean tenencia_terreno_vivienda = config.getDatos_situacion_habitacional().isTenencia_vivienda_terreno();
        boolean tiempo_ocupacion = config.getDatos_situacion_habitacional().isTiempo_ocupacion();
        boolean cantidad_de_hogares = config.getDatos_situacion_habitacional().isCantidad_hogares_vivienda();
        boolean cantidad_de_cuartos_UE = config.getDatos_situacion_habitacional().isCantidad_cuartos_ue();

        if(!tipo_vivienda){
            act.findViewById(R.id.detalle_tipo_vivienda).setVisibility(View.GONE);
        }

        if(!tenencia_terreno_vivienda){
            act.findViewById(R.id.detalle_tenencia_vivienda_terreno).setVisibility(View.GONE);
        }
        if(!tiempo_ocupacion){
            act.findViewById(R.id.detalle_tiempo_ocupacion).setVisibility(View.GONE);
        }
        if(!cantidad_de_hogares){
            act.findViewById(R.id.detalle_cantidad_hogares_vivienda).setVisibility(View.GONE);
        }
        if(!cantidad_de_cuartos_UE){
            act.findViewById(R.id.detalle_cantidad_cuartos_ue).setVisibility(View.GONE);
        }
    }

    public void adaptInfraestructuraBarrial(){
        boolean infraestructura_calles = config.getDatos_infraestructura_barrial().isCalles();
        boolean iluminacion = config.getDatos_infraestructura_barrial().isIluminacion();
        boolean inundacion = config.getDatos_infraestructura_barrial().isInundacion();
        boolean recoleccion = config.getDatos_infraestructura_barrial().isRecoleccion_basura();
        boolean distancias = config.getDatos_infraestructura_barrial().isDistancias();

        if(!infraestructura_calles){
            act.findViewById(R.id.detalle_infraestructura_calles).setVisibility(View.GONE);
        }
        if(!iluminacion){
            act.findViewById(R.id.detalle_iluminacion_publica).setVisibility(View.GONE);
        }
        if(!inundacion){
            act.findViewById(R.id.detalle_inundacion).setVisibility(View.GONE);
        }
        if(!recoleccion){
            act.findViewById(R.id.detalle_recoleccion_residuos).setVisibility(View.GONE);
        }
        if(!distancias){
            act.findViewById(R.id.detalle_distancia_salud).setVisibility(View.GONE);
            act.findViewById(R.id.detalle_distancia_educacion).setVisibility(View.GONE);
            act.findViewById(R.id.detalle_distancia_transporte).setVisibility(View.GONE);
        }

    }
}
