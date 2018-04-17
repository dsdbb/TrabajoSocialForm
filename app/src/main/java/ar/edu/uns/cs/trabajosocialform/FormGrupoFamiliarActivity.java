package ar.edu.uns.cs.trabajosocialform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ar.edu.uns.cs.trabajosocialform.java_classes.configuracion.Configuracion;

public class FormGrupoFamiliarActivity extends AppCompatActivity {

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_grupo_familiar);

        Intent intent = getIntent();
        bundle = intent.getBundleExtra("CONFIG");
        this.bundle = bundle;
        Configuracion config = (Configuracion)bundle.getSerializable("CONFIG");

        //ViewAdapter va = new ViewAdapter(config,this);
        //va.adaptarGrupoFamiliar();
    }

    public void nuevoFamiliar(View viewnuev){
        Intent intent = new Intent(this,NuevoFamiliarActivity.class);
        startActivity(intent);
    }

    public void siguiente(View view){
        Intent intent = new Intent(this,FormSituacionHabitacionalActivity.class);
        intent.putExtra("CONFIG",bundle);
        startActivity(intent);
    }
}
