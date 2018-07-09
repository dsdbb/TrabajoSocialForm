package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.widget.AdapterView;

import com.google.gson.Gson;

import java.util.List;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Familiar;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.Presentation.activities.MainActivity;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.RxBus;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.ContextItemSelectedObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.ContextMenuObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.NextButtonClickedObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.NuevoFamiliarObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.OnActivityResultFamiliarObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.FormGrupoFamiliarView;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.FormSituacionHabitacionalView;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.NuevoFamiliarView;
import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;

public class FormGrupoFamiliarPresenter extends GeneralSectionPresenter {

    private FormGrupoFamiliarView view;

    public FormGrupoFamiliarPresenter(FormGrupoFamiliarView view, Formulario form, Configuracion configuration, Formulario updateForm) {
        this.view = view;
        this.form = form;
        this.configuration = configuration;
        this.updateForm = updateForm;

        //Compruebo si se requiere la vista de solicitante
        if(!configuration.getDatos_solicitante().required()){
            //continue
            onNextButtonClicked();
            view.finish();
        }
        else{
            view.inicializarGui();
        }
        //If there is a form to update, fill fields
        if(updateForm!=null){
            this.update = true;
            view.rellenarCampos(updateForm);
            //add familiares to the form that is been created
            List<Familiar> familiares = updateForm.getFamiliares();
            for (int i = 0; i < familiares.size(); i++) {
                form.getFamiliares().add(familiares.get(i));
            }
        }
    }

    public void onNextButtonClicked(){
        Intent intent = new Intent(view.getActivity(), FormSituacionHabitacionalView.class);
        intent.putExtra("CONFIG", configuration);
        intent.putExtra("FORM", form);
        intent.putExtra("UPDATE", update);
        if (update)
            intent.putExtra("UPDATE_FORM", updateForm);
        view.getActivity().startActivity(intent);
        if (!configuration.getDatos_grupo_familiar().required()) {
            view.finish();
        }
    }

    public void onNuevoFamiliarClicked(){
        Intent intent = new Intent(view.getActivity(), NuevoFamiliarView.class);
        intent.putExtra(MainActivity.KEY_CONFIGURATION_FILE,configuration);
        intent.putExtra(MainActivity.KEY_ACTUAL_FORM,form);
        view.getActivity().startActivityForResult(intent,1);
    }

    public void onActivityResultFamiliar(Intent data) {


        /*Replace the old form with the new one that came from the intent*/
        if (data != null) {
            form = (Formulario) data.getSerializableExtra("RETURN_FORM");
            Log.i("LOG","Familiar actualizado: "+(new Gson()).toJson(form.getFamiliares()));

            /*If the action was an update I have to delete the old familiar*/
            boolean familiarActualizado = data.getBooleanExtra("UPDATED_FAMILIAR", false);
            if (familiarActualizado) {
                Familiar oldFamiliar = (Familiar) data.getSerializableExtra("OLD_FAMILIAR");
                for (int i = 0; i < form.getFamiliares().size(); i++) {
                    if (form.getFamiliares().get(i).equals(oldFamiliar)) {
                        form.getFamiliares().remove(i);
                    }
                }

            }

            //Refresh RecyclerView
            view.refreshContenedorFamiliares(form.getFamiliares());
        }
    }

    public void onContextItemSelected(final MenuItem item){

        final ContextMenu.ContextMenuInfo menuInfo = item.getMenuInfo();
        final int pos = item.getGroupId();
        switch(item.getItemId()) {
            case R.id.edit:
                Intent intent = new Intent(view.getActivity(),NuevoFamiliarView.class);
               // putExtras(intent);
                intent.putExtra("CONFIG",configuration);
                intent.putExtra("FORM",form);
                intent.putExtra("UPDATE",true);
                //int pos = item.getOrder();
                Familiar familiar = form.getFamiliares().get(pos);
                intent.putExtra("UPDATE_FAMILIAR", familiar);
                view.getActivity().startActivityForResult(intent,1);
                break;
            case R.id.delete:

                Runnable run = new Runnable() {
                    @Override
                    public void run() {
                        int position = item.getOrder();
                        form.getFamiliares().remove(pos);
                        view.refreshContenedorFamiliares(form.getFamiliares());

                    }
                };
                view.showAlertDialog(run);

                break;
            default:
                break;
        }
    }

    public void register() {
        Activity activity = view.getActivity();
        if (activity==null){
            return;
        }

        RxBus.subscribe(activity, new NextButtonClickedObserver() {
            @Override
            public void onEvent(NextButtonClicked value) {
                onNextButtonClicked();
            }
        });

        RxBus.subscribe(activity, new NuevoFamiliarObserver() {
            @Override
            public void onEvent(NuevoFamiliar value) {
                onNuevoFamiliarClicked();
            }
        });

        RxBus.subscribe(activity, new OnActivityResultFamiliarObserver() {
            @Override
            public void onEvent(OnActivityResultFamiliar value) {
                onActivityResultFamiliar(value.getIntent());
            }
        });

        RxBus.subscribe(activity, new ContextMenuObserver() {
            @Override
            public void onEvent(ContextMenu value) {
                view.setContextMenu(value.getMenu());
            }
        });

        RxBus.subscribe(activity, new ContextItemSelectedObserver() {
            @Override
            public void onEvent(ContextItemSelected value) {
                onContextItemSelected(value.getMenuItem());
            }
        });



    }



    public void unregister() {
        Activity activity = view.getActivity();

        if (activity==null){
            return;
        }
        RxBus.clear(activity);
    }
}
