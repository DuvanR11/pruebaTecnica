package org.example.pruebatecnica.dominio.product;

import java.util.List;

public interface ProductRepository {
    Product save(Product product);

    Product findById(Long productId);

    List<Product> findAllByClientId(Long clientId);

    void delete(Product product);
}
