package ru.clevertec.check.validation;

public class DiscountCardValidator implements Validator{

    @Override
    public boolean isValid(String value) {
        return value.matches("discountCard=[0-9]{4}");
    }
}
