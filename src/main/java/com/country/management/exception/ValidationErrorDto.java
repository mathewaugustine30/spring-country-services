package com.country.management.exception;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ValidationErrorDto {

    private List<ErrorDetails> fieldErrors = new ArrayList<ErrorDetails>();

    public ValidationErrorDto(){

    }

    public void addFieldError(String path, String message, Date date) {
        ErrorDetails error = new ErrorDetails(date,path, message);
        fieldErrors.add(error);
    }
}
