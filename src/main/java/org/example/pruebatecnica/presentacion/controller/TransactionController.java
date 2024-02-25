package org.example.pruebatecnica.presentacion.controller;

import org.example.pruebatecnica.aplicacion.transaction.TransactionServiceImpl;
import org.example.pruebatecnica.dominio.transaction.Transaction;
import org.example.pruebatecnica.presentacion.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.pruebatecnica.aplicacion.transaction.TransactionMapper.convertDtoToDomainTransaction;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionServiceImpl transactionServiceImpl;

    @Autowired
    public TransactionController(TransactionServiceImpl transactionServiceImpl) {
        this.transactionServiceImpl = transactionServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Object> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        try {
            Transaction transaction = convertDtoToDomainTransaction(transactionDTO);
            Transaction createdTransaction = transactionServiceImpl.createTransaction(transaction);
            return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST);
        }
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
