package com.MedicalHistory.exceptions;

public class DuplicateEmailIDException extends RuntimeException{
    private String msg;
    public DuplicateEmailIDException (String msg)
    {
        super(msg);
        this.msg = msg;
    }
}
