package ar.edu.uns.cs.trabajosocialform;

import android.arch.persistence.room.Database;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import ar.edu.uns.cs.trabajosocialform.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.DataModel.Solicitante;
import ar.edu.uns.cs.trabajosocialform.Database.DatabaseAcces;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;

public class EntradasActivity extends AppCompatActivity {


    private List<Formulario> forms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entradas);
        final Context context = this;


        forms = (new DatabaseAcces()).getFormularios(this);

        final ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(new customAdapter(this,forms));

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = new Intent(context,DetallesFormActivity.class);
                Formulario form = forms.get(arg2);
                intent.putExtra("FORM",form);
                startActivity(intent);
            }
        });

        /*Para el menu al mantener presionado*/
        registerForContextMenu(listview);


    }

    /**
     * MENU
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.list_view) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_list, menu);
        }

    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case R.id.edit:

                return true;
            case R.id.delete:
                Utils utils = new Utils(this);

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        ContextMenu.ContextMenuInfo menuInfo = item.getMenuInfo();
                        int pos = ((AdapterView.AdapterContextMenuInfo)menuInfo).position;
                        int formId = forms.get(pos).getId();
                        DatabaseAcces db = new DatabaseAcces();
                        db.deleteJoins(EntradasActivity.this,formId);
                        db.deleteFormulario(EntradasActivity.this,formId);
                        int solId = forms.get(pos).getSolicitanteId();
                        (new DatabaseAcces()).deleteSolicitante(EntradasActivity.this,solId);
                    }
                };

                utils.showAlertDialog(R.string.titulo_confirmacion_eliminar, R.string.texto_confirmacion_eliminar,runnable);

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public class customAdapter extends BaseAdapter {

        private Context context;
        private List<Formulario> forms;
        private int position;

        public customAdapter(Context context, List<Formulario> forms){
            this.context = context;
            this.forms = forms;
        }

        public View getView(int position, View convertView, ViewGroup container) {
            this.position = position;

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
            }

            int solicitanteId = forms.get(position).getSolicitanteId();
            Solicitante solicitante = (new DatabaseAcces()).getSolicitante(EntradasActivity.this,solicitanteId);
            ((TextView) convertView.findViewById(R.id.label)).setText(solicitante.getNombres());
            return convertView;



        }

        public int getPosition(){
            return position;
        }
        public final int getCount() {
            return forms.size();
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

    }
}


