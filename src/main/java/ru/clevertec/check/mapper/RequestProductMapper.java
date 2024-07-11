package ru.clevertec.check.mapper;

import ru.clevertec.check.dto.ProductInCheck;
import ru.clevertec.check.dto.RequestProduct;
import ru.clevertec.check.entity.product.Product;

public class RequestProductMapper {

    public ProductInCheck toProductInCheck(RequestProduct requestProduct, Product productInDb) {

        ProductInCheck productInCheck = new ProductInCheck();

        productInCheck.setId(productInDb.getId());
        productInCheck.setDescription(productInDb.getDescription());
        productInCheck.setPrice(productInDb.getPrice());
        productInCheck.setQuantity(requestProduct.getQuantity());
        productInCheck.setWholesaleProduct(productInDb.getWholesaleProduct());

        return productInCheck;
    }
}
