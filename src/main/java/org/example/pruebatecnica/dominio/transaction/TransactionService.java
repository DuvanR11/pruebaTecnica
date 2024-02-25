package org.example.pruebatecnica.dominio.transaction;


import java.util.List;

public interface TransactionService {
      Transaction createTransaction(Transaction transaction);

      List<Transaction> getAllTransactionsByProductId(Long productId);

      Transaction getTransactionById(Long transactionId);
}
