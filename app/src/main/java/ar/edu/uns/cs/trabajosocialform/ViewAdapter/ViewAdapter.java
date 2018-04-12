package ar.edu.uns.cs.trabajosocialform.ViewAdapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.java_classes.configuracion.Configuracion;

/**
 * Created by Kevin (User) on 10/4/2018.
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
            act.findViewById(R.id.panel_motivos_de_poder_apoderado).setVisibility(View.GONE);
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
            act.findViewById(R.id.panel_entre_calles).setVisibility(View.GONE);
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

}
