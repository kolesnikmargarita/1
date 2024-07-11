package ru.clevertec.check.entity.discountCard;

public class DiscountCard {

    private Integer id;
    private Integer number;
    private Float discount;

    DiscountCard(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public void toDisplay() {
        System.out.println(id);
        System.out.println(number);
        System.out.println(discount);
    }
}
