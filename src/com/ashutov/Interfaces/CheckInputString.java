package com.ashutov.Interfaces;

import com.ashutov.Exceptions.WrongInputException;

public interface CheckInputString {
    boolean check(String string,String regex) throws WrongInputException;
}
