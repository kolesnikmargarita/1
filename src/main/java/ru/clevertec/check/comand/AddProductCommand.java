package ru.clevertec.check.comand;

import ru.clevertec.check.exception.ExceptionHandler;
import ru.clevertec.check.exception.ProductIsAbsentException;

public class AddProductCommand implements Command {

    RequestHandler requestHandler;

    public AddProductCommand(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @Override
    public Object execute(String product) {
        try {
            return requestHandler.addProduct(product);
        } catch (ProductIsAbsentException e) {
            ExceptionHandler.writeExceptionInFile(e.getMessage());
        }
        return null;
    }
}
