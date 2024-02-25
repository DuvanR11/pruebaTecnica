package org.example.pruebatecnica.dominio.product;

import org.example.pruebatecnica.infraestructura.producto.ProductEntity;

import java.util.List;

public interface ProductRepository {
    ProductEntity save(ProductEntity product);

    Product findById(Long productId);

    List<Product> findAllByClientId(Long clientId);

    void delete(Product product);
}
