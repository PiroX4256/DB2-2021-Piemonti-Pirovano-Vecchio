package it.polimi.db2.db2project.model;

import java.util.Date;

public class ProductDTO {
    private Long id;
    private final String productName;
    private final String productImage;
    private final Date date;

    public ProductDTO(String productName, String productImage, Date date) {
        this.productName = productName;
        this.productImage = productImage;
        this.date = date;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public Date getDate() {
        return date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
