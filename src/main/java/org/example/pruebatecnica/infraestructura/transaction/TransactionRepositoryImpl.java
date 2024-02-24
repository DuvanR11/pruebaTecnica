package org.example.pruebatecnica.infraestructura.transaction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.pruebatecnica.dominio.client.Client;
import org.example.pruebatecnica.dominio.product.Product;
import org.example.pruebatecnica.dominio.transaction.Transaction;
import org.example.pruebatecnica.dominio.transaction.TransactionRepository;
import org.example.pruebatecnica.infraestructura.client.ClientEntity;
import org.example.pruebatecnica.infraestructura.producto.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    @PersistenceContext
    private EntityManager entityManager;


    private ProductEntity convertToProductEntity(Product product) {
        ProductEntity productEntity = new ProductEntity();

        productEntity.setId(product.getId());
        productEntity.setType(product.getType());
        productEntity.setStatus(product.getStatus());
        productEntity.setNumber(product.getNumber());
        productEntity.setBalance(product.getBalance());
        productEntity.setGmf(product.getGmf());
        productEntity.setCreateDate(product.getCreateDate());
        productEntity.setUpdateDate(product.getUpdateDate());

        return productEntity;
    }

    private TransactionEntity convertToEntity(Transaction transaction) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setId(transaction.getId());
        transactionEntity.setType(transaction.getType());
        transactionEntity.setAmount(transaction.getAmount());

        ProductEntity productEntity = convertToProductEntity(transaction.getProduct());
        transactionEntity.setProduct(productEntity);

        return transactionEntity;
    }

    private Product convertToProduct(ProductEntity productEntity) {
        Product product = new Product();

        product.setId(productEntity.getId());
        product.setType(productEntity.getType());
        product.setStatus(productEntity.getStatus());
        product.setNumber(productEntity.getNumber());
        product.setBalance(productEntity.getBalance());
        product.setGmf(productEntity.getGmf());
        product.setCreateDate(productEntity.getCreateDate());
        product.setUpdateDate(productEntity.getUpdateDate());


        // Puedes establecer los productos asociados si es necesario

        return product;
    }

    private Transaction convertToTransaction(TransactionEntity transactionEntity) {
        Transaction transaction = new Transaction();

        transaction.setId(transactionEntity.getId());
        transaction.setType(transactionEntity.getType());
        transaction.setAmount(transactionEntity.getAmount());

        Product product = convertToProduct(transactionEntity.getProduct());
        transaction.setProduct(product);
        return transaction;
    }

    @Override
    @Transactional
    public Transaction save(Transaction transaction) {
        TransactionEntity transactionEntity = convertToEntity(transaction);
        if (transaction.getId() == null) {
            System.out.println("Creando---");
            Date currentDate = new Date();
            transactionEntity.setCreateDate(currentDate);
            entityManager.persist(transactionEntity);
            transaction.setId(transactionEntity.getId());
        } else {
            System.out.println("Actualizando ---");
            entityManager.merge(transactionEntity);
        }

        return transaction;
    }

    @Override
    public Transaction findById(Long transactionId) {
        TransactionEntity transactionEntity = entityManager.find(TransactionEntity.class, transactionId);

        if (transactionEntity != null) {
            return convertToTransaction(transactionEntity);
        } else {
            return null;
        }
    }

    @Override
    public List<Transaction> findAllByProductId(Long productId) {
        return entityManager.createQuery(
                        "SELECT p FROM TransactionEntity p WHERE p.product.id = :productId", Transaction.class)
                .setParameter("productId", productId)
                .getResultList();
    }

    @Override
    public void delete(Transaction transaction) {

    }
}
