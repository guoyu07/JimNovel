package com.jim.novel.exception;

/**
 * ${DESCRIPTION}
 *
 * @author run
 * @create 2017-04-17
 **/
public class MyRuntimeException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MyRuntimeException(){
        super();
        throw new RuntimeException();
    }

}