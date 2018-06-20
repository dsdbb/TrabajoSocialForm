package ar.edu.uns.cs.trabajosocialform.Utils;

public class Validator {

    public boolean validateCuil(Long text){
        if(text!=null){
            return text.toString().matches("^\\d{10,11}$");
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
