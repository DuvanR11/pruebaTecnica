package org.example.pruebatecnica.presentacion.controller;

import org.example.pruebatecnica.aplicacion.transaction.TransactionServiceImpl;
import org.example.pruebatecnica.dominio.transaction.Transaction;
import org.example.pruebatecnica.dominio.transaction.TransactionService;
import org.example.pruebatecnica.presentacion.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionServiceImpl transactionServiceImpl;

    @Autowired
    public TransactionController(TransactionServiceImpl transactionServiceImpl) {
        this.transactionServiceImpl = transactionServiceImpl;
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody TransactionDTO transactionDTO) {
        return transactionServiceImpl.createTransaction(transactionDTO.getProduct(), transactionDTO.getType(), transactionDTO.getAmount());
    }

    @GetMapping("/byProductId/{productId}")
    public List<Transaction> getAllTransactionsByProductId(@PathVariable Long productId) {
        return transactionServiceImpl.getAllTransactionsByProductId(productId);
    }

    @GetMapping("/{transactionId}")
    public Transaction getTransactionById(@PathVariable Long transactionId) {
        return transactionServiceImpl.getTransactionById(transactionId);
    }
}
