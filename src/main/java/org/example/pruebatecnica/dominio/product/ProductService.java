package org.example.pruebatecnica.dominio.product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);

    Product updateProduct(Long productId, Product product);

    void deleteProduct(Long productId);

    List<Product> getAllProductsByClientId(Long clientId);

    Product getProductById(Long productId);
}
