package ru.clevertec.check.comand;

import ru.clevertec.check.exception.ExceptionHandler;
import ru.clevertec.check.exception.NotEnoughMoneyException;

public class AddDebitCardBalanceCommand implements Command {

    RequestHandler requestHandler;

    public AddDebitCardBalanceCommand(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @Override
    public Object execute(String balance) {
        try {
            return requestHandler.addDebitCardBalance(balance);
        } catch (NotEnoughMoneyException e) {
            ExceptionHandler.writeExceptionInFile(e.getMessage());
        }
        return null;
    }
}
