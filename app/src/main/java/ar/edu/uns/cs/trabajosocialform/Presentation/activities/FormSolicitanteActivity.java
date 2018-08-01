package ar.edu.uns.cs.trabajosocialform.Presentation.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.RxBus;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.OnActivityResultPhotoObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter.FormSolicitantePresenter;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.FormSolicitanteView;
import ar.edu.uns.cs.trabajosocialform.R;

public class FormSolicitanteActivity extends AppCompatActivity {

    public static final int CAMERA_PHOTO = 111;
    private FormSolicitantePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_solicitante);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Intent intent = getIntent();
        Formulario form = (Formulario)intent.getSerializableExtra(MainActivity.KEY_ACTUAL_FORM);
        Configuracion config = (Configuracion)intent.getSerializableExtra(MainActivity.KEY_CONFIGURATION_FILE);
        Formulario updateForm = null;
        boolean update = getIntent().getBooleanExtra(MainActivity.KEY_UPDATE, false);
        if(update){
            updateForm = (Formulario) getIntent().getSerializableExtra(MainActivity.KEY_UPDATE_FORM);
        }
        presenter = new FormSolicitantePresenter(new FormSolicitanteView(this),form,config,updateForm);
    }


    /*  /**
     * Gets the photo Bitmap and show it in the corresponding imageView
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if (requestCode == CAMERA_PHOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            presenter.register();
            RxBus.post(new OnActivityResultPhotoObserver.OnActivityResult(photo));
            presenter.unregister();
       }
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
