package org.example.pruebatecnica.dominio.transaction;

import org.example.pruebatecnica.dominio.product.Product;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Product productId, String type, Integer amount);

    List<Transaction> getAllTransactionsByProductId(Long productId);

    Transaction getTransactionById(Long transactionId);
}
