package ar.edu.uns.cs.trabajosocialform.Presentation.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter.AppStartPresenter;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.AppStartActivity;
import ar.edu.uns.cs.trabajosocialform.R;

public class MainActivity extends AppCompatActivity {

    private AppStartPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new AppStartPresenter(new AppStartActivity(this));

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
