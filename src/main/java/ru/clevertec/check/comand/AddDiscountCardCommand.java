package ru.clevertec.check.comand;

import ru.clevertec.check.exception.DiscountCardNotFoundException;
import ru.clevertec.check.exception.ExceptionHandler;

public class AddDiscountCardCommand implements Command{

    RequestHandler requestHandler;

    public AddDiscountCardCommand(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @Override
    public Object execute(String discountCard) {
        try {
            return requestHandler.addDiscountCard(discountCard);
        } catch (DiscountCardNotFoundException e) {
            ExceptionHandler.writeExceptionInFile(e.getMessage());
        }
        return null;
    }
}
