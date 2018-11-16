package ar.edu.uns.cs.trabajosocialform.Presentation.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter.AppStartPresenter;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter.LoginPresenter;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.AppStartView;
import ar.edu.uns.cs.trabajosocialform.R;

public class MainActivity extends AppCompatActivity {

    private AppStartPresenter presenter;
    public static final String KEY_ACTUAL_FORM = "FORM";
    public static final String KEY_CONFIGURATION_FILE = "CONFIG";
    public static final String KEY_UPDATE = "UPDATE";
    public static final String KEY_UPDATE_FORM = "UPDATE_FORM";
    public static final String KEY_FAMILIAR = "FAMILIAR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String user = getIntent().getStringExtra(LoginPresenter.KEY_USER);
        presenter = new AppStartPresenter(new AppStartView(this), user);

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
