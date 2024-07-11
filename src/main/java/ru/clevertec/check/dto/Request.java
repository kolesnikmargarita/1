package ru.clevertec.check.dto;

import ru.clevertec.check.entity.discountCard.DiscountCard;

import java.util.List;

public class Request {

    private List<String> products;
    private String discountCard;
    private String debitCardBalance;
}
