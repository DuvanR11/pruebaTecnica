package org.example.pruebatecnica.presentacion.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.pruebatecnica.dominio.client.ClientTypeIdentification;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class ClientDTO {
    private Long id;
    private ClientTypeIdentification typeIdentification;
    private String identificationNumber;
    private String name;
    private String lastName;
    private String email;
    private Integer age;
    //private List<ProductDTO> products;
    private Date createDate;
    private Date updateDate;

}
