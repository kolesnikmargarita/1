package ru.clevertec.check.comand;

import ru.clevertec.check.dto.RequestProduct;
import ru.clevertec.check.entity.debitCard.DebitCard;
import ru.clevertec.check.entity.debitCard.DebitCardBuilder;
import ru.clevertec.check.entity.discountCard.DiscountCard;
import ru.clevertec.check.entity.discountCard.DiscountCardBuilder;
import ru.clevertec.check.entity.product.Product;
import ru.clevertec.check.exception.DiscountCardNotFoundException;
import ru.clevertec.check.exception.NotEnoughMoneyException;
import ru.clevertec.check.exception.ProductIsAbsentException;
import ru.clevertec.check.mapper.RequestProductMapper;
import ru.clevertec.check.repository.DiscountCardsDB;
import ru.clevertec.check.repository.ProductsDB;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.function.Predicate;

public class RequestHandler {

    private List<Product> productsInDB;
    private List<DiscountCard> discountCardsInDB;
    private final RequestProductMapper requestProductMapper = new RequestProductMapper();

    public RequestHandler() {
        try {
            productsInDB = ProductsDB.getInstance().getProducts();
            discountCardsInDB = DiscountCardsDB.getInstance().getDiscountCards();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public Object addProduct(String requestProductString) throws ProductIsAbsentException {

        RequestProduct requestProduct = getRequestProduct(requestProductString);
        for(Product productInDB : productsInDB) {
            if(productInDB.getId() == requestProduct.getId() && productInDB.getQuantityInStock() >= requestProduct.getQuantity()) {
                return requestProductMapper.toProductInCheck(requestProduct, productInDB);
            }
        }
        throw new ProductIsAbsentException("There is not the product in stock");
    }

    private static RequestProduct getRequestProduct(String requestProductString) {
        Function<String, RequestProduct> requestProductConverter = productDateString -> {
            RequestProduct requestProduct = new RequestProduct();
            String[] productRequestDate = productDateString.split("-");
            int id = Integer.parseInt(productRequestDate[0]);
            int quantity = Integer.parseInt(productRequestDate[1]);
            requestProduct.setId(id);
            requestProduct.setQuantity(quantity);
            return requestProduct;
        };
        
        return requestProductConverter.apply(requestProductString);
    }

    public Object addDiscountCard(String requestDiscountCardString) throws DiscountCardNotFoundException {
        Function<String, Integer> requestDiscountCardConverter = discuntCardString -> {
            String discountCardNumberString = discuntCardString.substring(discuntCardString.indexOf('=') + 1);
            return Integer.parseInt(discountCardNumberString);
        };

        int requestDiscountCardNumber = requestDiscountCardConverter.apply(requestDiscountCardString);
        for(DiscountCard discountCardInDB : discountCardsInDB) {
            if(discountCardInDB.getNumber() == requestDiscountCardNumber) {
                return
                        new DiscountCardBuilder()
                                .id(discountCardInDB.getId())
                                .number(discountCardInDB.getNumber())
                                .discount(discountCardInDB.getDiscount())
                                .build();
            }
        }
        throw new DiscountCardNotFoundException("Discount card not found");
    }

    public Object addDebitCardBalance(String requestBalanceString) throws NotEnoughMoneyException {
        Function<String, Float> requestDebitCardBalanceConverter = debitCardBalanceString -> {
            String balanceString = debitCardBalanceString.substring(debitCardBalanceString.indexOf('=') + 1);
            return Float.parseFloat(balanceString);
        };

        float requestDebitCardBalance = requestDebitCardBalanceConverter.apply(requestBalanceString);
        return new DebitCardBuilder().balance(requestDebitCardBalance).build();
    }
}
