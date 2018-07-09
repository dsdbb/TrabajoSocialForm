package ar.edu.uns.cs.trabajosocialform.Presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.widget.AdapterView;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.RxBus;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.ContextItemSelectedObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.OnActivityResultFamiliarObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter.FormGrupoFamiliarPresenter;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.FormGrupoFamiliarView;
import ar.edu.uns.cs.trabajosocialform.R;

public class FormGrupoFamiliarActivity extends AppCompatActivity{

    private FormGrupoFamiliarPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_grupo_familiar);
        Intent intent = getIntent();
        Formulario form = (Formulario) intent.getSerializableExtra(MainActivity.KEY_ACTUAL_FORM);
        Configuracion config = (Configuracion) intent.getSerializableExtra(MainActivity.KEY_CONFIGURATION_FILE);
        Formulario updateForm = null;
        boolean update = intent.getBooleanExtra(MainActivity.KEY_UPDATE, false);
        if (update) {
            updateForm = (Formulario) getIntent().getSerializableExtra(MainActivity.KEY_UPDATE_FORM);
        }
        presenter = new FormGrupoFamiliarPresenter(new FormGrupoFamiliarView(this),form,config,updateForm);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Have to register because onActivityResult is before onResume
        presenter.register();
        RxBus.post(new OnActivityResultFamiliarObserver.OnActivityResultFamiliar(data));
        presenter.unregister();
    }


   @Override
    public boolean onContextItemSelected(final MenuItem item) {
       RxBus.post(new ContextItemSelectedObserver.ContextItemSelected(item));
       return true;
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
}
