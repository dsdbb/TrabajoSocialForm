package ar.edu.uns.cs.trabajosocialform;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ar.edu.uns.cs.trabajosocialform.DataModel.Familiar;
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

    public void nuevoFamiliar(View view){
        Intent intent = new Intent(this,NuevoFamiliarActivity.class);
        intent.putExtra("CONFIG",bundle);
        intent.putExtra("FORM",form);
        startActivityForResult(intent,1);
        /*Reemplazo el formulario por el que devolvio la actividad (que tendrá el nuevo familiar agregado)*/

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("ENTRE ","ACA");
        Log.i("requestCode: ", requestCode+"");
        Log.i("resultCode: ",resultCode+"");
        Log.i("DATA: ",data.toString());
            Log.i("NUEVO FORM","ss");
            form = (Formulario)data.getSerializableExtra("RETURN_FORM");
             refreshContenedorFamiliares();

    }

    public void siguiente(View view){
        Intent intent = new Intent(this,FormSituacionHabitacionalActivity.class);
        intent.putExtra("CONFIG",bundle);
        intent.putExtra("FORM",form);
        startActivity(intent);
        finish();
    }

    private void refreshContenedorFamiliares(){
        List<Familiar> familiares = form.getFamiliares();
        Log.i("FAMILIARES SIZE: ",familiares.size()+"");
        for(int i=0; i<familiares.size(); i++){
            LinearLayout contenedor = (LinearLayout)findViewById(R.id.contenedorFamiliares);
            contenedor.removeAllViews();
            LayoutInflater inflater = LayoutInflater.from(this);

            View inflatedView = inflater.inflate(R.layout.view_familiar, contenedor, false);
            ((TextView)inflatedView.findViewById(R.id.textView)).setText(familiares.get(i).getNombres());
            contenedor.addView(inflatedView);
        }
    }
}
