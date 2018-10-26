package com.example.a14791.marcelogps;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnShowLocation;
    TextView vt;
    TextView vlat;
    TextView vlng;

    // GPSTracker class
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
        gps = new GPSTracker(MainActivity.this);
        vt = findViewById(R.id.puntos);
        vlat = findViewById(R.id.lat);
        vlng = findViewById(R.id.lng);

        findViewById(R.id.floating_button_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
            }

            private void saveTask() {
                final TextView vlatitud = findViewById(R.id.lat);
                final TextView vlongitud  = findViewById(R.id.lng);
                /*if (vlatitud.isEmpty()) {
                    editTextTask.setError("Task required");
                    editTextTask.requestFocus();
                    return;
                }*/
                class SaveTask extends AsyncTask<Void, Void, Void> {
                    @Override
                    protected Void doInBackground(Void... voids) {

                        //creating a task
                        Task task = new Task();
                        task.setTask("1");
                        task.setLatitud(vlatitud.toString());
                        task.setLongitud(vlongitud.toString());

                        //adding to database
                        DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                                .taskDao()
                                .insert(task);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
                    }
                }

                SaveTask st = new SaveTask();
                st.execute();

                }


        });

        // Show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Create class object

                // Check if GPS enabled
                if(gps.canGetLocation()) {

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                    vt.setText("Your Location is - \nLat: " + latitude + "\nLong: " + longitude);
                    vlat.setText("Lat: " + latitude);
                    vlng.setText("long: " + longitude);
                } else {
                    // Can't get location.
                    // GPS or network is not enabled.
                    // Ask user to enable GPS/network in settings.
                    gps.showSettingsAlert();
                }
            }
        });

    }
    }



