package org.example.pruebatecnica.infraestructura.transaction;

import org.example.pruebatecnica.dominio.product.Product;
import org.example.pruebatecnica.dominio.transaction.Transaction;
import org.example.pruebatecnica.infraestructura.producto.ProductEntity;

import static org.example.pruebatecnica.infraestructura.producto.ProductEntityMapper.convertToEntityDomainProduct;
import static org.example.pruebatecnica.infraestructura.producto.ProductEntityMapper.convertToEntityProduct;

public class TransactionEntityMapper {

    public static TransactionEntity convertToEntityTransaction(Transaction transaction) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setId(transaction.getId());
        transactionEntity.setType(transaction.getType());
        transactionEntity.setAmount(transaction.getAmount());
        transactionEntity.setCreateDate(transaction.getCreateDate());
        ProductEntity productEntity = convertToEntityProduct(transaction.getProduct());
        transactionEntity.setProduct(productEntity);
        if(transaction.getDestinationProduct() != null) {
            System.out.println("No es nulo");
            ProductEntity destinationProduct = convertToEntityProduct(transaction.getDestinationProduct());
            transactionEntity.setDestinationProduct(destinationProduct);
        }

        return transactionEntity;
    }

    public static Transaction  convertToEntityDomainTransaction(TransactionEntity transactionEntity) {
        Transaction transaction = new Transaction();

        transaction.setId(transactionEntity.getId());
        transaction.setType(transactionEntity.getType());
        transaction.setAmount(transactionEntity.getAmount());
        transaction.setCreateDate(transactionEntity.getCreateDate());
        Product product = convertToEntityDomainProduct(transactionEntity.getProduct());
        transaction.setProduct(product);
        if(transactionEntity.getDestinationProduct() != null) {
            System.out.println("tampoco es nulo");
            Product destinationProduct = convertToEntityDomainProduct(transactionEntity.getDestinationProduct());
            transaction.setDestinationProduct(destinationProduct);
        }

        return transaction;
    }
}
