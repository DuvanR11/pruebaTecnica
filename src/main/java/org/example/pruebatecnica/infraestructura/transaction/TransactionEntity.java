package org.example.pruebatecnica.infraestructura.transaction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.pruebatecnica.dominio.transaction.TransactionType;
import org.example.pruebatecnica.infraestructura.producto.ProductEntity;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @Column(nullable = false)
    private Integer amount;
    @Column(nullable = false)
    private Date createDate;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private ProductEntity product;
    @ManyToOne
    @JoinColumn(name = "destination_product_id")
    private ProductEntity destinationProduct;
}

