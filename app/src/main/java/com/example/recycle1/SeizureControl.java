package com.example.recycle1;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SeizureControl {

    private static Matcher matcher;

    public static boolean isString(String text) {



        if (text.matches("^[a-zA-Z]+$")) {

            return true;

        }

        return false;

    }

    public static boolean isNull(String text){

        if(text.equals("")  || text == null){

            return true; //null

        }

        return false ;//n'est pas vide

    }

    public static boolean isName(String text) {



        if (text.matches("^[a-z A-Z]+$") ) {

            return true;

        }

        return false;

    }

    public static boolean DateNullCS(String date){

        if(date.equals("") ){

            return true ;

        }

        return false;

    }

    public static boolean adresse(String text) {



        if (text.matches("^[A-Z a-z 0-9]+$")) {

            return true;

        }

        return false;

    }

    public static boolean iscin(String text) {



        if (text.matches("^[0-9]+$")&& text.length()== 10) {

            return true;

        } else {

            return false;

        }

    }

    public static boolean isTel(String text) {



        if (text.matches("^[0-9]+$")&& text.length()==8) {

            return true;

        } else {

            return false;

        }

    }





    private static final String pwd=  "^[A-Za-z0-9]+$";

    private static Pattern pattern1 = Pattern.compile(pwd);


    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean valiemail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    public static boolean validPasswor(final String hex) {

        matcher = pattern1.matcher(hex);

        return matcher.matches();

    }
}
