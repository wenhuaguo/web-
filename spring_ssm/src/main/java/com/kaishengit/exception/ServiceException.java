package com.kaishengit.exception;

/**
 * Created by Acer on 2017/2/20.
 */
public class ServiceException extends RuntimeException {
    public ServiceException(String s) {
        super(s);
    }
    public ServiceException(){

    }
    public ServiceException(String message,Throwable th){
        super(message,th);
    }
    public ServiceException(Throwable th){
        super(th);
    }

}
