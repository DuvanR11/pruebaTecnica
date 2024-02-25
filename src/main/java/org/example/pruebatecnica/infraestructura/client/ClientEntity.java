package org.example.pruebatecnica.infraestructura.client;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.example.pruebatecnica.dominio.client.ClientTypeIdentification;
import org.example.pruebatecnica.infraestructura.producto.ProductEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_identification")
    private ClientTypeIdentification typeIdentification;

    @Column(name = "identification_number")
    private String identificationNumber;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    private String email;
    private Integer age;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ProductEntity> products;

    public ClientEntity(Long id, ClientTypeIdentification typeIdentification, String identificationNumber, String name, String lastName, String email, Integer age) {
        this.id = id;
        this.typeIdentification = typeIdentification;
        this.identificationNumber = identificationNumber;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }
}

