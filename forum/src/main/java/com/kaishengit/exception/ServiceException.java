package com.kaishengit.exception;

/**
 * Created by Acer on 2016/12/16.
 */
public class ServiceException extends RuntimeException {
    public ServiceException(){}
    public ServiceException(String message){
        super(message);
    }
    public ServiceException(Throwable th){
        super(th);
    }
    public ServiceException(String msg,Throwable th){
        super(msg,th);
    }
}
