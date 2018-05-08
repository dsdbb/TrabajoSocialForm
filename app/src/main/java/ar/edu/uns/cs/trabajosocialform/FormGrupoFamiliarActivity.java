package ar.edu.uns.cs.trabajosocialform;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import ar.edu.uns.cs.trabajosocialform.DataModel.Familiar;
import ar.edu.uns.cs.trabajosocialform.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;
import ar.edu.uns.cs.trabajosocialform.configuracion.Configuracion;

public class FormGrupoFamiliarActivity extends AppCompatActivity {

    private Bundle bundle;
    private Formulario form;
    private boolean update;
    private Formulario updateForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_grupo_familiar);

        Intent intent = getIntent();
        bundle = intent.getBundleExtra("CONFIG");
        form = (Formulario)intent.getSerializableExtra("FORM");
        Configuracion config = (Configuracion)bundle.getSerializable("CONFIG");

        /*Chequeo si es un update y en ese caso relleno los campos*/
        update = getIntent().getBooleanExtra("UPDATE",false);
        if(update){
            updateForm = (Formulario)getIntent().getSerializableExtra("UPDATE_FORM");
            rellenarCampos();
        }


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

        form = (Formulario)data.getSerializableExtra("RETURN_FORM");
        refreshContenedorFamiliares();

    }

    public void siguiente(View view){
        Intent intent = new Intent(this,FormSituacionHabitacionalActivity.class);
        intent.putExtra("CONFIG",bundle);
        intent.putExtra("FORM",form);
        intent.putExtra("UPDATE",update);
        if(update)
            intent.putExtra("UPDATE_FORM",updateForm);
        startActivity(intent);
        finish();
    }

    private void refreshContenedorFamiliares(){
        List<Familiar> familiares = form.getFamiliares();
        Log.i("FAMILIARES SIZE: ",familiares.size()+"");
        ListView listview = (ListView)findViewById(R.id.list_view_familiares);
        listview.setAdapter(new FormGrupoFamiliarActivity.customAdapter(this,familiares));
        Utils.setListViewHeightBasedOnChildren(listview);


    }

    private void rellenarCampos(){
        ListView listview = (ListView)findViewById(R.id.list_view_familiares);
        List<Familiar> familiares = updateForm.getFamiliares();
        listview.setAdapter(new FormGrupoFamiliarActivity.customAdapter(this,familiares));
        Utils.setListViewHeightBasedOnChildren(listview);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = new Intent(FormGrupoFamiliarActivity.this,NuevoFamiliarActivity.class);
                intent.putExtra("UPDATE",update);
                Familiar familiar = updateForm.getFamiliares().get(arg2);
                intent.putExtra("UPDATE_FAMILIAR", familiar);
                startActivity(intent);
            }
        });
    }


    public class customAdapter extends BaseAdapter {

        private Context context;
        private List<Familiar> familiares;
        private int position;

        public customAdapter(Context context, List<Familiar> familiares){
            this.context = context;
            this.familiares = familiares;
        }

        public View getView(int position, View convertView, ViewGroup container) {
            this.position = position;

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_familiar, container, false);
            }
            Familiar familiar = familiares.get(position);
            ((TextView) convertView.findViewById(R.id.label)).setText(familiar.getNombres() + " " + familiar.getApellidos());
            return convertView;

        }

        public int getPosition(){
            return position;
        }
        public final int getCount() {
            return familiares.size();
        }

        public Object getItem(int position) {
            return familiares.get(position);
        }

        public long getItemId(int position) {
            return familiares.get(position).getId();
        }

    }
}
