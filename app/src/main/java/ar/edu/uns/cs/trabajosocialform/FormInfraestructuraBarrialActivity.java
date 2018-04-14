package ar.edu.uns.cs.trabajosocialform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ar.edu.uns.cs.trabajosocialform.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.java_classes.configuracion.Configuracion;

public class FormInfraestructuraBarrialActivity extends AppCompatActivity {

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_infraestructura_barrial);

        Intent intent = getIntent();
        bundle = intent.getBundleExtra("CONFIG");
        this.bundle = bundle;
        Configuracion config = (Configuracion)bundle.getSerializable("CONFIG");

        ViewAdapter va = new ViewAdapter(config,this);
        va.adaptarInfraestructura_barrial();
    }

    public void continuar(View view){
        /*Mostar resultado y guardar*/
    }
}
