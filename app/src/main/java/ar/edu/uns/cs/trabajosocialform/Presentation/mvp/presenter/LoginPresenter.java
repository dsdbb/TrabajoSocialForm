package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import ar.edu.uns.cs.trabajosocialform.Presentation.activities.MainActivity;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.RxBus;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.LoginObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.LoginView;
import ar.edu.uns.cs.trabajosocialform.R;

public class LoginPresenter {

    private LoginView view;

    public LoginPresenter(LoginView view) {
        this.view = view;
    }

    public void LoginClicked(String user, String password) {
        String [] usuarios = view.getContext().getResources().getStringArray(R.array.usuarios);
        for(int i=0; i<usuarios.length; i++){
            if(usuarios[i].equals(user) && usuarios[i+1].equals(password)){
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                view.getActivity().startActivity(intent);
                break;
            }
            i++;
            if(i+1==usuarios.length) Toast.makeText(view.getContext(),"Error en el Login",Toast.LENGTH_SHORT).show();
        }
    }

    public void register() {
        Activity activity = view.getActivity();

        if (activity == null) {
            return;
        }

        RxBus.subscribe(activity, new LoginObserver() {
            @Override
            public void onEvent(LoginButtonClicked value) {
                LoginClicked(value.getUser(), value.getPassword());
            }
        });

    }

    public void unregister() {
        Activity activity = view.getActivity();

        if (activity == null) {
            return;
        }
        RxBus.clear(activity);
    }
}
