package org.example.pruebatecnica.dominio.transaction;

import org.example.pruebatecnica.dominio.product.Product;

import java.util.List;

public interface TransactionRepository {
    Transaction save(Transaction transaction);

    Transaction findById(Long transactionId);

    List<Transaction> findAllByProductId(Long productId);

    void delete(Transaction transaction);
}
