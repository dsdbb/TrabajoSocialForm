package ar.edu.uns.cs.trabajosocialform.ViewAdapter;

import android.app.Activity;
import android.view.View;

import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.configuracion.Configuracion;

/**
 * Class to adapt the different inserting fields according to the configuration files (Hide not corresponding views)
 */

public class ViewAdapter {

    private Configuracion config;
    private Activity act;

    public ViewAdapter(Configuracion config,Activity act){
        this.config = config;
        this.act = act;
    }

    public void adaptarSolicitante(){

        boolean nombres = config.getDatos_solicitante().isNombres_solicitante();
        boolean apellidos = config.getDatos_solicitante().isApellidos_solicitante();
        boolean cuil = config.getDatos_solicitante().isCuil_solicitante();
        boolean telefono_principal = config.getDatos_solicitante().isTelefono_principal_solicitante();
        boolean otro_telefono = config.getDatos_solicitante().isOtro_telefono();


        if(!nombres){
            act.findViewById(R.id.panel_nombres_solicitante).setVisibility(View.GONE);
        }
        if(!apellidos){
            act.findViewById(R.id.panel_apellidos_solicitante).setVisibility(View.GONE);
        }
        if(!cuil){
            act.findViewById(R.id.panel_cuil_solicitante).setVisibility(View.GONE);
        }
        if(!telefono_principal){
            act.findViewById(R.id.panel_telefono_principal_solicitante).setVisibility(View.GONE);
        }
        if(!otro_telefono){
            act.findViewById(R.id.panel_otro_telefono_solicitante).setVisibility(View.GONE);
        }

    }

    public void adaptarApoderado(){

        boolean nombres = config.getDatos_apoderado().isNombres_apoderado();
        boolean apellidos = config.getDatos_apoderado().isApellidos_apoderado();
        boolean cuil = config.getDatos_apoderado().isCuil_apoderado();
        boolean fecha_nac = config.getDatos_apoderado().isFecha_nac_apoderado();
        boolean telefono_principal = config.getDatos_apoderado().isTelefono_principal_apoderado();
        boolean motivos_de_poder = config.getDatos_apoderado().isMotivos_del_poder();

        if(!nombres){
            act.findViewById(R.id.panel_nombres_apoderado).setVisibility(View.GONE);
        }
        if(!apellidos){
            act.findViewById(R.id.panel_apellidos_apoderado).setVisibility(View.GONE);
        }
        if(!cuil){
            act.findViewById(R.id.panel_cuil_apoderado).setVisibility(View.GONE);
        }
        if(!telefono_principal){
            act.findViewById(R.id.panel_telefono_principal_apoderado).setVisibility(View.GONE);
        }
        if(!fecha_nac){
            act.findViewById(R.id.panel_fecha_nacimiento_apoderado).setVisibility(View.GONE);
        }
        if(!motivos_de_poder){
            act.findViewById(R.id.panel_motivos_poder_apoderado).setVisibility(View.GONE);
        }

    }

    public void adaptarDomicilio(){
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
            act.findViewById(R.id.panel_calle).setVisibility(View.GONE);
        }
        if(!numero){
            act.findViewById(R.id.panel_numero).setVisibility(View.GONE);
        }
        if(!manzana){
            act.findViewById(R.id.panel_manzana).setVisibility(View.GONE);
        }
        if(!monoblock_torre){
            act.findViewById(R.id.panel_monoblock_torre).setVisibility(View.GONE);
        }
        if(!piso){
            act.findViewById(R.id.panel_piso).setVisibility(View.GONE);
        }
        if(!acc_int){
            act.findViewById(R.id.panel_acc_int).setVisibility(View.GONE);
        }
        if(!casa_depto){
            act.findViewById(R.id.panel_casa_depto).setVisibility(View.GONE);
        }
        if(!entre_calles){
            act.findViewById(R.id.panel_entre_calles1).setVisibility(View.GONE);
            act.findViewById(R.id.panel_entre_calles2).setVisibility(View.GONE);
        }
        if(!barrio){
            act.findViewById(R.id.panel_barrio).setVisibility(View.GONE);
        }
        if(!delegacion){
            act.findViewById(R.id.panel_delegacion).setVisibility(View.GONE);
        }

