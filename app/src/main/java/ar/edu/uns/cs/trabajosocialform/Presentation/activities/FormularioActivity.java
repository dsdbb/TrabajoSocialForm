package ar.edu.uns.cs.trabajosocialform.Presentation.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter.FormularioPresenter;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.FormularioView;
import ar.edu.uns.cs.trabajosocialform.R;

public class FormularioActivity extends AppCompatActivity {

    private FormularioPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        boolean update = getIntent().getBooleanExtra(MainActivity.KEY_UPDATE,false);
        Formulario updateForm = null;
        if(update){
             updateForm =(Formulario)getIntent().getSerializableExtra(MainActivity.KEY_UPDATE_FORM);
        }
        presenter = new FormularioPresenter(new FormularioView(this),updateForm);
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
