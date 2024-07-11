package ru.clevertec.check.mapper;

import ru.clevertec.check.comand.*;
import ru.clevertec.check.dto.Check;
import ru.clevertec.check.exception.*;
import ru.clevertec.check.validation.DebitCardValidator;
import ru.clevertec.check.validation.DiscountCardValidator;
import ru.clevertec.check.validation.ProductValidator;

public class StringRequestMapper {

    private final DebitCardValidator debitCardValidator = new DebitCardValidator();
    private final DiscountCardValidator discountCardValidator = new DiscountCardValidator();
    private final ProductValidator productValidator = new ProductValidator();

    public Check toCheck(String[] args) throws DebitCardBalanceNotFoundException, ProductUnreadableException, ProductsIsAbsentInRequestException, NotEnoughMoneyException, ProductIsAbsentException {
        RequestHandler requestHandler = new RequestHandler();

        Developer developer = new Developer(
                new AddProductCommand(requestHandler),
                new AddDiscountCardCommand(requestHandler),
                new AddDebitCardBalanceCommand(requestHandler)
        );

        int i = args.length - 1;
        if (debitCardValidator.isValid(args[i])) {
            developer.addDebitCardBalance(args[i--]);
        } else {
            throw new DebitCardBalanceNotFoundException("Balance of debit card not found");
        }
        if (discountCardValidator.isValid(args[i])) {
            developer.addDiscountCard(args[i--]);
        }
        if( i == -1) {
            throw new ProductsIsAbsentInRequestException("Products is absent in the request");
        }
        for(; i >= 0; i--) {
            if(productValidator.isValid(args[i])) {
                developer.addProduct(args[i]);
            } else {
                throw new ProductUnreadableException("Unreadable product");
            }
        }

        return developer.getCheck();
    }

}
