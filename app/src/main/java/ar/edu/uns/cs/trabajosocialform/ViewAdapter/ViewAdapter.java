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
}
