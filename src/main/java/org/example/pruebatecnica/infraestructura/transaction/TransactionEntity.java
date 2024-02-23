package org.example.pruebatecnica.infraestructura.transaction;

import jakarta.persistence.*;
import org.example.pruebatecnica.infraestructura.producto.ProductEntity;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private Integer amount;
    @Column(nullable = false)
    private Date createDate;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

}

