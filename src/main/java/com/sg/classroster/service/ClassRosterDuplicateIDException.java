package com.sg.classroster.service;

public class ClassRosterDuplicateIDException extends Exception {

    public ClassRosterDuplicateIDException(String msg){
        super(msg);
    }

    public ClassRosterDuplicateIDException(String msg, Throwable cause){
        super(msg, cause);
    }

}
