package ru.clevertec.check.comand;

import ru.clevertec.check.dto.Check;
import ru.clevertec.check.dto.ProductInCheck;
import ru.clevertec.check.entity.debitCard.DebitCard;
import ru.clevertec.check.entity.discountCard.DiscountCard;
import ru.clevertec.check.entity.discountCard.DiscountCardBuilder;
import ru.clevertec.check.exception.NotEnoughMoneyException;
import ru.clevertec.check.exception.ProductIsAbsentException;

import java.util.ArrayList;

public class Developer {

    private final Command addProduct;
    private final Command addDiscountCard;
    private final Command addDebitCardBalance;

    private final ArrayList<ProductInCheck> productsInCheck = new ArrayList<>();
    private DiscountCard discountCardInCheck;
    private DebitCard debitCard;

    public Developer(Command addProduct, Command addDiscountCard, Command addDebitCardBalance) {
        this.addProduct = addProduct;
        this.addDiscountCard = addDiscountCard;
        this.addDebitCardBalance = addDebitCardBalance;
    }

    public void addProduct(String product) throws ProductIsAbsentException {
        ProductInCheck productInCheck = (ProductInCheck) addProduct.execute(product);
        if(productInCheck == null) {
            throw new ProductIsAbsentException("Product not found");
        }
        productsInCheck.add(productInCheck);
    }

    public void addDiscountCard(String discountCard) {
        discountCardInCheck = (DiscountCard)addDiscountCard.execute(discountCard);
        if(discountCardInCheck == null) {
            discountCardInCheck = new DiscountCardBuilder().discount(0f).build();
        }
    }

    public void addDebitCardBalance(String debitCardBalance) {
        debitCard = (DebitCard) addDebitCardBalance.execute(debitCardBalance);
    }

    public Check getCheck() throws NotEnoughMoneyException, ProductIsAbsentException {

        return new Check(
                productsInCheck,
                discountCardInCheck,
                debitCard
        );
    }

}
