package ru.clevertec.check;

import ru.clevertec.check.dto.Check;
import ru.clevertec.check.exception.*;
import ru.clevertec.check.mapper.StringRequestMapper;

public class CheckRunner {

    public static void main(String[] args) {

        StringRequestMapper stringRequestMapper = new StringRequestMapper();
        try {
            Check check = stringRequestMapper.toCheck(args);
            check.writeFile();
        } catch (DebitCardBalanceNotFoundException |
                 ProductUnreadableException |
                 ProductsIsAbsentInRequestException |
                 NotEnoughMoneyException |
                 ProductIsAbsentException e) {
            ExceptionHandler.writeExceptionInFile(e.getMessage());
        }
    }
}
