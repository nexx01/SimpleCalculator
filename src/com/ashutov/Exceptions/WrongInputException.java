package com.ashutov.Exceptions;

public class WrongInputException extends Exception {
    private String message;

    public WrongInputException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "WrongInputException{" +
                       "message='" + message + '\'' +
                       '}';
    }
}
