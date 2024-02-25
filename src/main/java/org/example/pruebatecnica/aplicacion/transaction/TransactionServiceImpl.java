package org.example.pruebatecnica.aplicacion.transaction;


import org.example.pruebatecnica.dominio.product.Product;
import org.example.pruebatecnica.dominio.transaction.Transaction;
import org.example.pruebatecnica.dominio.transaction.TransactionService;
import org.example.pruebatecnica.infraestructura.producto.ProductEntity;
import org.example.pruebatecnica.infraestructura.producto.ProductRepositoryImpl;
import org.example.pruebatecnica.infraestructura.transaction.TransactionEntity;
import org.example.pruebatecnica.infraestructura.transaction.TransactionEntityMapper;
import org.example.pruebatecnica.infraestructura.transaction.TransactionRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.example.pruebatecnica.infraestructura.producto.ProductEntityMapper.convertToEntityDomainProduct;
import static org.example.pruebatecnica.infraestructura.producto.ProductEntityMapper.convertToEntityProduct;
import static org.example.pruebatecnica.infraestructura.transaction.TransactionEntityMapper.convertToEntityDomainTransaction;
import static org.example.pruebatecnica.infraestructura.transaction.TransactionEntityMapper.convertToEntityTransaction;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepositoryImpl transactionRepositoryImpl;

    @Autowired
    private ProductRepositoryImpl productRepositoryImpl;

    @Override
    public Transaction createTransaction(Transaction transaction) {

        if (transaction.getProduct() == null) {
            throw new IllegalArgumentException("Producto origen no puede ser nulo");
        }

        Optional<ProductEntity> productOrigen = productRepositoryImpl.findById(transaction.getProduct().getId());

        if (productOrigen.isEmpty()) {
            throw new IllegalArgumentException("Producto origen no encontrado con ID: " + transaction.getProduct().getId());
        }

        Product origen = convertToEntityDomainProduct(productOrigen.get());

        switch (transaction.getType()) {
            case TRANSFERENCIA:
                Optional<ProductEntity> productDestination = productRepositoryImpl.findById(transaction.getDestinationProduct().getId());
                if (productDestination.isEmpty()) {
                    throw new IllegalArgumentException("Producto destino no encontrado con ID: " + transaction.getDestinationProduct().getId());
                }
                Product destino = convertToEntityDomainProduct(productDestination.get());
                origen.transfer(transaction.getAmount(), destino);
                productRepositoryImpl.save(convertToEntityProduct(destino));
                transaction.setDestinationProduct(destino);
                break;

            case CONSIGNACION:
                origen.deposit(transaction.getAmount());
                break;

            case RETIRO:
                origen.withdraw(transaction.getAmount());;
                break;

            default:
                throw new UnsupportedOperationException("Tipo de transacción no soportado: " + transaction.getType());
        }

        productRepositoryImpl.save(convertToEntityProduct(origen));
        transaction.setCreateDate(new Date());
        transaction.setProduct(origen);
        TransactionEntity createTransaction = transactionRepositoryImpl.save(convertToEntityTransaction(transaction));

        return convertToEntityDomainTransaction(createTransaction);
    }

    @Override
    public List<Transaction> getAllTransactionsByProductId(Long productId) {
        List<TransactionEntity> transactions =  transactionRepositoryImpl.findAllByProductId(productId);
        return transactions.stream()
                .map(TransactionEntityMapper::convertToEntityDomainTransaction)
                .collect(Collectors.toList());
    }

    @Override
    public Transaction getTransactionById(Long transactionId) {
        Optional<TransactionEntity> existingTransactionOptional = transactionRepositoryImpl.findById(transactionId);

        if (existingTransactionOptional.isEmpty()) {
            throw new IllegalArgumentException("Transaction no encontrado con ID: " + transactionId);
        }

        TransactionEntity existingTransaction = existingTransactionOptional.get();
        return convertToEntityDomainTransaction(existingTransaction);
    }
}