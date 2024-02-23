package org.example.pruebatecnica.aplicacion.product;

import org.example.pruebatecnica.dominio.client.Client;
import org.example.pruebatecnica.dominio.product.Product;
import org.example.pruebatecnica.dominio.product.ProductService;
import org.example.pruebatecnica.dominio.transaction.Transaction;
import org.example.pruebatecnica.infraestructura.producto.ProductRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepositoryImpl productRepositoryImpl;

    @Override
    public Product createProduct(Client clientId, String type, String status, Integer number, Integer balance, Boolean gmf) {
        List<Transaction> transactions = new ArrayList<>();
        return productRepositoryImpl.save(new Product(clientId, type, status, number, balance, gmf, transactions));
    }

    @Override
    public Product updateProduct(Long productId, String type, String status, Integer number, Integer balance, Boolean gmf) {
        Product existingProduct = productRepositoryImpl.findById(productId);
        if (existingProduct != null) {

            existingProduct.setType(type);
            existingProduct.setStatus(status);
            existingProduct.setNumber(number);
            existingProduct.setBalance(balance);
            existingProduct.setGmf(gmf);

            return productRepositoryImpl.save(existingProduct);
        }
        return null;
    }

    @Override
    public String deleteProduct(Long productId) {
        Product existingProduct = productRepositoryImpl.findById(productId);
        if (existingProduct != null) {
            productRepositoryImpl.delete(existingProduct);
            return "Cliente eliminado";
        }
        return "Cliente no existente";
    }

    @Override
    public List<Product> getAllProductsByClientId(Long clientId) {
        return productRepositoryImpl.findAllByClientId(clientId);
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepositoryImpl.findById(productId);
    }
}
