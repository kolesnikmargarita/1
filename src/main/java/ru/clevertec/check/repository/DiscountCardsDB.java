package ru.clevertec.check.repository;

import ru.clevertec.check.entity.discountCard.DiscountCard;
import ru.clevertec.check.entity.discountCard.DiscountCardBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DiscountCardsDB {

    private final String FILE_PATH = "./src/main/resources/discountCards.csv";
    private final List<DiscountCard> discountCards = new ArrayList<>();
    private static DiscountCardsDB instance;

    private DiscountCardsDB() throws FileNotFoundException {
        setDiscountCards();
    }

    public List<DiscountCard> getDiscountCards() {
        return discountCards;
    }

    private void setDiscountCards() throws FileNotFoundException {
        try (Stream<String> cardsStream = new BufferedReader(new FileReader(FILE_PATH)).lines()) {
            List<String> cardsStrings = cardsStream.skip(1).collect(Collectors.toList());
            for(String cards : cardsStrings) {
                String[] dataArr = cards.split(";");
                dataArr[2] = dataArr[2].replace(',','.');
                discountCards.add(
                        new DiscountCardBuilder()
                                .id(Integer.parseInt(dataArr[0]))
                                .number(Integer.parseInt(dataArr[1]))
                                .discount(Float.parseFloat(dataArr[2]))
                                .build()
                );
            }
        }
    }

    public static DiscountCardsDB getInstance() throws FileNotFoundException {
        if(instance == null) {
            instance = new DiscountCardsDB();
        }
        return instance;
    }
}
