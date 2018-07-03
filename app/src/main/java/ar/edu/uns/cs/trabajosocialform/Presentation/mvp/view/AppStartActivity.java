package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import ar.edu.uns.cs.trabajosocialform.Data.Database.StorageAccess;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.RxBus;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.NuevaEntradaObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.VerEntradasObserver;
import ar.edu.uns.cs.trabajosocialform.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppStartActivity extends ActivityView{

    @BindView(R.id.toolbar) Toolbar toolbar;

    public AppStartActivity(AppCompatActivity activity){
        super(activity);
        ButterKnife.bind(this, activity);
        getActivity().setSupportActionBar(toolbar);
    }

    /**
     * Inits a new Activity that gets data to create a new entry of the form
     * @param view
     */
    @OnClick(R.id.nueva_entrada)
    public void nuevaEntrada(View view){
        RxBus.post(new NuevaEntradaObserver.NuevaEntrada());
    }

    /**
     * Create a new Activity to show the created forms
     * @param view
     */
    @OnClick(R.id.ver_entradas)
    public void verEntradas(View view) {
        RxBus.post(new VerEntradasObserver.VerEntradas());
    }

}
