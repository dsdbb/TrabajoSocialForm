package ar.edu.uns.cs.trabajosocialform.Presentation.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Familiar;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter.DetallesFamiliarPresenter;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.DetallesFamiliarView;
import ar.edu.uns.cs.trabajosocialform.R;

public class DetallesFamiliarActivity extends AppCompatActivity{

    private DetallesFamiliarPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_familiar);
        Familiar familiar = (Familiar)getIntent().getSerializableExtra(MainActivity.KEY_FAMILIAR);
        presenter = new DetallesFamiliarPresenter(new DetallesFamiliarView(this), familiar);

    }
}
