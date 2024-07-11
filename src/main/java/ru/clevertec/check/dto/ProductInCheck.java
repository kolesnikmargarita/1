package ru.clevertec.check.dto;

public class ProductInCheck {

    private Integer id;
    private Integer quantity;
    private String description;
    private Float price;
    private Float total;
    private Float discount;
    private Boolean wholesaleProduct;

    public Boolean getWholesaleProduct() {
        return wholesaleProduct;
    }

    public void setWholesaleProduct(Boolean wholesaleProduct) {
        this.wholesaleProduct = wholesaleProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal() {
        total = price * quantity;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        if(wholesaleProduct && quantity >= 5) {
            this.discount = 10f * total / 100;
        } else if (discount != null) {
            this.discount = discount * total / 100;
        } else {
            this.discount = 0f;
        }
    }
}
