package ar.edu.uns.cs.trabajosocialform.Presentation.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter.LoginPresenter;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.LoginView;
import ar.edu.uns.cs.trabajosocialform.R;

public class LoginActivity extends AppCompatActivity {

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(new LoginView(this));
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
