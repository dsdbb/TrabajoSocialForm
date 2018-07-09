package ar.edu.uns.cs.trabajosocialform.Presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter.FormSituacionHabitacionalPresenter;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.FormSituacionHabitacionalView;
import ar.edu.uns.cs.trabajosocialform.R;

public class FormSituacionHabitacionalActivity extends AppCompatActivity {

    private FormSituacionHabitacionalPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_situacion_habitacional);
        Intent intent = getIntent();
        Formulario form = (Formulario)intent.getSerializableExtra(MainActivity.KEY_ACTUAL_FORM);
        Configuracion config = (Configuracion)intent.getSerializableExtra(MainActivity.KEY_CONFIGURATION_FILE);
        Formulario updateForm = null;
        boolean update = getIntent().getBooleanExtra("UPDATE",false);
        if(update) {
            updateForm = (Formulario) getIntent().getSerializableExtra("UPDATE_FORM");
        }
        presenter = new FormSituacionHabitacionalPresenter(new FormSituacionHabitacionalView(this),form,config,updateForm);
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
