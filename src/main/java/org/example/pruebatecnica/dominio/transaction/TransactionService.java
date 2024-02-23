package org.example.pruebatecnica.dominio.transaction;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Long productId, String type, Integer amount);

    List<Transaction> getAllTransactionsByProductId(Long productId);

    Transaction getTransactionById(Long transactionId);
}
