package com.ashutov;

import com.ashutov.Exceptions.WrongInputException;
import com.ashutov.Interfaces.CheckInputString;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {


    public static void main(String[] args) throws WrongInputException {
        CalculatorClass calculator = new CalculatorClass();
        String retry="y";

        System.out.println("Введите выражение из арабских или римских цифр.");
        System.out.println("Цифры должны быть от 1 до 10.Римские цифры загравными буквами");
        while (retry.equals("y")){
            String inputString = input();
            calculator.getResult(inputString);
            System.out.println("Повторить? Нажмите y");
            retry=input();
        }
    }

    public static String input() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
