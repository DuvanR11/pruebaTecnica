package org.example.pruebatecnica.presentacion.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.pruebatecnica.dominio.product.Product;

import java.util.Date;

@Setter
@Getter
public class TransactionDTO {
    private String type;
    private Integer amount;
    private Date createDate;
    private Product product;
}
