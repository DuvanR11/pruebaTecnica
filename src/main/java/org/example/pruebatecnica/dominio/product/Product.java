package org.example.pruebatecnica.dominio.product;

import org.example.pruebatecnica.dominio.client.Client;
import org.example.pruebatecnica.dominio.transaction.Transaction;

import java.util.Date;
import java.util.List;

public class Product {
    private Long id;
    private String type;
    private String status;
    private Integer number;
    private Integer balance;
    private Boolean gmf;
    private Date createDate;
    private Date updateDate;
    private Client client;
    private List<Transaction> transactions;


    public Product(Client client, String type, String status, Integer number, Integer balance, Boolean gmf, List<Transaction> transactions) {
        this.type = type;
        this.status = status;
        this.number = number;
        this.balance = balance;
        this.gmf = gmf;
        this.client = client;
        this.transactions = transactions;
    }

    public Product() {}

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Boolean getGmf() {
        return gmf;
    }

    public void setGmf(Boolean gmf) {
        this.gmf = gmf;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
