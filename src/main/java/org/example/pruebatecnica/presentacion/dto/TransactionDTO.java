package org.example.pruebatecnica.presentacion.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.pruebatecnica.dominio.transaction.TransactionType;


import java.util.Date;

@Setter
@Getter
public class TransactionDTO {
    private TransactionType type;
    private Integer amount;
    private Date createDate;
    private ProductDTO product;
    private ProductDTO destinationProduct;
}