        if(!localidad){
            act.findViewById(R.id.panel_localidad).setVisibility(View.GONE);
        }

    }

    public void adaptarCaracteristicas_vivienda(){

        boolean techo = config.getDatos_caracteristicas_vivienda().isTecho();
        boolean paredes = config.getDatos_caracteristicas_vivienda().isParedes();
        boolean piso = config.getDatos_caracteristicas_vivienda().isPisos();
        boolean servicios = config.getDatos_caracteristicas_vivienda().isServicios();
        boolean banio = config.getDatos_caracteristicas_vivienda().isBanio();
        boolean cocina = config.getDatos_caracteristicas_vivienda().isCocina();


        if(!techo){
            act.findViewById(R.id.panel_techo).setVisibility(View.GONE);
        }
        if(!paredes){
            act.findViewById(R.id.panel_paredes).setVisibility(View.GONE);
        }
        if(!piso){
            act.findViewById(R.id.panel_piso).setVisibility(View.GONE);
        }
        if(!servicios){
            act.findViewById(R.id.panel_agua).setVisibility(View.GONE);
            act.findViewById(R.id.panel_fuente_agua).setVisibility(View.GONE);
            act.findViewById(R.id.panel_desague).setVisibility(View.GONE);
            act.findViewById(R.id.panel_electricidad).setVisibility(View.GONE);
            act.findViewById(R.id.panel_combustible_cocina).setVisibility(View.GONE);
        }
        if(!banio){
            act.findViewById(R.id.panel_baño).setVisibility(View.GONE);
            act.findViewById(R.id.panel_baño_tiene).setVisibility(View.GONE);

        }
        if(!cocina){
            act.findViewById(R.id.panel_cocina).setVisibility(View.GONE);
        }

    }

    public void adaptarSituacion_habitacional() {

        boolean tipo_vivienda = config.getDatos_situacion_habitacional().isTipo_vivienda();
        boolean tenencia_terreno_vivienda = config.getDatos_situacion_habitacional().isTenencia_vivienda_terreno();
        boolean tiempo_ocupacion = config.getDatos_situacion_habitacional().isTiempo_ocupacion();
        boolean cantidad_de_hogares = config.getDatos_situacion_habitacional().isCantidad_hogares_vivienda();
        boolean cantidad_de_cuartos_UE = config.getDatos_situacion_habitacional().isCantidad_cuartos_ue();

        if(!tipo_vivienda){
            act.findViewById(R.id.panel_tipo_vivienda).setVisibility(View.GONE);
        }

        if(!tenencia_terreno_vivienda){
            act.findViewById(R.id.panel_tenencia_vivienda_terreno).setVisibility(View.GONE);
        }
        if(!tiempo_ocupacion){
            act.findViewById(R.id.panel_tiempo_ocupacion).setVisibility(View.GONE);
        }
        if(!cantidad_de_hogares){
            act.findViewById(R.id.panel_cantidad_hogares_vivienda).setVisibility(View.GONE);
        }
        if(!cantidad_de_cuartos_UE){
            act.findViewById(R.id.panel_cantidad_cuartos_ue).setVisibility(View.GONE);
        }
    }

    public void adaptarInfraestructura_barrial(){
        boolean infraestructura_calles = config.getDatos_infraestructura_barrial().isCalles();
        boolean iluminacion = config.getDatos_infraestructura_barrial().isIluminacion();
        boolean inundacion = config.getDatos_infraestructura_barrial().isInundacion();
        boolean recoleccion = config.getDatos_infraestructura_barrial().isRecoleccion_basura();
        boolean distancias = config.getDatos_infraestructura_barrial().isDistancias();

        if(!infraestructura_calles){
            act.findViewById(R.id.panel_infraestructura_calles).setVisibility(View.GONE);
        }
        if(!iluminacion){
            act.findViewById(R.id.panel_iluminacion).setVisibility(View.GONE);
        }
        if(!inundacion){
            act.findViewById(R.id.panel_inundacion).setVisibility(View.GONE);
        }
        if(!recoleccion){
            act.findViewById(R.id.panel_recoleccion).setVisibility(View.GONE);
        }
        if(!distancias){
            act.findViewById(R.id.panel_distancia_educacion).setVisibility(View.GONE);
            act.findViewById(R.id.panel_distancia_salud).setVisibility(View.GONE);
            act.findViewById(R.id.panel_distancia_transporte).setVisibility(View.GONE);
        }

    }

    public void adaptarNuevoFamiliar(){
        /*Datos generales*/
        boolean nombres = config.getDatos_grupo_familiar().isNombres();
        boolean apellidos = config.getDatos_grupo_familiar().isApellidos();
        boolean cuil = config.getDatos_grupo_familiar().isCuil();
        boolean sexo = config.getDatos_grupo_familiar().isSexo();
        boolean fechaNacimiento = config.getDatos_grupo_familiar().isFecha_nac();
        boolean estadoCivil = config.getDatos_grupo_familiar().isEstado_civil();
        boolean nacion = config.getDatos_grupo_familiar().isNacion();
        boolean vinculo = config.getDatos_grupo_familiar().isVinculo();
        boolean educacion = config.getDatos_grupo_familiar().isEducacion();
        boolean nucleo = config.getDatos_grupo_familiar().isNucleo();

        /*Datos de la ocupacion*/
        boolean condicionActividad = config.getDatos_grupo_familiar().getDatos_ocupacion().isCondicion_de_actividad();
        boolean puestoTrabajo = config.getDatos_grupo_familiar().getDatos_ocupacion().isPuesto_de_trabajo();
        boolean aporteJubilatorio = config.getDatos_grupo_familiar().getDatos_ocupacion().isAporte_jubilatorio();
        boolean duracion = config.getDatos_grupo_familiar().getDatos_ocupacion().isDuracion();
        boolean tipoActividad = config.getDatos_grupo_familiar().getDatos_ocupacion().isTipo_de_actividad();
        boolean calificacion = config.getDatos_grupo_familiar().getDatos_ocupacion().isCalificacion();

        /*Datos de los ingresos*/
        boolean ingresosLaborales = config.getDatos_grupo_familiar().getDatos_ingresos().isIngresos_laborales();
        boolean programasSocialesSti = config.getDatos_grupo_familiar().getDatos_ingresos().isPrograma_sociales_sti();
        boolean ingresosNoLaborales = config.getDatos_grupo_familiar().getDatos_ingresos().isIngresos_no_laborales();

        /*Datos de la salud*/
        boolean cobertura = config.getDatos_grupo_familiar().getDatos_salud().isCobertura();
        boolean embarazo = config.getDatos_grupo_familiar().getDatos_salud().isEmbarazo();
        boolean discapacidad = config.getDatos_grupo_familiar().getDatos_salud().isDiscapacidad();
        boolean enfermedadCronica = config.getDatos_grupo_familiar().getDatos_salud().isEnfermedad_cronica();
        boolean independenciaFuncional = config.getDatos_grupo_familiar().getDatos_salud().isIndependencia_funcional();

        if(!config.getDatos_grupo_familiar().requiredGeneral()){
            act.findViewById(R.id.panel_datos_generales_familiar).setVisibility(View.GONE);
        }
        else{
            if(!nombres){
                act.findViewById(R.id.panel_nombres_familiar).setVisibility(View.GONE);
            }
            if(!apellidos){
                act.findViewById(R.id.panel_apellidos_familiar).setVisibility(View.GONE);
            }
            if(!cuil){
                act.findViewById(R.id.panel_cuil_familiar).setVisibility(View.GONE);
                act.findViewById(R.id.panel_tipo_doc_familiar).setVisibility(View.GONE);
            }
            if(!sexo){
                act.findViewById(R.id.panel_sexo_familiar).setVisibility(View.GONE);
            }
            if(!fechaNacimiento){
                act.findViewById(R.id.panel_fecha_nac_familiar).setVisibility(View.GONE);
            }
            if(!estadoCivil){
                act.findViewById(R.id.panel_estado_civil_familiar).setVisibility(View.GONE);
            }
            if(!nacion){
                act.findViewById(R.id.panel_nacion_familiar).setVisibility(View.GONE);
            }
            if(!vinculo){
                act.findViewById(R.id.panel_vinculo_familiar).setVisibility(View.GONE);
            }
            if(!educacion){
                act.findViewById(R.id.panel_educacion_familiar).setVisibility(View.GONE);
            }
            if(!nucleo){
                act.findViewById(R.id.panel_nucleo_familiar).setVisibility(View.GONE);
            }
        }

        if(!config.getDatos_grupo_familiar().getDatos_ocupacion().required()){
            act.findViewById(R.id.panel_ocupacion_familiar).setVisibility(View.GONE);
        }
        else{
            if(!condicionActividad){
                act.findViewById(R.id.panel_condicion_actividad).setVisibility(View.GONE);
            }
            if(!puestoTrabajo){
                act.findViewById(R.id.panel_puesto_trabajo).setVisibility(View.GONE);
            }
            if(!aporteJubilatorio){
                act.findViewById(R.id.panel_aporte_jubilatorio).setVisibility(View.GONE);
            }
            if(!duracion){
                act.findViewById(R.id.panel_duracion_empleo).setVisibility(View.GONE);
            }
            if(!tipoActividad){
                act.findViewById(R.id.panel_tipo_actividad).setVisibility(View.GONE);
            }
            if(!calificacion){
                act.findViewById(R.id.panel_calificacion).setVisibility(View.GONE);
            }
        }

        if(!config.getDatos_grupo_familiar().getDatos_ingresos().required()){
            act.findViewById(R.id.panel_ingresos_laborales_familiar).setVisibility(View.GONE);
        }
        else{
            if(!ingresosLaborales){
                act.findViewById(R.id.panel_ingresos_laborales).setVisibility(View.GONE);
            }
            if(!ingresosNoLaborales){
                act.findViewById(R.id.panel_ingresos_no_laborales).setVisibility(View.GONE);
            }
            if(!programasSocialesSti){
                act.findViewById(R.id.panel_programas_sociales_sti).setVisibility(View.GONE);
            }
        }

        if(!config.getDatos_grupo_familiar().getDatos_salud().required()){
            act.findViewById(R.id.panel_salud_familiar).setVisibility(View.GONE);
        }
        else{
            if(!cobertura){
                act.findViewById(R.id.panel_cobertura).setVisibility(View.GONE);
            }
            if(!embarazo){
                act.findViewById(R.id.panel_embarazo).setVisibility(View.GONE);
            }
            if(!discapacidad){
                act.findViewById(R.id.panel_discapacidad).setVisibility(View.GONE);
            }
            if(!enfermedadCronica){
                act.findViewById(R.id.panel_enfermedad_cronica).setVisibility(View.GONE);
            }
            if(!independenciaFuncional){
                act.findViewById(R.id.panel_independecia_funcional).setVisibility(View.GONE);
            }
        }



    }

}
