package org.example.pruebatecnica.presentacion.controller;

import org.example.pruebatecnica.dominio.transaction.Transaction;
import org.example.pruebatecnica.dominio.transaction.TransactionService;
import org.example.pruebatecnica.presentacion.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public Transaction createTransaction(@RequestBody TransactionDTO transactionDTO) {
        // Convertir DTO a entidad Transaction y llamar al servicio para crear una transacción
        return null;
    }
//
//    @GetMapping("/byProductId/{productId}")
//    public List<Transaction> getAllTransactionsByProductId(@PathVariable Long productId) {
//        // Llamar al servicio para obtener todas las transacciones de un producto
//    }
//
//    @GetMapping("/{transactionId}")
//    public Transaction getTransactionById(@PathVariable Long transactionId) {
//        // Llamar al servicio para obtener una transacción por ID
//    }
}
