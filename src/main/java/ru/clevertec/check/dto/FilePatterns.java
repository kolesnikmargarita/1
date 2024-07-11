package ru.clevertec.check.dto;

public enum FilePatterns {
    DATA_TIME, PRODUCT, DISCOUNT_CARD, TOTAL;

    public String getTitle() {
        if (this == DATA_TIME) {
            return "Data;Time\n";
        }
        if (this == PRODUCT) {
            return "QTY;DESCRIPTION;PRICE;DISCOUNT;TOTAL\n";
        }
        if (this == DISCOUNT_CARD) {
            return "DISCOUNT CARD;DISCOUNT PERCENTAGE\n";
        }
        else {
            return "TOTAL PRICE;TOTAL DISCOUNT;TOTAL WITH DISCOUNT\n";
        }
    }
}
