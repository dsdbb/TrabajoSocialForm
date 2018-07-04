package ar.edu.uns.cs.trabajosocialform.Presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter.FormApoderadoPresenter;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.FormApoderadoView;
import ar.edu.uns.cs.trabajosocialform.R;

public class FormApoderadoAcitivity extends AppCompatActivity {

    private FormApoderadoPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_apoderado);

        Intent intent = getIntent();
        Formulario form = (Formulario) intent.getSerializableExtra("FORM");
        Configuracion config = (Configuracion) intent.getSerializableExtra("CONFIG");
        Formulario updateForm = null;
        boolean update = getIntent().getBooleanExtra(MainActivity.KEY_UPDATE, false);
        if(update){
            updateForm = (Formulario) getIntent().getSerializableExtra(MainActivity.KEY_UPDATE_FORM);
        }
        presenter = new FormApoderadoPresenter(new FormApoderadoView(this),form,config,updateForm);

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
