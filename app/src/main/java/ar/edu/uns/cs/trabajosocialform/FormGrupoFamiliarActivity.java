package ar.edu.uns.cs.trabajosocialform;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import ar.edu.uns.cs.trabajosocialform.DataModel.Familiar;
import ar.edu.uns.cs.trabajosocialform.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Database.DatabaseAcces;
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

        inicializarGui();

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
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*Reemplazo el formulario por el que devolvio la actividad (que tendrá el nuevo familiar agregado)*/
        if(data!=null){
            form = (Formulario)data.getSerializableExtra("RETURN_FORM");
            boolean familiarActualizado = data.getBooleanExtra("UPDATED_FAMILIAR",false);

            if(familiarActualizado){
                Familiar oldFamiliar = (Familiar)data.getSerializableExtra("OLD_FAMILIAR");
                //form.getFamiliares().remove(oldFamiliar);
                for(int i=0;i<form.getFamiliares().size();i++){
                    if(form.getFamiliares().get(i).equals(oldFamiliar)){
                        form.getFamiliares().remove(i);
                    }
                }

            }

            refreshContenedorFamiliares();
        }


    }

    public void inicializarGui(){
        Utils utils = new Utils(this);

        utils.addContentToTemplate(R.layout.form_grupo_familiar);
        utils.addNextButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siguiente();
            }
        });
    }
    public void siguiente(){
        Intent intent = new Intent(this,FormSituacionHabitacionalActivity.class);
        intent.putExtra("CONFIG",bundle);
        intent.putExtra("FORM",form);
        intent.putExtra("UPDATE",update);
        if(update)
            intent.putExtra("UPDATE_FORM",updateForm);
        startActivity(intent);
    }

    private void refreshContenedorFamiliares(){
        List<Familiar> familiares = form.getFamiliares();
        Log.i("FAMILIARES SIZE: ",familiares.size()+"");
        ListView listview = (ListView)findViewById(R.id.list_view_familiares);
        listview.setAdapter(new FormGrupoFamiliarActivity.customAdapter(this,familiares));
        //Utils.setListViewHeightBasedOnChildren(listview);

        /*Para el menu al mantener presionado*/
        registerForContextMenu(listview);

    }

    private void rellenarCampos(){
        ListView listview = (ListView)findViewById(R.id.list_view_familiares);
        List<Familiar> familiares = updateForm.getFamiliares();
        listview.setAdapter(new FormGrupoFamiliarActivity.customAdapter(this,familiares));
        //Utils.setListViewHeightBasedOnChildren(listview);

        /*Copio todos los familiares al nuevo form*/
        for(int i=0; i<familiares.size();i++){
            form.getFamiliares().add(familiares.get(i));
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

            }
        });

        /*Para el menu al mantener presionado*/
        registerForContextMenu(listview);
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

    /**
     * MENU
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.list_view_familiares) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_list, menu);
        }

    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final ContextMenu.ContextMenuInfo menuInfo = item.getMenuInfo();
        switch(item.getItemId()) {
            case R.id.edit:
                Intent intent = new Intent(FormGrupoFamiliarActivity.this,NuevoFamiliarActivity.class);
                intent.putExtra("CONFIG",bundle);
                intent.putExtra("FORM",form);
                intent.putExtra("UPDATE",update);
                int pos = ((AdapterView.AdapterContextMenuInfo)menuInfo).position;
                Familiar familiar = form.getFamiliares().get(pos);
                intent.putExtra("UPDATE_FAMILIAR", familiar);
                startActivityForResult(intent,1);
                return true;
            case R.id.delete:
                Utils utils = new Utils(this);

                Runnable run = new Runnable() {
                    @Override
                    public void run() {
                        int position = ((AdapterView.AdapterContextMenuInfo)menuInfo).position;
                        form.getFamiliares().remove(position);
                        refreshContenedorFamiliares();

                    }
                };

                utils.showAlertDialog(R.string.titulo_confirmacion_eliminar, R.string.texto_confirmacion_eliminar,run);

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
