package org.example.pruebatecnica.infraestructura.transaction;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepositoryImpl extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findAllByProductId(Long productId);
}
