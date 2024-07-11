package ru.clevertec.check.validation;

public class DebitCardValidator implements Validator {

    @Override
    public boolean isValid(String value) {
        return value.matches("balanceDebitCard=-?[0-9]+(\\.[0-9]{2})?");
    }
}
