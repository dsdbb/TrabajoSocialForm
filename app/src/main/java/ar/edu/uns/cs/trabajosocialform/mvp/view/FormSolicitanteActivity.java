package ar.edu.uns.cs.trabajosocialform.mvp.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import ar.edu.uns.cs.trabajosocialform.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.DataModel.Solicitante;
import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.Utils.FieldsValidator;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;
import ar.edu.uns.cs.trabajosocialform.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.mvp.presenter.solicitantePresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * This class is responsible of managing the UI of the Solicitante data and get the user data insertion
 * related with it.
 */

public class FormSolicitanteActivity extends GeneralActivity {

    private EditText nombreEt;
    private EditText apellidoEt;
    private EditText cuilEt;
    private EditText telefonoEt;
    private EditText otroTelefonoEt;

    private solicitantePresenter presenter;
    private Bitmap foto;

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int CAMERA_PHOTO = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_solicitante);
        presenter = new solicitantePresenter();

        /*Avoid showing the keyboard automatically*/
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        /*Get needed data from previous activity*/
        Intent intent = getIntent();
        form = (Formulario)intent.getSerializableExtra("FORM");
        config = (Configuracion)intent.getSerializableExtra("CONFIG");
        update = getIntent().getBooleanExtra("UPDATE", false);

        /*If the data of the Solicitante is not needed, we continuo with the next activity.
        Otherwise we init this GUI and adapt it correspondingly*/
        if(!config.getDatos_solicitante().required()){
            continuar(null);
        }
        else {
            inicializarGui();
            /*Bind Activity to use ButterKnife facilities*/
            ButterKnife.bind(this);

            /*Get all the editTexts*/
            nombreEt = findViewById(R.id.panel_nombres_solicitante).findViewById(R.id.editText);
            apellidoEt = findViewById(R.id.panel_apellidos_solicitante).findViewById(R.id.editText);
            cuilEt = findViewById(R.id.panel_cuil_solicitante).findViewById(R.id.editText);
            telefonoEt = findViewById(R.id.panel_telefono_principal_solicitante).findViewById(R.id.editText);
            otroTelefonoEt = findViewById(R.id.panel_otro_telefono_solicitante).findViewById(R.id.editText);

            /*If the action is an update, complete the fields with data correponding to the edited form*/
            if (update) {
                updateForm = (Formulario) getIntent().getSerializableExtra("UPDATE_FORM");
                rellenarCampos();
            }

            /*Adapt the view according to the configuration file (show only relevant fields)*/
            presenter.adaptView(config,this);
        }
    }

    @Override
    @OnClick(R.id.siguiente_button)
    protected void continuar(View view){
        Solicitante solicitante = tomarDatos();

        if(validate(solicitante)){
            form.setSolicitante(solicitante);
            Intent intent = new Intent(this,FormApoderadoActivity.class);
            putExtras(intent);
            startActivity(intent);

            /*If the data of the Solicitante is not required, when the user came back from the next activity
             * this activity does not have to be shown*/
            if(!config.getDatos_solicitante().required()){
                finish();
            }
        }
        else{
            Toast.makeText( this,R.string.datos_invalidos, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Inits an Activity to use the camera to take a photo.
     */
    @OnClick(R.id.plus_button)
    public void tomarFoto(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, CAMERA_PHOTO);
        }
    }

    /**
     * Gets the photo Bitmap and show it in the corresponding imageView
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if (requestCode == CAMERA_PHOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            foto = (Bitmap) extras.get("data");
            ImageView img = findViewById(R.id.imageView);
            img.setImageBitmap(foto);
        }
    }

    @Override
    protected void inicializarGui(){
        Utils utils = new Utils(this);

        /*Add the fields associated to the Solicitante to the template*/
        utils.addContentToTemplate(R.layout.form_solicitante);

        /*Toolbar title*/
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.titulo_solicitante);

        /*Datos*/
        utils.setValuesTvEt(R.string.nombres_solicitante,R.id.panel_nombres_solicitante);
        utils.setValuesTvEt(R.string.apellidos_solicitante,R.id.panel_apellidos_solicitante);
        utils.setValuesTvEt(R.string.cuil_solicitante,R.id.panel_cuil_solicitante);
        utils.setValuesTvEt(R.string.telefono_principal_solicitante,R.id.panel_telefono_principal_solicitante);
        utils.setValuesTvEt(R.string.otro_telefono_solicitante,R.id.panel_otro_telefono_solicitante);

        EditText cuil = findViewById(R.id.panel_cuil_solicitante).findViewById(R.id.editText);
        cuil.setInputType(InputType.TYPE_CLASS_NUMBER);

    }


    @Override
    protected Solicitante tomarDatos(){
        Utils utils = new Utils(this);
        String nombre = utils.getDataTvEt(R.id.panel_nombres_solicitante);
        String apellido = utils.getDataTvEt(R.id.panel_apellidos_solicitante);
        String cuilS = utils.getDataTvEt(R.id.panel_cuil_solicitante);
        String telefono = utils.getDataTvEt(R.id.panel_telefono_principal_solicitante);
        String otroTelefono = utils.getDataTvEt(R.id.panel_otro_telefono_solicitante);

        Long cuil = utils.getLongFromString(cuilS);

        String fotoString = utils.bitmapToString(foto);
        Solicitante solicitante = new Solicitante(nombre,apellido,cuil,telefono,otroTelefono,fotoString);

        return solicitante;

    }


    @Override
    protected void rellenarCampos(){
        Utils utils = new Utils(this);

        Solicitante solicitante = updateForm.getSolicitante();
        utils.setValueToEditText(R.id.panel_nombres_solicitante,solicitante.getNombres());
        utils.setValueToEditText(R.id.panel_apellidos_solicitante, solicitante.getApellidos());
        utils.setValueToEditText(R.id.panel_cuil_solicitante,solicitante.getCuil());
        utils.setValueToEditText(R.id.panel_telefono_principal_solicitante, solicitante.getTelefono());
        utils.setValueToEditText(R.id.panel_otro_telefono_solicitante, solicitante.getOtro_telefono());
    }

    @Override
    protected boolean validate(Object obj){
        boolean result = true;

        Solicitante solicitante = (Solicitante) obj;
        FieldsValidator validator = new FieldsValidator();

        if(!validator.validateShortString(solicitante.getNombres()) && config.getDatos_solicitante().isNombres_solicitante()){
            nombreEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorError));
            result = false;
        }
        else{
            nombreEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorMain));
        }
        if(!validator.validateShortString(solicitante.getApellidos()) && config.getDatos_solicitante().isApellidos_solicitante()){
            apellidoEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorError));
            result = false;
        }
        else{
            apellidoEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorMain));
        }
        if(!validator.validateCuil(solicitante.getCuil()) && config.getDatos_solicitante().isCuil_solicitante()){
            cuilEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorError));
            result = false;
        }
        else{
            cuilEt.setBackgroundTintList(getResources().getColorStateList(R.color.colorMain));
        }

        return result;
    }

}
