package ar.edu.uns.cs.trabajosocialform.Presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter.FormInfraestructuraBarrialPresenter;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.FormInfraestructuraBarrialView;
import ar.edu.uns.cs.trabajosocialform.R;

public class FormInfraestructuraBarrialActivity extends AppCompatActivity {

    private FormInfraestructuraBarrialPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_infraestructura_barrial);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Intent intent = getIntent();
        Formulario form = (Formulario)intent.getSerializableExtra(MainActivity.KEY_ACTUAL_FORM);
        Configuracion config = (Configuracion)intent.getSerializableExtra(MainActivity.KEY_CONFIGURATION_FILE);
        Formulario updateForm = null;
        boolean update = getIntent().getBooleanExtra(MainActivity.KEY_UPDATE,false);
        if(update){
            updateForm = (Formulario)getIntent().getSerializableExtra(MainActivity.KEY_UPDATE_FORM);
        }
        presenter = new FormInfraestructuraBarrialPresenter(new FormInfraestructuraBarrialView(this),form,config,updateForm);
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
