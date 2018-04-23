package ar.edu.uns.cs.trabajosocialform.Utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.fragments.DatePickerFragment;

public class Utils {

    private Activity act;

    public Utils(Activity act){
        this.act = act;
    }

    public static void getFecha(final Activity act){

        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            String mes = String.format("%02d",month+1);
            String dia = String.format("%02d",day);
            // +1 because january is zero
            final String selectedDate = dia + " / " + mes + " / " + year;
            ((EditText)act.findViewById(R.id.editText)).setText(selectedDate);
        }
    });
        newFragment.show(act.getFragmentManager(), "datePicker");
    }

    public void setValuesTvSpinner(int opcionesSpinnerId, int tituloId, int panelId){
        String[] opciones = act.getResources().getStringArray(opcionesSpinnerId);
        String titulo = act.getResources().getString(tituloId);
        ConstraintLayout panel = (ConstraintLayout)act.findViewById(panelId);
        ((TextView)panel.findViewById(R.id.textView)).setText(titulo);
        ((Spinner)panel.findViewById(R.id.spinner)).setAdapter(new ArrayAdapter<String>(
                act,R.layout.spinner_row2,opciones));
    }

    public void setValuesTvEt(int tituloId, int panelId){
        String titulo = act.getResources().getString(tituloId);
        ConstraintLayout panel = (ConstraintLayout)act.findViewById(panelId);
        ((TextView)panel.findViewById(R.id.textView)).setText(titulo);
    }

    public String getDataTvSpinner(int panelId){
        ConstraintLayout panel = (ConstraintLayout)act.findViewById(panelId);
        return ((Spinner)panel.findViewById(R.id.spinner)).getSelectedItem().toString();
    }

    public String getDataTvEt(int panelId){
        ConstraintLayout panel = (ConstraintLayout)act.findViewById(panelId);
        return ((EditText)panel.findViewById(R.id.editText)).getText().toString();
    }

    public Boolean getBoolean(String s){
        if(s.equals("SI") || s.equals("Si") || s.equals("si")){
            return true;
        }
        else{
            return false;
        }
    }

    public void addDateListener(int panelId){
        ConstraintLayout panel = (ConstraintLayout)act.findViewById(panelId);
        ((EditText)panel.findViewById(R.id.editText)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFecha(act);
            }
        });
    }
}
