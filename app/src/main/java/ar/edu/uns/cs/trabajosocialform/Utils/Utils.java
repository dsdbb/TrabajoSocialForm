package ar.edu.uns.cs.trabajosocialform.Utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.fragments.DatePickerFragment;

public class Utils {

    private Activity act;

    public Utils(Activity act){
        this.act = act;
    }

    public void getFecha(final ConstraintLayout panel ){

        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            String mes = String.format("%02d",month+1);
            String dia = String.format("%02d",day);
            // +1 because january is zero
            final String selectedDate = dia + " / " + mes + " / " + year;
            ((EditText)panel.findViewById(R.id.editText)).setText(selectedDate);
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

    public void setValuesTvSpinner2(int opcionesSpinner1Id,int opcionesSpinner2Id, int tituloId, int panelId){
        String[] opciones1 = act.getResources().getStringArray(opcionesSpinner1Id);
        String[] opciones2 = act.getResources().getStringArray(opcionesSpinner2Id);
        String titulo = act.getResources().getString(tituloId);
        ConstraintLayout panel = (ConstraintLayout)act.findViewById(panelId);
        ((TextView)panel.findViewById(R.id.textView)).setText(titulo);
        ((Spinner)panel.findViewById(R.id.spinner1)).setAdapter(new ArrayAdapter<String>(
                act,R.layout.spinner_row2,opciones1));
        ((Spinner)panel.findViewById(R.id.spinner2)).setAdapter(new ArrayAdapter<String>(
                act,R.layout.spinner_row2,opciones2));
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

    public List<String> getDataTvSpinner2(int panelId){
        List<String> lista = new ArrayList<String>();
        ConstraintLayout panel = (ConstraintLayout)act.findViewById(panelId);
        String val1 = ((Spinner)panel.findViewById(R.id.spinner1)).getSelectedItem().toString();
        String val2 = ((Spinner)panel.findViewById(R.id.spinner2)).getSelectedItem().toString();
        lista.add(val1);
        lista.add(val2);
        return lista;
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

    public String getStringFromBoolean(Boolean b){
        if(b){
            return "Si";
        }
        else{
            return "No";
        }
    }

    public void addDateListener(int panelId){
        final ConstraintLayout panel = (ConstraintLayout)act.findViewById(panelId);
        ((EditText)panel.findViewById(R.id.editText)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFecha(panel);
            }
        });
    }

    public void addAddingButtonListener(int panelId, final int newLayoutId, final List<View> list){
        final ConstraintLayout panel = (ConstraintLayout)act.findViewById(panelId);
        ((ImageButton)panel.findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout contenedor = (LinearLayout) panel.findViewById(R.id.contenedor);
                LayoutInflater inflater = LayoutInflater.from(act);

                View inflatedView = inflater.inflate(newLayoutId, contenedor, false);
                list.add(inflatedView);
                contenedor.addView(inflatedView);
            }
        });

    }

    public Date getDateFromString(String fechaS){
        Date fecha=null;
        if(!fechaS.equals("")){
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
            try {
                fecha = formatoDelTexto.parse(fechaS);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return fecha;
    }

    public Integer getIntegerFromString(String intS){
        Integer num = null;
        if(!intS.equals(""))
            num = Integer.parseInt(intS);

        return num;
    }

    public void setDetailValues(int panelId, int tituloId, String dato){
        ConstraintLayout panel = act.findViewById(panelId);
        String titulo = act.getResources().getString(tituloId);
        ((TextView)panel.findViewById(R.id.textViewTitulo)).setText(titulo);
        ((TextView)panel.findViewById(R.id.textViewDato)).setText(dato);
    }

    public String getStringFromDate(Date fecha){
        String fechaString = null;
        if(fecha!=null){
            fechaString = fecha.toString();
        }

        return fechaString;
    }
}
