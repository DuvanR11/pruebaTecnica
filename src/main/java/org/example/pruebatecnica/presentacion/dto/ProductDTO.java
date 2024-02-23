package org.example.pruebatecnica.presentacion.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.pruebatecnica.dominio.client.Client;
import org.example.pruebatecnica.dominio.transaction.Transaction;

import java.util.Date;
import java.util.List;
@Setter
@Getter
public class ProductDTO {
    private String type;
    private String Status;
    private Integer number;
    private Integer balance;
    private Boolean gmf;
    private Date createDate;
    private Date updateDate;
    private Client  client;
    private List<TransactionDTO> transactions;

}
