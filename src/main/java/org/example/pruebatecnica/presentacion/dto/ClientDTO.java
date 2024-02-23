package org.example.pruebatecnica.presentacion.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.pruebatecnica.dominio.product.Product;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class ClientDTO {
    private String typeIdentification;
    private String identificationNumber;
    private String name;
    private String lastName;
    private String email;
    private List<ProductDTO> products;
    private Date createDate;
    private Date updateDate;
    // private List<ProductDTO> products;
    // Constructores, getters y setters
}
