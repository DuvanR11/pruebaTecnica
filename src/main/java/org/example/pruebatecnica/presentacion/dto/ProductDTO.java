package org.example.pruebatecnica.presentacion.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.pruebatecnica.dominio.product.ProductStatus;
import org.example.pruebatecnica.dominio.product.ProductType;


import java.util.Date;
import java.util.List;
@Setter
@Getter
public class ProductDTO {
    private ProductType type;
    private ProductStatus status;
    private String number;
    private Integer balance;
    private Boolean gmf;
    private Date createDate;
    private Date updateDate;
    private ClientDTO client;
    private List<TransactionDTO> transactions;
}
