package ar.edu.uns.cs.trabajosocialform.Presentation.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter.DetallesFormPresenter;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.DetallesFormView;
import ar.edu.uns.cs.trabajosocialform.R;

public class DetallesFormActivity extends AppCompatActivity {

    private DetallesFormPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_form);
        Formulario form = (Formulario)getIntent().getSerializableExtra("FORM");
        presenter = new DetallesFormPresenter(new DetallesFormView(this),form);
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
