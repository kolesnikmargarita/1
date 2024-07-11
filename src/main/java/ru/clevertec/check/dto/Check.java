package ru.clevertec.check.dto;

import ru.clevertec.check.entity.debitCard.DebitCard;
import ru.clevertec.check.entity.discountCard.DiscountCard;
import ru.clevertec.check.exception.NotEnoughMoneyException;
import ru.clevertec.check.exception.ProductIsAbsentException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Check {

    private LocalDate date;
    private LocalTime time;

    private List<ProductInCheck> products = new ArrayList<>();
    private DiscountCard card;

    private Float totalPrice;
    private Float totalDiscount;
    private Float totalWithDiscount;

    private DebitCard balance;

    public Check(List<ProductInCheck> products, DiscountCard discountCard, DebitCard debitCardBalance) throws NotEnoughMoneyException, ProductIsAbsentException {
        this.products = products;
        card = discountCard;
        balance = debitCardBalance;
        create();
    }

    public LocalDate getDate() {
        return date;
    }

    private void setDate() {
        date = LocalDate.now();
    }

    public LocalTime getTime() {
        return time;
    }

    private void setTime() {
        time = LocalTime.now();
    }

    public List<ProductInCheck> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInCheck> products) {
        this.products = products;
    }

    public DiscountCard getCard() {
        return card;
    }

    public void setCard(DiscountCard card) {
        this.card = card;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    private void setTotalPriceAndDiscount() {
        totalPrice = 0f;
        totalDiscount = 0f;
        for(ProductInCheck product : products) {
            totalPrice += product.getTotal();
            totalDiscount += product.getDiscount();
        }
    }

    public Float getTotalDiscount() {
        return totalDiscount;
    }

    public Float getTotalWithDiscount() {
        return totalWithDiscount;
    }

    private void setTotalWithDiscount() {
        totalWithDiscount = totalPrice - totalDiscount;
    }

    private void create() throws ProductIsAbsentException, NotEnoughMoneyException {
        toUniqueProductList();
        setDate();
        setTime();
        for (ProductInCheck product : products) {
            if (product != null) {
                product.setTotal();
                if (card != null){
                    product.setDiscount(card.getDiscount());
                } else {
                    product.setDiscount(0f);
                }
            } else {
                throw new ProductIsAbsentException("Product not found");
            }
        }
        setTotalPriceAndDiscount();
        setTotalWithDiscount();
        if(totalWithDiscount > balance.getBalance()) {
            throw new NotEnoughMoneyException("Not enough money on debit card");
        }
    }

    public void writeFile() {
        try (FileWriter writer = new FileWriter("result.csv")) {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
            StringBuilder fileText = new StringBuilder();
            fileText.append(FilePatterns.DATA_TIME.getTitle())
                            .append(date).append(";").append(dateFormat.format(time)).append("\n\n")
                            .append(FilePatterns.PRODUCT.getTitle());
            for (ProductInCheck product : products) {
                fileText.append(product.getQuantity()).append(";")
                        .append(product.getDescription()).append(";")
                        .append(String.format("%.2f$", product.getPrice())).append(";")
                        .append(String.format("%.2f$", product.getDiscount())).append(";")
                        .append(String.format("%.2f$", product.getTotal())).append("\n\n");
            }
            if(card != null){
                fileText.append(FilePatterns.DISCOUNT_CARD.getTitle())
                        .append(card.getNumber()).append(";")
                        .append(String.format("%.1f", card.getDiscount())).append("%\n\n");
            }
            fileText.append(FilePatterns.TOTAL.getTitle())
                    .append(String.format("%.2f$", totalPrice)).append(";")
                    .append(String.format("%.2f$", totalDiscount)).append(";")
                    .append(String.format("%.2f$", totalWithDiscount)).append("\n");
            writer.write(fileText.toString());
            System.out.println(fileText);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void toUniqueProductList() {
        ProductInCheck product;
        ProductInCheck productRepeat;
        for(int i = 0; i < products.size() - 1; i++) {
            for(int j = i + 1; j < products.size(); j++) {
                if(Objects.equals(products.get(i).getId(), products.get(j).getId())) {
                    product = products.get(i);
                    productRepeat = products.get(j);
                    product.setQuantity(product.getQuantity() + productRepeat.getQuantity());
                    products.remove(productRepeat);
                }
            }
        }
    }
}
