package ar.edu.uns.cs.trabajosocialform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import ar.edu.uns.cs.trabajosocialform.Database.StorageAccess;
import ar.edu.uns.cs.trabajosocialform.ServerConnection.ServerAccess;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;
import ar.edu.uns.cs.trabajosocialform.mvp.view.EntradasActivity;
import ar.edu.uns.cs.trabajosocialform.mvp.view.FormularioActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Sets the toolbar*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*Gets the configuration file from server*/
        ServerAccess sa = new ServerAccess(this);
        sa.getConfigurationFileFromServer();
    }


    /**
     * Inits a new Activity that gets data to create a new entry of the form
     * @param view
     */
    public void nuevaEntrada(View view){
        /*First checks if exists a configuration file to start*/
        //Utils utils = new Utils(this);
        StorageAccess sa = new StorageAccess();
        if(sa.existsConfigurationFile(this)){
            Intent intent = new Intent(this,FormularioActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this,R.string.error_fichero,Toast.LENGTH_SHORT);
        }


    }

    /**
     * Create a new Activity to show the created forms
     * @param view
     */
    public void verEntradas(View view) {
        Intent intent = new Intent(this,EntradasActivity.class);
        startActivity(intent);

    }


}
