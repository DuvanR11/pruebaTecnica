package org.example.pruebatecnica.aplicacion.transaction;

import org.example.pruebatecnica.dominio.product.Product;
import org.example.pruebatecnica.dominio.transaction.Transaction;
import org.example.pruebatecnica.dominio.transaction.TransactionService;
import org.example.pruebatecnica.infraestructura.producto.ProductRepositoryImpl;
import org.example.pruebatecnica.infraestructura.transaction.TransactionRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepositoryImpl transactionRepositoryImpl;

    @Override
    public Transaction createTransaction(Product productId, String type, Integer amount) {
        return transactionRepositoryImpl.save(new Transaction(type, amount, productId));
    }

    @Override
    public List<Transaction> getAllTransactionsByProductId(Long productId) {
        return transactionRepositoryImpl.findAllByProductId(productId);
    }

    @Override
    public Transaction getTransactionById(Long transactionId) {
        return transactionRepositoryImpl.findById(transactionId);
    }
}