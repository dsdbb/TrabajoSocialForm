package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Solicitante;
import ar.edu.uns.cs.trabajosocialform.Data.Database.DatabaseAcces;
import ar.edu.uns.cs.trabajosocialform.Data.ServerConnection.ServerAccess;
import ar.edu.uns.cs.trabajosocialform.Data.Transactions.Transaction;
import ar.edu.uns.cs.trabajosocialform.Data.Transactions.TransactionOptions;
import ar.edu.uns.cs.trabajosocialform.Presentation.activities.DetallesFormActivity;
import ar.edu.uns.cs.trabajosocialform.Presentation.activities.FormularioActivity;
import ar.edu.uns.cs.trabajosocialform.Presentation.activities.MainActivity;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.RxBus;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.ContextItemSelectedObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.ContextMenuObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.EntradasItemClickedObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.NuevaEntradaObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.OptionsItemSelectedObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.OptionsMenuObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.VerEntradasObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.ActivityView;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.EntradasView;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.FormularioView;
import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;
import io.reactivex.observers.DisposableObserver;

public class EntradasPresenter {

    private EntradasView view;
    private List<Formulario> forms;
    private List<Solicitante> solicitantes;
    private final DatabaseAcces db;

    public EntradasPresenter(EntradasView view){
        this.view = view;
        db = new DatabaseAcces();

        /*Get Forms and Solicitantes to show in the list*/
        forms = (new DatabaseAcces()).getFormularios(view.getActivity());
        DatabaseAcces da = new DatabaseAcces();
        solicitantes = new ArrayList<Solicitante>();
        for (Formulario form: forms) {
            Solicitante solicitante = da.getSolicitante(view.getActivity(),form.getSolicitanteId());
            solicitantes.add(solicitante);
        }
        view.refreshList(solicitantes);
    }

    public void onContextItemSelected(final MenuItem item){

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final ContextMenu.ContextMenuInfo menuInfo = item.getMenuInfo();
        final int pos = item.getGroupId();
        Log.i("Position",pos+"");

        switch(item.getItemId()) {
            case R.id.edit:
                //Inicio la misma actividad de alta pero con un parámetro que especifique que se refiere a una modificación
                Intent intent = new Intent(view.getActivity(),FormularioActivity.class);
                intent.putExtra(MainActivity.KEY_UPDATE,true);
                //int pos = item.getOrder();
                Formulario form = forms.get(pos);
                db.getCompleteForm(view.getActivity(),form);
                intent.putExtra(MainActivity.KEY_UPDATE_FORM,form);
                view.getActivity().startActivityForResult(intent,1);
                break;
            case R.id.delete:
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        //int pos = item.getOrder();
                        int formId = forms.get(pos).getId();
                        db.delete(view.getActivity(), forms.get(pos), new DisposableObserver<Boolean>() {
                            @Override
                            public void onNext(Boolean aBoolean) {
                                if(aBoolean) view.showMessage(R.string.delete_correcto);
                            }

                            @Override
                            public void onError(Throwable e) {
                                view.showMessage(R.string.error_delete);
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                        forms.remove(pos);
                        solicitantes.remove(pos);
                        view.refreshList(solicitantes);
                    }
                };
                view.showAlertDialog(R.string.titulo_confirmacion_eliminar, R.string.texto_confirmacion_eliminar,runnable);

                break;
        }
    }

    public void onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_refresh:
                refreshInternetDatabase();
                break;
            default:
                break;
        }
    }

    public void onEntradaItemClicked(int position){
        Intent intent = new Intent(view.getContext(),DetallesFormActivity.class);
        Formulario form = forms.get(position);
        intent.putExtra(MainActivity.KEY_ACTUAL_FORM,form);
        view.getActivity().startActivity(intent);
    }

    public void refreshInternetDatabase(){
        DatabaseAcces db = new DatabaseAcces();
        List<Transaction> transactions = db.getTransactions(view.getActivity());
        ServerAccess sa = new ServerAccess(view.getActivity());

        Log.i("Tamaño Transacciones",transactions.size()+"");
        if(transactions.size()==0){
            view.showMessage(R.string.sin_actualizaciones);
        }
        else {
            for (int i = 0; i < transactions.size(); i++) {
                Transaction transaction = transactions.get(i);
                if (transaction.getTransactionId() == TransactionOptions.INSERT.getValue()) {
                    Formulario form = db.getFormulario(view.getActivity(), transaction.getFormId());
                    sa.uploadForm(form, transaction, new DisposableObserver<Boolean>() {
                        @Override
                        public void onNext(Boolean aBoolean) {}
                        @Override
                        public void onError(Throwable e) {
                            view.showMessage(R.string.error_servidor);
                        }
                        @Override
                        public void onComplete() {}
                    });
                }
                if (transaction.getTransactionId() == TransactionOptions.DELETE.getValue()) {
                    sa.deleteForm(transaction.getFormId(), transaction, new DisposableObserver<Boolean>() {
                        @Override
                        public void onNext(Boolean aBoolean) {

                        }

                        @Override
                        public void onError(Throwable e) {
                            view.showMessage(R.string.error_servidor);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
                }

            }
            view.showMessage(R.string.actualizado_correctamente);
        }
    }

    public void register() {
        Activity activity = view.getActivity();

        if (activity==null){
            return;
        }

        RxBus.subscribe(activity, new OptionsMenuObserver() {
            @Override
            public void onEvent(OptionsMenu value) {
                view.setOptionsMenu(value.getMenu());
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

        RxBus.subscribe(activity, new OptionsItemSelectedObserver() {
            @Override
            public void onEvent(OptionsItemSelected value) {
                onOptionsItemSelected(value.getMenuItem());
            }
        });

        RxBus.subscribe(activity, new EntradasItemClickedObserver() {
            @Override
            public void onEvent(EntradasItemClicked value) {
                onEntradaItemClicked(value.getPosition());
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
