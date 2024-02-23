package org.example.pruebatecnica.dominio.transaction;

import org.example.pruebatecnica.dominio.product.Product;

import java.util.Date;

public class Transaction {
    private Long id;
    private String type;
    private Integer amount;
    private Date createDate;
    private Product product;
}
