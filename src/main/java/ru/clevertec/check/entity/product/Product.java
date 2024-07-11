package ru.clevertec.check.entity.product;


public class Product {

    private Integer id;
    private String description;
    private Float price;
    private Integer quantityInStock;
    private Boolean wholesaleProduct;

    Product() {}

    public Integer getId() {
        return id;
    }

    void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Boolean getWholesaleProduct() {
        return wholesaleProduct;
    }

    void setWholesaleProduct(Boolean wholesaleProduct) {
        this.wholesaleProduct = wholesaleProduct;
    }

    public void toDisplay() {
        System.out.println(id);
        System.out.println(description);
        System.out.println(price);
        System.out.println(quantityInStock);
        System.out.println(wholesaleProduct);
    }
}
