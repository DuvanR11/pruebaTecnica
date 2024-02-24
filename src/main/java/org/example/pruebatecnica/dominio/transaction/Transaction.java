package org.example.pruebatecnica.dominio.transaction;

import org.example.pruebatecnica.dominio.product.Product;

import java.util.Date;

public class Transaction {
    private Long id;
    private String type;
    private Integer amount;
    private Date createDate;
    private Product product;

    public Transaction(String type, Integer amount, Product product) {
        this.type = type;
        this.amount = amount;
        this.product = product;
    }

    public Transaction() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
