package org.example.pruebatecnica.dominio.transaction;

import org.example.pruebatecnica.dominio.product.Product;

import java.util.Date;

public class Transaction {
    private Long id;
    private TransactionType type;
    private Integer amount;
    private Date createDate;
    private Product product;
    private Product destinationProduct;

    public Transaction(TransactionType type, Integer amount, Product product, Product destinationProduct ) {
        this.type = type;
        this.amount = amount;
        this.product = product;
        this.destinationProduct = destinationProduct;

    }

    public Transaction() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
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

    public Product getDestinationProduct() {
        return destinationProduct;
    }

    public void setDestinationProduct(Product destinationProduct) {
        this.destinationProduct = destinationProduct;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type=" + type +
                ", amount=" + amount +
                ", createDate=" + createDate +
                ", product=" + product +
                ", destinationProduct=" + destinationProduct +
                '}';
    }
}
