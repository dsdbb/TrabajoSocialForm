package ar.edu.uns.cs.trabajosocialform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void configurarNuevoFormulario(View view){
        Intent intent = new Intent(this,configurarNuevoFormulario_Activity.class);
        startActivity(intent);
    }

    public void seleccionarFormulario(View view){
        Intent intent = new Intent(this,FormularioActivity.class);
        startActivity(intent);
    }
}
