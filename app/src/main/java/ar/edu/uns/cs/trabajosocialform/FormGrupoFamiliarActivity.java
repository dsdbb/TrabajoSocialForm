package ar.edu.uns.cs.trabajosocialform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ar.edu.uns.cs.trabajosocialform.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.configuracion.Configuracion;

public class FormGrupoFamiliarActivity extends AppCompatActivity {

    private Bundle bundle;
    private Formulario form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_grupo_familiar);

        Intent intent = getIntent();
        bundle = intent.getBundleExtra("CONFIG");
        form = (Formulario)intent.getSerializableExtra("FORM");
        Configuracion config = (Configuracion)bundle.getSerializable("CONFIG");

        //ViewAdapter va = new ViewAdapter(config,this);
        //va.adaptarGrupoFamiliar();
    }

    public void nuevoFamiliar(View viewnuev){
        Intent intent = new Intent(this,NuevoFamiliarActivity.class);
        intent.putExtra("CONFIG",bundle);
        intent.putExtra("FORM",form);
        startActivity(intent);
        /*Reemplazo el formulario por el que devolvio la actividad (que tendrá el nuevo familiar agregado)*/
        form = (Formulario)intent.getSerializableExtra("FORM");
    }

    public void siguiente(View view){
        Intent intent = new Intent(this,FormSituacionHabitacionalActivity.class);
        intent.putExtra("CONFIG",bundle);
        intent.putExtra("FORM",form);
        startActivity(intent);
        finish();
    }
}
