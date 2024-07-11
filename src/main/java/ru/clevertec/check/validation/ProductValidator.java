package ru.clevertec.check.validation;

public class ProductValidator implements Validator{

    @Override
    public boolean isValid(String value) {
        return value.matches("[1-9][0-9]*-[1-9][0-9]*");
    }
}