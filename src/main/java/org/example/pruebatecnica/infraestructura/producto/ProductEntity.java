package org.example.pruebatecnica.infraestructura.producto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.pruebatecnica.infraestructura.client.ClientEntity;
import org.example.pruebatecnica.infraestructura.transaction.TransactionEntity;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String status;
    private Integer number;
    private Integer balance;
    private Boolean gmf;
    @Column(name = "create_date")
    private Date createDate;
    @Column(name = "update_date")
    private Date updateDate;;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
    private ClientEntity client;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactions;

}

