package ar.edu.uns.cs.trabajosocialform;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;

import ar.edu.uns.cs.trabajosocialform.Database.DatabaseAcces;
import ar.edu.uns.cs.trabajosocialform.ServerConnection.ServerAccess;
import ar.edu.uns.cs.trabajosocialform.ServerConnection.ServerSingleton;
import ar.edu.uns.cs.trabajosocialform.Utils.Utils;

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
        Utils utils = new Utils(this);

        if(utils.existsConfigurationFile()){
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
