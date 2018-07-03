package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Familiar;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormGrupoFamiliarView extends GeneralActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_grupo_familiar);

        Intent intent = getIntent();
        form = (Formulario) intent.getSerializableExtra("FORM");
        config = (Configuracion) intent.getSerializableExtra("CONFIG");

        if (!config.getDatos_grupo_familiar().required()) {
            continuar(null);
        } else {
            inicializarGui();
            /*Bind Activity to use ButterKnife facilities*/
            ButterKnife.bind(this);

            /*Chequeo si es un update y en ese caso relleno los campos*/
            update = getIntent().getBooleanExtra("UPDATE", false);
            if (update) {
                updateForm = (Formulario) getIntent().getSerializableExtra("UPDATE_FORM");
                rellenarCampos();
            }
        }

    }

    @OnClick(R.id.nuevo_familiar_btn)
    public void nuevoFamiliar(View view) {
        Intent intent = new Intent(this, NuevoFamiliarView.class);
        intent.putExtra("CONFIG", config);
        intent.putExtra("FORM", form);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*Reemplazo el formulario por el que devolvio la actividad (que tendrá el nuevo familiar agregado)*/
        if (data != null) {
            form = (Formulario) data.getSerializableExtra("RETURN_FORM");
            boolean familiarActualizado = data.getBooleanExtra("UPDATED_FAMILIAR", false);

            if (familiarActualizado) {
                Familiar oldFamiliar = (Familiar) data.getSerializableExtra("OLD_FAMILIAR");
                //form.getFamiliares().remove(oldFamiliar);
                for (int i = 0; i < form.getFamiliares().size(); i++) {
                    if (form.getFamiliares().get(i).equals(oldFamiliar)) {
                        form.getFamiliares().remove(i);
                    }
                }

            }

            refreshContenedorFamiliares();
        }


    }

    @Override
    protected void inicializarGui() {
        Utils utils = new Utils(this);

        /*Titulo toolbar*/
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.titulo_grupo_familiar);

        utils.addContentToTemplate(R.layout.form_grupo_familiar);
        /*utils.addNextButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continuar();
            }
        });*/
    }

    @Override
    @OnClick(R.id.siguiente_button)
    public void continuar(View view) {
        Intent intent = new Intent(this, FormSituacionHabitacionalView.class);
        intent.putExtra("CONFIG", config);
        intent.putExtra("FORM", form);
        intent.putExtra("UPDATE", update);
        if (update)
            intent.putExtra("UPDATE_FORM", updateForm);
        startActivity(intent);
        if (!config.getDatos_grupo_familiar().required()) {
            finish();
        }
    }

    private void refreshContenedorFamiliares() {
        List<Familiar> familiares = form.getFamiliares();
        Log.i("FAMILIARES SIZE: ", familiares.size() + "");
        ListView listview = (ListView) findViewById(R.id.list_view_familiares);
        listview.setAdapter(new FormGrupoFamiliarView.customAdapter(this, familiares));
        //Utils.setListViewHeightBasedOnChildren(listview);

        /*Para el menu al mantener presionado*/
        registerForContextMenu(listview);

    }

    @Override
    protected void rellenarCampos() {
        ListView listview = (ListView) findViewById(R.id.list_view_familiares);
        List<Familiar> familiares = updateForm.getFamiliares();
        listview.setAdapter(new FormGrupoFamiliarView.customAdapter(this, familiares));
        //Utils.setListViewHeightBasedOnChildren(listview);

        /*Copio todos los familiares al nuevo form*/
        for (int i = 0; i < familiares.size(); i++) {
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

    @Override
    protected Object tomarDatos(){return null;}

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
                Intent intent = new Intent(FormGrupoFamiliarView.this,NuevoFamiliarView.class);
                intent.putExtra("CONFIG",config);
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

    @Override
    protected boolean validate(Object obj){
        return true;
    }
}
