package org.example.pruebatecnica.aplicacion.transaction;

import org.example.pruebatecnica.dominio.transaction.Transaction;
import org.example.pruebatecnica.dominio.transaction.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Override
    public Transaction createTransaction(Long productId, String type, Integer amount) {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactionsByProductId(Long productId) {
        return null;
    }

    @Override
    public Transaction getTransactionById(Long transactionId) {
        return null;
    }
}
