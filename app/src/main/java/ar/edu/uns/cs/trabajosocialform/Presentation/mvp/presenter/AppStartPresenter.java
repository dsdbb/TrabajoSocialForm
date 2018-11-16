package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter;

import android.app.Activity;
import android.content.Intent;

import ar.edu.uns.cs.trabajosocialform.Data.Database.StorageAccess;
import ar.edu.uns.cs.trabajosocialform.Data.ServerConnection.ServerAccess;
import ar.edu.uns.cs.trabajosocialform.Presentation.activities.EntradasActivity;
import ar.edu.uns.cs.trabajosocialform.Presentation.activities.FormularioActivity;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.RxBus;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.NuevaEntradaObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.VerEntradasObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.AppStartView;
import ar.edu.uns.cs.trabajosocialform.R;
import io.reactivex.observers.DisposableObserver;

public class AppStartPresenter {

    private AppStartView view;
    private String user;

    public AppStartPresenter(AppStartView view, String user){
        this.view = view;
        this.user = user;
        //Set the toolbar to the view

        //App is starting, check and get the configuration file
        getConfigurationFile();
    }

    public void getConfigurationFile(){
        /*Gets the configuration file from server*/
        ServerAccess sa = new ServerAccess(view.getActivity());
        sa.getConfigurationFileFromServer(new DisposableObserver<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                if(aBoolean){
                    view.showMessage(R.string.nueva_configuracion);
                }
                else{
                    view.showMessage(R.string.error_fichero);
                }
            }

            @Override
            public void onError(Throwable e) {
                view.showMessage(R.string.error_servidor);
            }

            @Override
            public void onComplete() {}
        });
    }


    public void verEntradas(){
        Intent intent = new Intent(view.getContext(),EntradasActivity.class);
        view.getActivity().startActivity(intent);

    }

    public void nuevaEntrada(){
        /*First checks if exists a configuration file to start*/
        StorageAccess sa = new StorageAccess();
        if(sa.existsConfigurationFile(view.getActivity())){
            Intent intent = new Intent(view.getContext(), FormularioActivity.class);
            intent.putExtra(LoginPresenter.KEY_USER, user);
            view.getActivity().startActivity(intent);
        }
        else{
            view.showMessage(R.string.error_fichero);
        }
    }

    public void register() {
        Activity activity = view.getActivity();

        if (activity==null){
            return;
        }

        RxBus.subscribe(activity, new VerEntradasObserver() {
            @Override
            public void onEvent(VerEntradas value) {
                verEntradas();
            }
        });

        RxBus.subscribe(activity, new NuevaEntradaObserver() {
            @Override
            public void onEvent(NuevaEntrada value) {
                nuevaEntrada();
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
