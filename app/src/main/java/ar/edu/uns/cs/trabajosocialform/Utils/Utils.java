package ar.edu.uns.cs.trabajosocialform.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.configuracion.Configuracion;
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

    public void addRemovingButtonListener(int panelId, final List<View> list){
        final ConstraintLayout panel = (ConstraintLayout)act.findViewById(panelId);
        ((ImageButton)panel.findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.size()>0){
                    //Tomo el ultimo elemento de la lista, lo escondo y lo elimino de la lista
                    View vista = list.get(list.size()-1);
                    vista.setVisibility(View.GONE);
                    list.remove(list.size()-1);
                }

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

    public Long getLongFromString(String s){
        Long num = null;
        if(!s.equals(""))
            num = Long.parseLong(s);

        return num;
    }

    public void setDetailValues(int panelId, int tituloId, String dato){
        ConstraintLayout panel = act.findViewById(panelId);
        String titulo = act.getResources().getString(tituloId);
        ((TextView)panel.findViewById(R.id.textViewTitulo)).setText(titulo);
        ((TextView)panel.findViewById(R.id.textViewDato)).setText(dato);
    }

    public void setDetailValues(View view, int tituloId, String dato){
        String titulo = act.getResources().getString(tituloId);
        ((TextView)view.findViewById(R.id.textViewTitulo)).setText(titulo);
        ((TextView)view.findViewById(R.id.textViewTitulo)).setText(dato);
    }

    public void setTitleValue(int panelId, int tituloId){
        ConstraintLayout panel = act.findViewById(panelId);
        String titulo = act.getResources().getString(tituloId);
        ((TextView)panel.findViewById(R.id.titulo)).setText(titulo);
    }

    public String getStringFromDate(Date fecha){
        String fechaString = null;
        if(fecha!=null){
            fechaString = fecha.toString();
        }

        return fechaString;
    }

    public void showAlertDialog(int titulo, int mensaje, final Runnable runnable){
        final List<Boolean> result = new ArrayList<Boolean>();
        new AlertDialog.Builder(act)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(titulo)
                .setMessage(mensaje)
                .setPositiveButton(R.string.boton_si, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        runnable.run();
                    }

                })
                .setNegativeButton(R.string.boton_no, null)
                .show();

    }

    public View inflarDetalles(int panelId){
        LinearLayout contenedor = act.findViewById(panelId);
        LayoutInflater inflater = LayoutInflater.from(act);

        View inflatedView = inflater.inflate(R.layout.detalles_item, contenedor, false);
        contenedor.addView(inflatedView);
        return inflatedView;
    }

    public void setValueToEditText(int panelId, String valor){
        ConstraintLayout panel = act.findViewById(panelId);
        ((EditText)panel.findViewById(R.id.editText)).setText(valor);
    }

    public void setValueToEditText(int panelId, Long valor){
        ConstraintLayout panel = act.findViewById(panelId);
        if(valor!=null)
            ((EditText)panel.findViewById(R.id.editText)).setText(valor+"");
        else
            ((EditText)panel.findViewById(R.id.editText)).setText("");
    }

    public void setValueToEditText(int panelId, Integer valor){
        ConstraintLayout panel = act.findViewById(panelId);
        if(valor!=null)
            ((EditText)panel.findViewById(R.id.editText)).setText(valor+"");
        else
            ((EditText)panel.findViewById(R.id.editText)).setText("");
    }

    public void setValueToSpinner(int panelId,int opcionesId,String compareValue){
        Spinner spinner = (Spinner)act.findViewById(panelId).findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(act, opcionesId, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            spinner.setSelection(spinnerPosition);
        }
    }

    public void setValueToSpinner(Spinner spinner, int opcionesId, String compareValue){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(act, opcionesId, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            spinner.setSelection(spinnerPosition);
        }
    }

    public void setValueToSpinner2(int panelId,int opcionesId,String compareValue, int opcionesId2, String compareValue2){
        Spinner spinner1 = (Spinner)act.findViewById(panelId).findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner)act.findViewById(panelId).findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(act, opcionesId, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            spinner1.setSelection(spinnerPosition);
        }
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(act, opcionesId2, android.R.layout.simple_spinner_item);
        spinner2.setAdapter(adapter2);
        if(compareValue2 != null){
            int spinner2Position = adapter.getPosition(compareValue2);
            spinner2.setSelection(spinner2Position);
        }
    }

    /**** Method for Setting the Height of the ListView dynamically.
     **** Hack to fix the issue of not showing all the items of the ListView
     **** when placed inside a ScrollView  ****/
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public View inflarEnUpdate(int panelId, int newLayoutId ){
        ConstraintLayout panel = act.findViewById(panelId);
        LinearLayout contenedor = (LinearLayout)panel.findViewById(R.id.contenedor);
        LayoutInflater inflater = LayoutInflater.from(act);

        View inflatedView = inflater.inflate(newLayoutId, contenedor, false);
        contenedor.addView(inflatedView);

        return inflatedView;
    }

    public Configuracion getConfigurationFile(){
        try
        {
            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    act.openFileInput("config.txt")));

            /*Leo el String json del archivo de configuracion y hago la deserializacion en la clase configuracion con la
             librería GSON*/
            String json = fin.readLine();
            if(json !=null){
                Configuracion config = (new Gson()).fromJson(json, Configuracion.class);
                return config;
            }
            else{
                return null;
            }
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
            ex.printStackTrace();
        }
        return null;
    }

    public boolean existsConfigurationFile(){
        try
        {
            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    act.openFileInput("config.txt")));
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
