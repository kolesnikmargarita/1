package ru.clevertec.check.entity.debitCard;

public class DebitCardBuilder {

    private final DebitCard debitCard = new DebitCard();

    public DebitCardBuilder balance(Float balance) {
        debitCard.setBalance(balance);
        return this;
    }

    public DebitCard build() {
        return debitCard;
    }
}
