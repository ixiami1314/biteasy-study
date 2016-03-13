package com.biteasy.study.dao;

/**
 * Created by xiaoxia on 16/3/13.
 */
public class DaoException extends Exception {
    private String message = null;

    public DaoException (String mess) {
        this.message = mess;
    }


    @Override
    public String getMessage() {
        return message;
    }

    protected void setErrorMessage(Exception errorMessage) {
        this.message = errorMessage.getMessage();
    }
}
