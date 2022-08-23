package com.MedicalHistory.exceptions;

public class DuplicateEmailIDException extends RuntimeException {
    private final String msg;

    public DuplicateEmailIDException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
