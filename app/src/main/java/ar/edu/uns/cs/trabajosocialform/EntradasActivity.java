package ar.edu.uns.cs.trabajosocialform;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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

public class EntradasActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entradas);
        final Context context = this;


        List<Formulario> forms = (new DatabaseAcces()).getFormularios(this);

        ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(new customAdapter(this,forms));
       // GridView grid = (GridView) findViewById(R.id.gridview);
        //grid.setAdapter(new customAdapter(this,nombres));

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = new Intent(context,DetallesFormActivity.class);
                startActivity(intent);
            }
        });

    }

    public class customAdapter extends BaseAdapter {

        private Context context;
        private List<Formulario> forms;

        public customAdapter(Context context, List<Formulario> forms){
            this.context = context;
            this.forms = forms;
        }

        public View getView(int position, View convertView, ViewGroup container) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
            }

            int solicitanteId = forms.get(position).getSolicitanteId();
            String json = (new Gson()).toJson(forms.get(position));
            Log.i("JSON",json);
            List<String> nombres = (new DatabaseAcces()).getNombresSolicitantes(EntradasActivity.this);
            for(int i=0;i<nombres.size();i++){
                Log.i("NOMBRE "+i,nombres.get(i));
            }
            Solicitante solicitante = (new DatabaseAcces()).getSolicitante(EntradasActivity.this,solicitanteId);
            ((TextView) convertView.findViewById(R.id.label)).setText(solicitante.getNombres());
            return convertView;



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


