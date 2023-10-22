package com.femfy.femfyapi;

import com.femfy.femfyapi.exception.CustomException;
import org.springframework.expression.ParseException;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Utils {
    private Utils(){}

    public static Date parseDate(String date) throws CustomException {
        java.sql.Date  dateSql = null;
        if(date != null){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try{
                java.util.Date parsedDate = format.parse(date);
                dateSql = new java.sql.Date(parsedDate.getTime());
            } catch (ParseException | java.text.ParseException e) {
                throw new CustomException("Error al parsear la fecha");
            }
        }
        return dateSql;
    }
}
