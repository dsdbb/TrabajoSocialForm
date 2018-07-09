package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Familiar;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Presentation.adapters.EntradasRecyclerViewAdapter;
import ar.edu.uns.cs.trabajosocialform.Presentation.adapters.FormFamiliaresRecyclerViewAdapter;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.RxBus;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.NextButtonClickedObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.NuevoFamiliarObserver;
import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormGrupoFamiliarView extends ActivityView implements FormActions{


    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.recycler_view)RecyclerView recyclerView;


    public FormGrupoFamiliarView(AppCompatActivity activity){
        super(activity);
    }

 /*   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_grupo_familiar);

        Intent intent = getIntent();
        form = (Formulario) intent.getSerializableExtra("FORM");
        config = (Configuracion) intent.getSerializableExtra("CONFIG");

        if (!config.getDatos_grupo_familiar().required()) {
            continuar(null);
        } else {
            inicializarGui();
            //Bind Activity to use ButterKnife facilities
            ButterKnife.bind(this);

            //Chequeo si es un update y en ese caso relleno los campos
            update = getIntent().getBooleanExtra("UPDATE", false);
            if (update) {
                updateForm = (Formulario) getIntent().getSerializableExtra("UPDATE_FORM");
                rellenarCampos();
            }
        }

    }*/

    @OnClick(R.id.nuevo_familiar_btn)
    public void nuevoFamiliar(View view) {
        RxBus.post(new NuevoFamiliarObserver.NuevoFamiliar());
    }
    /*    Intent intent = new Intent(this, NuevoFamiliarView.class);
        intent.putExtra("CONFIG", config);
        intent.putExtra("FORM", form);
        startActivityForResult(intent, 1);
    }*/


    @Override
    public void inicializarGui() {
        Utils utils = new Utils(getActivity());
        utils.addContentToTemplate(R.layout.form_grupo_familiar);

        ButterKnife.bind(this,getActivity());

        /*Titulo toolbar*/
        toolbar.setTitle(R.string.titulo_grupo_familiar);


    }

    @Override
    @OnClick(R.id.siguiente_button)
    public void continuar(View view) {
        RxBus.post(new NextButtonClickedObserver.NextButtonClicked());
    }

    public void refreshContenedorFamiliares(List<Familiar> familiares) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        FormFamiliaresRecyclerViewAdapter adapter = new FormFamiliaresRecyclerViewAdapter(familiares);
        recyclerView.setAdapter(adapter);

        /*Para el menu al mantener presionado*/
       // registerForContextMenu(listview);

    }

    @Override
    public void rellenarCampos(Formulario updateForm) {
        List<Familiar> familiares = updateForm.getFamiliares();
        refreshContenedorFamiliares(familiares);
        //listview.setAdapter(new FormGrupoFamiliarView.customAdapter(this, familiares));
        //Utils.setListViewHeightBasedOnChildren(listview);

        /*Copio todos los familiares al nuevo form*/
        /*for (int i = 0; i < familiares.size(); i++) {
            form.getFamiliares().add(familiares.get(i));
        }*/

        /*Para el menu al mantener presionado*/
        //registerForContextMenu(listview);
    }

    @Override
    public Object tomarDatos(){return null;}

    public void finish() {
        getActivity().finish();
    }




    @Override
    public boolean validate(Object obj,Configuracion config){
        return true;
    }

    public void showAlertDialog(Runnable run) {
        Utils utils = new Utils(getActivity());
        utils.showAlertDialog(R.string.titulo_confirmacion_eliminar, R.string.texto_confirmacion_eliminar,run);
    }

    public void setContextMenu(Menu menu){
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_list, menu);
    }
}
