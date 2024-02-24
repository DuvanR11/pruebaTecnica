package org.example.pruebatecnica.dominio.client;


import org.example.pruebatecnica.dominio.product.Product;

import java.util.Date;
import java.util.List;


public class Client {
    private Long id;
    private String typeIdentification;
    private String identificationNumber;
    private String name;
    private String lastName;
    private Integer age;
    private String email;
    private List<Product> products;
    private Date createDate;
    private Date updateDate;

    public Client(String typeIdentification, String identificationNumber, String name, String lastName, String email, Integer age, List<Product> products) {
        this.typeIdentification = typeIdentification;
        this.identificationNumber = identificationNumber;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.products = products;
    }

    public Client() {}

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeIdentification() {
        return typeIdentification;
    }

    public void setTypeIdentification(String typeIdentification) {
        this.typeIdentification = typeIdentification;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) {
        this.products = products;
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

}
