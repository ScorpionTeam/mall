package com.scoprion.exception;

/**
 * @author by kunlun
 * @created on 2017/11/28.
 */
public class PayException extends RuntimeException {
    
    public PayException(String message) {
        super(message);
    }

    public PayException(String message, Throwable cause) {
        super(message, cause);
    }
}
