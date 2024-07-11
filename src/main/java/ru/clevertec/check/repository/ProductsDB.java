package ru.clevertec.check.repository;

import ru.clevertec.check.entity.product.Product;
import ru.clevertec.check.entity.product.ProductBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductsDB {

    private final String FILE_PATH = "./src/main/resources/products.csv";
    private final List<Product> products = new ArrayList<>();
    private static ProductsDB instance;

    private ProductsDB() throws FileNotFoundException {
        setProducts();
    }

    public List<Product> getProducts() {
        return products;
    }

    private void setProducts() throws FileNotFoundException {
        try (Stream<String> productsStream = new BufferedReader(new FileReader(FILE_PATH)).lines()) {
            List<String> productsStrings = productsStream.skip(1).collect(Collectors.toList());
            for(String product : productsStrings) {
                String[] dataArr = product.split(";");
                dataArr[2] = dataArr[2].replace(',','.');
                products.add(
                        new ProductBuilder()
                                .id(Integer.parseInt(dataArr[0]))
                                .description(dataArr[1])
                                .price(Float.parseFloat(dataArr[2]))
                                .quantity(Integer.parseInt(dataArr[3]))
                                .wholesaleProduct(Boolean.parseBoolean(dataArr[4]))
                                .build()
                );
            }
        }
    }

    public static ProductsDB getInstance() throws FileNotFoundException {
        if(instance == null) {
            instance = new ProductsDB();
        }
        return instance;
    }
}
