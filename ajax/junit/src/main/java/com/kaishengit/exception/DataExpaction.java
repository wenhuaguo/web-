package com.kaishengit.exception;

/**
 * Created by Acer on 2016/11/30.
 */
public class DataExpaction extends RuntimeException {
    public DataExpaction(){

    }

    public DataExpaction(String message){
        super(message);
    }
    public DataExpaction(Throwable th){
        super(th);
    }
    public DataExpaction(String message, Throwable th){
        super(message,th);
    }
}
