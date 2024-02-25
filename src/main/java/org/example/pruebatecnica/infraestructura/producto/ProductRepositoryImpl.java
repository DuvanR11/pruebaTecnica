package org.example.pruebatecnica.infraestructura.producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepositoryImpl extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findAllByClientId(Long clientId);
    boolean existsByNumber(String number);

}