//package org.example.pruebatecnica.infraestructura.persistence;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import org.example.pruebatecnica.dominio.product.Product;
//import org.example.pruebatecnica.dominio.product.ProductRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class ProductRepositoryImpl implements ProductRepository {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public Product save(Product product) {
//        // Implementación para guardar un producto en la base de datos
//    }
//
//    @Override
//    public Product findById(Long productId) {
//        // Implementación para buscar un producto por ID en la base de datos
//    }
//
//    @Override
//    public List<Product> findAllByClientId(Long clientId) {
//        // Implementación para obtener todos los productos de un cliente en la base de datos
//    }
//
//    @Override
//    public void delete(Product product) {
//        // Implementación para eliminar un producto de la base de datos
//    }
//}