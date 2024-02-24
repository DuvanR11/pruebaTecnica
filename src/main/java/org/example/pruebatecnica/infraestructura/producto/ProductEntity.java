package org.example.pruebatecnica.infraestructura.producto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.pruebatecnica.dominio.product.ProductStatus;
import org.example.pruebatecnica.dominio.product.ProductType;
import org.example.pruebatecnica.infraestructura.client.ClientEntity;
import org.example.pruebatecnica.infraestructura.transaction.TransactionEntity;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ProductType type;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    @Column(unique = true, nullable = false)
    private String number;
    @Column(name = "balance", nullable = false)
    private Integer balance;
    private Boolean gmf;
    @Column(name = "create_date")
    private Date createDate;
    @Column(name = "update_date")
    private Date updateDate;;
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private ClientEntity client;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactions;
}

