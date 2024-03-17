package com.test.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ValidatorImpl implements Validator{
    Logger log = LogManager.getLogger(ValidatorImpl.class);

    public ValidatorImpl() {
        log.atLevel(Level.INFO);
    }
    @Override
    public boolean isNull(String input) {
        return input == null;
    }

    @Override
    public boolean isEmpty(String input) {
        String modifiedInput = cleanupWhiteSpaces(input);

        if (modifiedInput.isEmpty()) {
            log.info("{} -- input is empty", input);
            return false;
        }
        return true;
    }

    @Override
    public boolean isPersonalNumber(String personalnr) {
        // Clean up the string then take the last 10 characters and check with the Luhn algorithm
        personalnr = cleanupPersonalnr(personalnr);
        if (personalnr.length() != 10 && personalnr.length() != 12) {
            log.info("{} -- personal number is not valid, not appropiate length", personalnr);
            return false;
        }

        boolean valid = personalnr.length() == 12 ? luhn(personalnr.substring(2)) : luhn(personalnr);

        if (!valid) {
            log.info("{} -- personal number is not valid, checksum does not match", personalnr);
            return false;
        }
        return true;
    }

    @Override
    public boolean isVechicleRegistrationNumber(String vechiclenr) {
        vechiclenr = cleanupVehiclenr(vechiclenr);
        if (vechiclenr.length() != 6) {
            log.info("{} -- vehicle number is not valid, not appropiate length", vechiclenr);
            return false;
        }

        //Check if length is 6 and if the first three characters are letters and if the last the characters are integers
        if (!vechiclenr.substring(0, 3).matches("[a-zA-Z]+") && !vechiclenr.substring(3).matches("[0-9]+")) {
            log.info("{} -- vehicle number is most likely not a valid swedish vechicle number", vechiclenr);
            return false;
        }
        return true;
    }

    private boolean luhn(String luhnInput) {
        int checksum = Character.getNumericValue(luhnInput.charAt(luhnInput.length() - 1));
        int sum = 0;

        for (int i = 0; i < luhnInput.length() - 1; i++) {
            if (i % 2 == 0 ) {
                int value = Character.getNumericValue(luhnInput.charAt(i)) * 2;
                sum += value / 10 + value % 10;
            } else {
                sum += Character.getNumericValue(luhnInput.charAt(i));
            }
        }

        int step1 = sum % 10;
        int step2 = 10 - step1;
        int step3 = step2 % 10;

        return checksum - (10 - (sum % 10)) % 10 == 0;
    }
    private String cleanupPersonalnr(String text) {
        return text.replaceAll("(?<=[0-9])-(?=[0-9])", "");
    }

    private String cleanupVehiclenr(String text) {
        return text.replaceAll("(?<=[aA-zZ])-(?=[0-9])", "").replaceAll("\\s", "");
    }
    private String cleanupWhiteSpaces(String text) {
        return text.replaceAll("\\s", "");
    }
}
