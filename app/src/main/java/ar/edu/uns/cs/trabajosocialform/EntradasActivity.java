package ar.edu.uns.cs.trabajosocialform;

import android.arch.persistence.room.Database;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
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
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import ar.edu.uns.cs.trabajosocialform.DataModel.Familiar;
import ar.edu.uns.cs.trabajosocialform.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.DataModel.Solicitante;
import ar.edu.uns.cs.trabajosocialform.Database.DatabaseAcces;
import ar.edu.uns.cs.trabajosocialform.ServerConnection.ServerAccess;
import ar.edu.uns.cs.trabajosocialform.Transactions.Transaction;
import ar.edu.uns.cs.trabajosocialform.Transactions.TransactionDao;
import ar.edu.uns.cs.trabajosocialform.Transactions.TransactionOptions;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;

public class EntradasActivity extends AppCompatActivity {


    private List<Formulario> forms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entradas);
        final Context context = this;


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        forms = (new DatabaseAcces()).getFormularios(this);
        Log.i("FORMS", (new Gson()).toJson(forms));


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
            inflater.inflate(R.menu.menu_list_2, menu);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        ContextMenu.ContextMenuInfo menuInfo = item.getMenuInfo();
        switch(item.getItemId()) {
            case R.id.edit:
                /*Inicio la misma actividad de alta pero con un parámetro que especifique que se refiere a una modificación*/
                Intent intent = new Intent(EntradasActivity.this,FormularioActivity.class);
                intent.putExtra("UPDATE",true);
                int pos = ((AdapterView.AdapterContextMenuInfo)menuInfo).position;
                Formulario form = forms.get(pos);
                DatabaseAcces db = new DatabaseAcces();
                db.getCompleteForm(this,form);
                intent.putExtra("UPDATE_FORM",form);
                startActivityForResult(intent,1);
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
                        db.delete(EntradasActivity.this,forms.get(pos),true);
                        forms.remove(pos);
                        ListView listview = findViewById(R.id.list_view);
                        BaseAdapter ba = (BaseAdapter) listview.getAdapter();
                        ba.notifyDataSetChanged();

                    }
                };

                utils.showAlertDialog(R.string.titulo_confirmacion_eliminar, R.string.texto_confirmacion_eliminar,runnable);

                return true;
            case R.id.upload:
                int position = ((AdapterView.AdapterContextMenuInfo)menuInfo).position;
                Formulario formulario = forms.get(position);
                DatabaseAcces dbAccess = new DatabaseAcces();
                dbAccess.getCompleteForm(this,formulario);
                Log.i("Formulario upload",(new Gson()).toJson(formulario));
                ServerAccess sa = new ServerAccess(this);
                //sa.uploadForm(formulario);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Intent intent = getIntent();
        finish();
        startActivity(intent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
       switch (item.getItemId()){
           case R.id.action_refresh:
               refreshInternetDatabase();
               return true;
           default:
               return true;
       }

    }


    public void refreshInternetDatabase(){
        DatabaseAcces db = new DatabaseAcces();
        List<Transaction> transactions = db.getTransactions(this);
        ServerAccess sa = new ServerAccess(this);
        Log.i("TRANSACTIONS",(new Gson()).toJson(transactions));
        
        if(transactions.size()==0){
            Toast.makeText(this, R.string.sin_actualizaciones, Toast.LENGTH_SHORT).show();
        }
        else {
            for (int i = 0; i < transactions.size(); i++) {
                Transaction transaction = transactions.get(i);
                if (transaction.getTransactionId() == TransactionOptions.INSERT.getValue()) {
                    Formulario form = db.getFormulario(this, transaction.getFormId());
                    sa.uploadForm(form, transaction);
                }
                if (transaction.getTransactionId() == TransactionOptions.DELETE.getValue()) {
                    sa.deleteForm(transaction.getFormId(), transaction);
                }

            }
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
            ((TextView) convertView.findViewById(R.id.label)).setText(solicitante.getNombres()+" "+solicitante.getApellidos());
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


