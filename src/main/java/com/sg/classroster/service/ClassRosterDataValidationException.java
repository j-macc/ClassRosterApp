package com.sg.classroster.service;

public class ClassRosterDataValidationException extends Exception {

    public ClassRosterDataValidationException(String msg){
        super(msg);
    }

    public ClassRosterDataValidationException(String msg, Throwable cause){
        super(msg, cause);
    }

}
