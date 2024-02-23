package org.example.pruebatecnica.dominio.product;

import org.example.pruebatecnica.dominio.client.Client;
import org.example.pruebatecnica.dominio.transaction.Transaction;

import java.util.List;

public interface ProductService {
    Product createProduct(Client clientId, String type, String status, Integer number, Integer balance, Boolean gmf);

    Product updateProduct(Long productId, String type, String status, Integer number, Integer balance, Boolean gmf);

    String deleteProduct(Long productId);

    List<Product> getAllProductsByClientId(Long clientId);

    Product getProductById(Long productId);
}
