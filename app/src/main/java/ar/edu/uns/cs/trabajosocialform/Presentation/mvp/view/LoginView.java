package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view;

import android.support.annotation.BinderThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import ar.edu.uns.cs.trabajosocialform.Presentation.bus.RxBus;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.LoginObserver;
import ar.edu.uns.cs.trabajosocialform.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginView extends ActivityView {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.et_user) EditText userEt;
    @BindView(R.id.et_password) EditText passwordEt;

    public LoginView(AppCompatActivity activity){
        super(activity);
        ButterKnife.bind(this, activity);
        getActivity().setSupportActionBar(toolbar);
    }

    @OnClick(R.id.btn_login)
    public void loginButtonClicked(){
        String user = userEt.getText().toString();
        String password = passwordEt.getText().toString();
        RxBus.post(new LoginObserver.LoginButtonClicked(user, password));
    }
}
