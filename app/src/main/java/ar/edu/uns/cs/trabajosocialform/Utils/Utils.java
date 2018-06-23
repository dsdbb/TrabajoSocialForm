package ar.edu.uns.cs.trabajosocialform.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.arch.persistence.room.TypeConverter;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.constraint.ConstraintLayout;
import android.util.Base64;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
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
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ar.edu.uns.cs.trabajosocialform.R;
import ar.edu.uns.cs.trabajosocialform.configuracion.Configuracion;
import ar.edu.uns.cs.trabajosocialform.fragments.DatePickerFragment;

/**
 * Util methods
 */
public class Utils {

    private Activity act;

    public Utils(Activity act){
        this.act = act;
    }

    /**
     * Get the date from the datePicker and set it in the corresponding editText
     * @param panel Panel with layout tv_et_fecha
     */
    public void getFecha(final ConstraintLayout panel ){

        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            // +1 because January is 0
            String mes = String.format("%02d",month+1);
            String dia = String.format("%02d",day);
            final String selectedDate = dia + " / " + mes + " / " + year;
            ((EditText)panel.findViewById(R.id.editText)).setText(selectedDate);
        }
    });
        newFragment.show(act.getFragmentManager(), "datePicker");
    }

    /**
     * Set values of title and options to the spinner belonging to a corresponding panel
     * @param opcionesSpinnerId Spinner options id
     * @param tituloId Title string id
     * @param panelId Panel id with layout tv_spinner
     */
    public void setValuesTvSpinner(int opcionesSpinnerId, int tituloId, int panelId){
        String[] opciones = act.getResources().getStringArray(opcionesSpinnerId);
        String titulo = act.getResources().getString(tituloId);
        ConstraintLayout panel = (ConstraintLayout)act.findViewById(panelId);
        ((TextView)panel.findViewById(R.id.textView)).setText(titulo);

        ArrayAdapter adapter = new ArrayAdapter<String>(act,R.layout.spinner_row2,opciones);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ((Spinner)panel.findViewById(R.id.spinner)).setAdapter(adapter);
    }

    /**
     * Set values of title and options to both spinners belonging to a corresponding panel
     * @param opcionesSpinner1Id First spinner options id
     * @param opcionesSpinner2Id Second spinner options id
     * @param tituloId Title String id
     * @param panelId Panel id with layout tv_spinner2
     */
    public void setValuesTvSpinner2(int opcionesSpinner1Id,int opcionesSpinner2Id, int tituloId, int panelId){
        String[] opciones1 = act.getResources().getStringArray(opcionesSpinner1Id);
        String[] opciones2 = act.getResources().getStringArray(opcionesSpinner2Id);
        String titulo = act.getResources().getString(tituloId);
        ConstraintLayout panel = (ConstraintLayout)act.findViewById(panelId);
        ((TextView)panel.findViewById(R.id.textView)).setText(titulo);
        ArrayAdapter adapter1 = new ArrayAdapter<String>(act,R.layout.spinner_row2,opciones1);
        adapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ((Spinner)panel.findViewById(R.id.spinner1)).setAdapter(adapter1);
        ArrayAdapter adapter2 = new ArrayAdapter<String>(act,R.layout.spinner_row2,opciones2);
        adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ((Spinner)panel.findViewById(R.id.spinner2)).setAdapter(adapter2);
    }


    /**
     * Set title to a field panel
     * @param tituloId title string id
     * @param panelId panel id with tv_et layout
     */
    public void setValuesTvEt(int tituloId, int panelId){
        String titulo = act.getResources().getString(tituloId);
        ConstraintLayout panel = (ConstraintLayout)act.findViewById(panelId);
        ((TextView)panel.findViewById(R.id.textView)).setText(titulo);
    }

    /**
     * Get selected option from spinner
     * @param panelId panel id with layout tv_spinner
     * @return selected option
     */
    public String getDataTvSpinner(int panelId){
        ConstraintLayout panel = (ConstraintLayout)act.findViewById(panelId);
        return ((Spinner)panel.findViewById(R.id.spinner)).getSelectedItem().toString();
    }

    /**
     * get seleced options from spinner
     * @param panelId panel with layout tv_spinner2
     * @return List with first element as first spinner option and second element as second spinner option
     */
    public List<String> getDataTvSpinner2(int panelId){
        List<String> lista = new ArrayList<String>();
        ConstraintLayout panel = (ConstraintLayout)act.findViewById(panelId);
        String val1 = ((Spinner)panel.findViewById(R.id.spinner1)).getSelectedItem().toString();
        String val2 = ((Spinner)panel.findViewById(R.id.spinner2)).getSelectedItem().toString();
        lista.add(val1);
        lista.add(val2);
        return lista;
    }

    /**
     * Get data inserted in an editText
     * @param panelId panel id with layout tv_et
     * @return Inserted String
     */
    public String getDataTvEt(int panelId){
        ConstraintLayout panel = (ConstraintLayout)act.findViewById(panelId);
        return ((EditText)panel.findViewById(R.id.editText)).getText().toString();
    }

    /**
     * Get boolean from String (Si,si,SI)
     * @param s String to be associated with boolean value
     * @return boolean value
     */
    public Boolean getBoolean(String s){
        if(s.equals("SI") || s.equals("Si") || s.equals("si")){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Get String from boolean value
     * @param b boolean value to be converted in String
     * @return corresponding String
     */
    public String getStringFromBoolean(Boolean b){
        if(b){
            return "Si";
        }
        else{
            return "No";
        }
    }

    /**
     * Add Listener to a panel responsible of getting a date
     * @param panelId panel id with layout tv_et_fecha
     */
    public void addDateListener(int panelId){
        final ConstraintLayout panel = (ConstraintLayout)act.findViewById(panelId);
        ((EditText)panel.findViewById(R.id.editText)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFecha(panel);
            }
        });
    }

    /**
     * Add listener to the add button of a tv_button panel
     * @param panelId panel id with tv_button layout
     * @param newLayoutId layout wanted to be inflated by the add button
     * @param list List of inflated views by the button to add the new added view
     */
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

    /**
     * Add listener to the remove button of a tv_button panel
     * @param panelId panel id with tv_button layout
     * @param list List of inflated view by the panel to remove the last inflated view
     */
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

    /**
     * Add the fields of a section to the main template
     * @param layoutId layout containing the corresponding fields of the section
     */
    public void addContentToTemplate(int layoutId){
        LinearLayout contenedor = (LinearLayout) act.findViewById(R.id.contenedor);
        LayoutInflater inflater = LayoutInflater.from(act);

        View inflatedView = inflater.inflate(layoutId, contenedor, false);
        contenedor.addView(inflatedView);

    }

    /**
     * Get a java Date from a String if format "dd/MM/yyyy"
     * @param fechaS String date in "dd/MM/yyyy" format
     * @return java Date according to received String
     */
    public Date getDateFromString(String fechaS){
        Date fecha=null;
        if(!fechaS.equals("")){
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
            try {
                String date = fechaS.replaceAll(" ","");
                Log.i("Date",date);
                fecha = formatoDelTexto.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return fecha;
    }

    /**
     * Convert a String in an Integer
     * @param intS String to be converted
     * @return converted Integer
     */
    public Integer getIntegerFromString(String intS){
        Integer num = null;
        if(!intS.equals(""))
            num = Integer.parseInt(intS);

        return num;
    }

    /**
     * Convert String in a Long value
     * @param s String to be converted
     * @return converted Long
     */
    public Long getLongFromString(String s){
        Long num = null;
        if(!s.equals(""))
            num = Long.parseLong(s);

        return num;
    }

    /**
     * Set values to a detail layout
     * @param panelId panel id with detalles_item layout
     * @param tituloId id of the detail title
     * @param dato value associated with the title
     */
    public void setDetailValues(int panelId, int tituloId, String dato){
        ConstraintLayout panel = act.findViewById(panelId);
        String titulo = act.getResources().getString(tituloId);
        ((TextView)panel.findViewById(R.id.textViewTitulo)).setText(titulo);
        ((TextView)panel.findViewById(R.id.textViewDato)).setText(dato);
    }

    /**
     * Set values to a detail layout
     * @param view View with detalles_item layout
     * @param tituloId id of the detail title
     * @param dato value associated with the title
     */
    public void setDetailValues(View view, int tituloId, String dato){
        String titulo = act.getResources().getString(tituloId);
        ((TextView)view.findViewById(R.id.textViewTitulo)).setText(titulo);
        ((TextView)view.findViewById(R.id.textViewDato)).setText(dato);
    }

    /**
     * Set title to Title field of the panel
     * @param panelId details panel id
     * @param tituloId String Title id
     */
    public void setTitleValue(int panelId, int tituloId){
        ConstraintLayout panel = act.findViewById(panelId);
        String titulo = act.getResources().getString(tituloId);
        ((TextView)panel.findViewById(R.id.titulo)).setText(titulo);
    }

    /**
     * Get String from java Date with format "dd/MM/yyyy"
     * @param fecha java Date to be converted
     * @return String date with format "dd/MM/yyyy"
     */
    public String getStringFromDate(Date fecha){
        String fechaString = null;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        if(fecha!=null){
            fechaString = df.format(fecha);
        }

        return fechaString;
    }

    /**
     * Show an alert dialog and makes an action according to the response
     * @param titulo Title id of the alert dialog
     * @param mensaje Message id to show in the dialog
     * @param runnable Action to perform if the confirmation button is pressed
     */
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

    /**
     * Inflate a detalles_item layout to given panel
     * @param panelId panel id to add the detail layout
     * @return inflated View
     */
    public View inflarDetalles(int panelId){
        LinearLayout contenedor = act.findViewById(panelId);
        LayoutInflater inflater = LayoutInflater.from(act);

        View inflatedView = inflater.inflate(R.layout.detalles_item, contenedor, false);
        contenedor.addView(inflatedView);
        return inflatedView;
    }

    /**
     * Set a String value to a field of the form
     * @param panelId panel id with layout tv_et
     * @param valor String value to be inserted in the EditText
     */
    public void setValueToEditText(int panelId, String valor){
        ConstraintLayout panel = act.findViewById(panelId);
        ((EditText)panel.findViewById(R.id.editText)).setText(valor);
    }

    /**
     * Set a Long value to a field of the form
     * @param panelId panel id with layout tv_et
     * @param valor Long value to be inserted in the EditText
     */
    public void setValueToEditText(int panelId, Long valor){
        ConstraintLayout panel = act.findViewById(panelId);
        if(valor!=null)
            ((EditText)panel.findViewById(R.id.editText)).setText(valor+"");
        else
            ((EditText)panel.findViewById(R.id.editText)).setText("");
    }

    /**
     * Set Integer value to a field of the form
     * @param panelId panel id with layout tv_et
     * @param valor Integer value to be inserted in the EditText
     */
    public void setValueToEditText(int panelId, Integer valor){
        ConstraintLayout panel = act.findViewById(panelId);
        if(valor!=null)
            ((EditText)panel.findViewById(R.id.editText)).setText(valor+"");
        else
            ((EditText)panel.findViewById(R.id.editText)).setText("");
    }

    /**
     * Set spinner a value if existing in the options
     * @param panelId panel id with layout tv_spinner
     * @param opcionesId id of the spinner optiones
     * @param compareValue value to be set if existing in spinner options
     */
    public void setValueToSpinner(int panelId,int opcionesId,String compareValue){
        Spinner spinner = (Spinner)act.findViewById(panelId).findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(act, opcionesId, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            spinner.setSelection(spinnerPosition);
        }
    }

    /**
     * Set spinner a value if existing in the optiones
     * @param spinner Spinner where to set the value
     * @param opcionesId id of the spinner optiones
     * @param compareValue value to be set if existing in spinner options
     */
    public void setValueToSpinner(Spinner spinner, int opcionesId, String compareValue){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(act, opcionesId, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            spinner.setSelection(spinnerPosition);
        }
    }

    /**
     * Set two spinners the corresponding values if existing in the options
     * @param panelId panel id with tv_spinner2 layout
     * @param opcionesId first spinner options
     * @param compareValue value to be set in the first spinner
     * @param opcionesId2 second spinner options
     * @param compareValue2 value to be set in the second spinner
     */
    public void setValueToSpinner2(int panelId,int opcionesId,String compareValue, int opcionesId2, String compareValue2){
        Spinner spinner1 = (Spinner)act.findViewById(panelId).findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner)act.findViewById(panelId).findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(act, opcionesId, android.R.layout.simple_spinner_item);
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

    /**
     * Inflate a layout into a panel with tv_button layout
     * @param panelId panel id with tv_button layout
     * @param newLayoutId layout id to be inflated
     * @return inflated View
     */
    public View inflarEnUpdate(int panelId, int newLayoutId ){
        ConstraintLayout panel = act.findViewById(panelId);
        LinearLayout contenedor = (LinearLayout)panel.findViewById(R.id.contenedor);
        LayoutInflater inflater = LayoutInflater.from(act);

        View inflatedView = inflater.inflate(newLayoutId, contenedor, false);
        contenedor.addView(inflatedView);

        return inflatedView;
    }

    /**
     * Converts a bitmap into string to save it
     * @param bitmap Bitmap to be converted
     * @return converted bitmap String
     */
    public String bitmapToString(Bitmap bitmap){
        if(bitmap!=null){
            ByteArrayOutputStream baos=new  ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
            byte [] b=baos.toByteArray();
            String temp= Base64.encodeToString(b, Base64.DEFAULT);
            return temp;
        }
        return "";
    }
    
    /**
     * Converts a String into a bitmap
     * @param stringImg String to be converted
     * @return converted String Bitmap
     */
    public Bitmap stringToBitmap(String stringImg){

        if(stringImg!=null){
            try {
                byte [] encodeByte=Base64.decode(stringImg,Base64.DEFAULT);
                Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
                return bitmap;
            } catch(Exception e) {
                e.getMessage();
                return null;
            }
        }
        return null;
    }
}
