package ru.clevertec.check.entity.product;

public class ProductBuilder {

    private final Product product = new Product();

    public ProductBuilder id (Integer id) {
        product.setId(id);
        return this;
    }

    public ProductBuilder description (String description) {
        product.setDescription(description);
        return this;
    }

    public ProductBuilder price (Float price) {
        product.setPrice(price);
        return this;
    }

    public ProductBuilder quantity (Integer quantity) {
        product.setQuantityInStock(quantity);
        return this;
    }

    public ProductBuilder wholesaleProduct (Boolean wholesaleProduct) {
        product.setWholesaleProduct(wholesaleProduct);
        return this;
    }

    public Product build() {
        return product;
    }
}
