package ar.edu.uns.cs.trabajosocialform.Presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.Database.DatabaseAcces;
import ar.edu.uns.cs.trabajosocialform.Presentation.adapters.EntradasRecyclerViewAdapter;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.RxBus;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.ContextItemSelectedObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.ContextMenuObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.OptionsItemSelectedObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.OptionsMenuObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter.EntradasPresenter;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.EntradasView;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.FormularioView;
import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;

public class EntradasActivity extends AppCompatActivity {

    private EntradasPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entradas);
        presenter = new EntradasPresenter(new EntradasView(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.register();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unregister();
    }

    /*@Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        RxBus.post(new ContextMenuObserver.ContextMenu(v.getId(),menu));
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        RxBus.post(new OptionsMenuObserver.OptionsMenu(menu));
        return true;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        RxBus.post(new ContextItemSelectedObserver.ContextItemSelected(item));
        return true;
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
        RxBus.post(new OptionsItemSelectedObserver.OptionsItemSelected(item));
        return true;
    }
}
