package com.test.validator;

public interface Validator {
    boolean isNull(String input);
    boolean isEmpty(String input);
    boolean isPersonalNumber(String personalnr);
    boolean isVechicleRegistrationNumber(String carnr);
}
