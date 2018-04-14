package ar.edu.uns.cs.trabajosocialform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ar.edu.uns.cs.trabajosocialform.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.java_classes.configuracion.Configuracion;

public class FormApoderadoActivity extends AppCompatActivity {

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_apoderado);

        Intent intent = getIntent();
        bundle = intent.getBundleExtra("CONFIG");
        this.bundle = bundle;
        Configuracion config = (Configuracion)bundle.getSerializable("CONFIG");

        ViewAdapter va = new ViewAdapter(config,this);
        va.adaptarApoderado();

    }

    public void continuar(View view){
        Intent intent = new Intent(this,FormDomicilioActivity.class);
        intent.putExtra("CONFIG",bundle);
        startActivity(intent);
    }
}
