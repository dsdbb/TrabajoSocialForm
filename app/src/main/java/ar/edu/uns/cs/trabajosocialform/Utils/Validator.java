package ar.edu.uns.cs.trabajosocialform.Utils;

/**
 * Provides methods to validate different conditions of the data
 */
public class Validator {

    public boolean validateCuilFormat(Long text){
        if(text!=null){
            //return text.toString().matches("^\\d{10,11}$");
            return text.toString().matches("^\\d{7,11}$");

        }
        return false;
    }

    public boolean validateMaxLength(String text,int length){
        return text.length()<=length;
    }

    public boolean validateNotNull(String text){
        String textAux=text;
        if(text!=null){
            textAux = text.replaceAll(" ","");
        }
        if(text==null || textAux.equals("")){
            return false;
        }
        return true;
    }

    public boolean validateNotNullNumber(Long number){
        return number!=null;
    }
}
