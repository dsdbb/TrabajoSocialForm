package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Solicitante;
import ar.edu.uns.cs.trabajosocialform.Data.Database.DatabaseAcces;
import ar.edu.uns.cs.trabajosocialform.Presentation.adapters.EntradasRecyclerViewAdapter;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter.EntradasPresenter;
import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.Data.ServerConnection.ServerAccess;
import ar.edu.uns.cs.trabajosocialform.Data.Transactions.Transaction;
import ar.edu.uns.cs.trabajosocialform.Data.Transactions.TransactionOptions;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.observers.DisposableObserver;

public class EntradasView extends ActivityView {


    private EntradasPresenter presenter;
    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    public EntradasView(AppCompatActivity activity){
        super(activity);
        ButterKnife.bind(this, activity);
        getActivity().setSupportActionBar(toolbar);
        getActivity().registerForContextMenu(recyclerView);
    }

    public void refreshList(List<Solicitante> solicitantes){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        // specify an adapter (see also next example)
        EntradasRecyclerViewAdapter adapter = new EntradasRecyclerViewAdapter(solicitantes);
        recyclerView.setAdapter(adapter);
        //getActivity().registerForContextMenu(recyclerView);

    }

    public void setOptionsMenu(Menu menu){
        getActivity().getMenuInflater().inflate(R.menu.menu_toolbar, menu);
    }

    public void setContextMenu(Menu menu){
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_list, menu);
    }


    /**
     * Show an alert dialog and makes an action according to the response
     * @param titulo Title id of the alert dialog
     * @param mensaje Message id to show in the dialog
     * @param runnable Action to perform if the confirmation button is pressed
     */
    public void showAlertDialog(int titulo, int mensaje, String nombre,final Runnable runnable){
        final List<Boolean> result = new ArrayList<Boolean>();
        String mensajeString = getActivity().getResources().getString(mensaje);
        new AlertDialog.Builder(getActivity())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(titulo)
                .setMessage(mensajeString +" "+ nombre +"?")
                .setPositiveButton(R.string.boton_si, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        runnable.run();
                    }

                })
                .setNegativeButton(R.string.boton_no, null)
                .show();

    }

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entradas);

        presenter = new EntradasPresenter(this);

        final Context context = this;


        forms = (new DatabaseAcces()).getFormularios(this);
        Log.i("FORMS", (new Gson()).toJson(forms));


        final ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(new customAdapter(this,forms));

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = new Intent(context,DetallesFormView.class);
                Formulario form = forms.get(arg2);
                intent.putExtra("FORM",form);
                startActivity(intent);
            }
        });

        //Para el menu al mantener presionado
        registerForContextMenu(listview);


    }*/




}


