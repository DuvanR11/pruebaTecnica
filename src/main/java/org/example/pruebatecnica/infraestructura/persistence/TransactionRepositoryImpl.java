//package org.example.pruebatecnica.infraestructura.persistence;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import org.example.pruebatecnica.dominio.transaction.Transaction;
//import org.example.pruebatecnica.dominio.transaction.TransactionRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class TransactionRepositoryImpl implements TransactionRepository {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public Transaction save(Transaction transaction) {
//        // Implementación para guardar una transacción en la base de datos
//    }
//
//    @Override
//    public Transaction findById(Long transactionId) {
//        // Implementación para buscar una transacción por ID en la base de datos
//    }
//
//    @Override
//    public List<Transaction> findAllByProductId(Long productId) {
//        // Implementación para obtener todas las transacciones de un producto en la base de datos
//    }
//
//    @Override
//    public void delete(Transaction transaction) {
//        // Implementación para eliminar una transacción de la base de datos
//    }
//}
