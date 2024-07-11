package ru.clevertec.check.exception;

public class ProductsIsAbsentInRequestException extends Exception {

    public ProductsIsAbsentInRequestException(String message) {
        super(message);
    }
}
