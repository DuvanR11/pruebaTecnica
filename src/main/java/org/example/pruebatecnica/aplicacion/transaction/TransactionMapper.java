package org.example.pruebatecnica.aplicacion.transaction;

import org.example.pruebatecnica.dominio.product.Product;
import org.example.pruebatecnica.dominio.transaction.Transaction;
import org.example.pruebatecnica.presentacion.dto.TransactionDTO;

import static org.example.pruebatecnica.aplicacion.product.ProductMapper.convertDtoToDomainProduct;

public class TransactionMapper {
    public static Transaction convertDtoToDomainTransaction(TransactionDTO transactionDTO) {
        Product product = convertDtoToDomainProduct(transactionDTO.getProduct());
        Product transaction = convertDtoToDomainProduct(transactionDTO.getDestinationProduct());
        return new Transaction(
                transactionDTO.getType(),
                transactionDTO.getAmount(),
                product,
                transaction
        );
    }
}
