package com.test.validator;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
    Validator validator;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        this.validator = new ValidatorImpl();
    }

    @org.junit.jupiter.api.Test
    void isNull() {
        assertTrue(validator.isNull(null));
    }

    @org.junit.jupiter.api.Test
    void isPersonalNumber() {
        assertTrue(validator.isPersonalNumber("197802022389"));
        assertTrue(validator.isPersonalNumber("198204112380"));

        assertTrue(validator.isPersonalNumber("19820411-2380"));
        assertTrue(validator.isPersonalNumber("19780202-2389"));
    }

    @org.junit.jupiter.api.Test
    void isVechicleRegistrationNumber() {
        assertTrue(validator.isVechicleRegistrationNumber("abc-123"));
        assertTrue(validator.isVechicleRegistrationNumber("ABC-123"));
        assertTrue(validator.isVechicleRegistrationNumber("abc 123"));
        assertTrue(validator.isVechicleRegistrationNumber(" abc-123 "));
        assertTrue(validator.isVechicleRegistrationNumber(" abc 123  "));
    }
}