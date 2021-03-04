package com.savin.matrix;

/**
 * Exception class for Matrices
 *
 * @author Mikhail Savin
 */
public class MatrixException extends Exception {

    /**
     * Constructor of the exception with message
     *
     * @param message additional info on exception
     */
    public MatrixException(String message) {
        super(message);
    }

    /**
     * Constructor of the exception with Throwable object and message
     *
     * @param message additional info on exception
     * @param e the exception itself
     */
    public MatrixException(String message, Throwable e) {
        super(message, e);
    }
}
