package org.example.pruebatecnica.infraestructura.client;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.pruebatecnica.infraestructura.producto.ProductEntity;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_identification")
    private String typeIdentification;

    @Column(name = "identification_number")
    private String identificationNumber;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<ProductEntity> products;

}

