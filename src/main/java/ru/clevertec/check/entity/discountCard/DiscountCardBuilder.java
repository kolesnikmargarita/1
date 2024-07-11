package ru.clevertec.check.entity.discountCard;

public class DiscountCardBuilder {

    private final DiscountCard discountCard = new DiscountCard();

    public DiscountCardBuilder id(Integer id) {
        discountCard.setId(id);
        return this;
    }

    public  DiscountCardBuilder number(Integer number) {
        discountCard.setNumber(number);
        return this;
    }

    public  DiscountCardBuilder discount(Float discount) {
        discountCard.setDiscount(discount);
        return this;
    }

    public DiscountCard build() {
        return discountCard;
    }
}
