package com.test;

import com.test.validator.Validator;
import com.test.validator.ValidatorImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Validator validator = new ValidatorImpl();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!input.equalsIgnoreCase("exit")) {
            validator.isNull(input);
            validator.isPersonalNumber(input);
            validator.isVechicleRegistrationNumber(input);

            input = scanner.nextLine();
        }
    }
}