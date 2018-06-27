package ar.edu.uns.cs.trabajosocialform.Utils;

import java.util.Date;

/**
 * Provides methods to validate the different fields used in the Form
 */
public class FieldsValidator {

    private Validator validator;

    public FieldsValidator(){
        validator = new Validator();
    }

    public boolean validateName(String text){
        return validator.validateNotNull(text) && validateShortString(text);
    }

    public boolean validateSurname(String text){
        return validator.validateNotNull(text) && validateShortString(text);
    }

    public boolean validateShortString(String text){
        if(!validator.validateNotNull(text) || !validator.validateMaxLength(text,45)){
            return false;
        }
        return true;
    }

    public boolean validateLongString(String text){
        if(!validator.validateNotNull(text) || !validator.validateMaxLength(text,70)){
            return false;
        }
        return true;
    }

    public boolean validateCuil(Long number){
        if(!validator.validateCuilFormat(number) || !validator.validateNotNullNumber(number)){
            return false;
        }

        return true;
    }

    public boolean validateDate(Date date){
        return date != null;
    }

    public boolean validateNumber(Integer number){
        return number != null;
    }
}
