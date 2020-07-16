package com.ashutov;

import com.ashutov.Interfaces.CheckInputString;
import com.ashutov.Numbers.ConvertingNumbers;
import com.ashutov.Exceptions.WrongInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorClass {
    /*Регулярное выражение для проверки вводимой строки арабскими числами*/
    private String regexExpressionArabNumber = "^\\s*([1-9]{1,1}|10)\\s*[+*-\\/]\\s*([1-9]{1,1}|10)\\s*$";
    /*Регулярное выражение для проверки вводимой строки римскими числами*/
    private String regexExpressionRomsNumber = "^\\s*([I]{1,3}|[I][V]|[V]|[V][I]{1,3}|[I][X]|[X])\\s*[+*-\\/]\\s*([I]{1,3}|[I][V]|[V]|[V][I]{1,3}|[I][X]|[X])\\s*$";
    /*Регулярное выражение для преобразования строки в arraylist с элементами выражения*/
    private String regexForSplit = "([IVX]+)|([0-9]+)|(\\+|\\-|\\*|\\/)";

    private List<String> expression = new ArrayList<>();
    private String operation;
    private Integer result;


    public void getResult(String inputString) throws WrongInputException {
        CheckInputString checkInputString = (inputExpression, regex) ->
                                                    (Pattern.compile(regex).matcher(inputExpression).lookingAt());
        /*Проверяем какому регулярному выражению соответвует введеная строка и вызываем
         * соответсвующий сетод калькулятора. Если введеная строка не соответствует
         * ни одному из выражений выбрасываем исключение и завершаем программу */
        if (checkInputString.check(inputString, regexExpressionArabNumber)) {
            getArabicResult(inputString, regexForSplit);
        } else if (checkInputString.check(inputString, regexExpressionRomsNumber)) {
            getRomanResult(inputString, regexForSplit);
        } else {
            throw new WrongInputException("WrongInput");
        }
    }


    /*Метод преобразует строки в arraylist с элементами выражения*/
    private void split(String inputString, String regexForSplit) {
        Pattern pattern = Pattern.compile(regexForSplit);
        Matcher matcher = pattern.matcher(inputString);
        while (matcher.find()) {
            expression.add(matcher.group());
        }
    }


    /*Метод для получения результата, если введены Римские числа*/
    private void getArabicResult(String inputString, String regexForSplit) throws WrongInputException {
        split(inputString, regexForSplit);
        result = getOperation(Integer.valueOf(expression.get(0)), Integer.valueOf(expression.get(2)));
        System.out.println(inputString + " = " + result);
    }

    /*Прверяем какую арифметическую операцию пользователь ввел с консоли.
     * Если числа не делятся друг на друга в пределах целых чисел, выбрасываем исключение */
    private int getOperation(Integer number1, Integer number2) throws WrongInputException {
        operation = expression.get(1);
        switch (operation) {
            case "*":
                return number1 * number2;
            case "/":
                if (number1 % number2 != 0) {
                    throw new WrongInputException("Невозможно выполнить операцию деления" +
                                                          ", чтобы получилось целое число");
                }
                return number1 / number2;
            case "+":
                return number1 + number2;
            case "-":
                return number1 - number2;
        }
        return -1;
    }


    /*Метод для получения результата, если введены Римские числа.*/
    private void getRomanResult(String inputString, String regexForSplit) throws WrongInputException {
        split(inputString, regexForSplit);
        result = getOperation(ConvertingNumbers.romanToArabic(expression.get(0)), ConvertingNumbers.romanToArabic(expression.get(2)));
        if (result>0){
            System.out.println(inputString + " = " + ConvertingNumbers.arabicToRoman(result));
        } else {
        System.out.println(inputString + " = "+"в Риме не было нуля и  отрицательных чисел");
    }
}
}






