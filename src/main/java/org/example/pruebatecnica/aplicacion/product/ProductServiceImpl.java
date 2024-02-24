package org.example.pruebatecnica.aplicacion.product;

import org.example.pruebatecnica.dominio.client.Client;
import org.example.pruebatecnica.dominio.product.Product;
import org.example.pruebatecnica.dominio.product.ProductService;
import org.example.pruebatecnica.dominio.product.ProductStatus;
import org.example.pruebatecnica.dominio.product.ProductType;
import org.example.pruebatecnica.dominio.transaction.Transaction;
import org.example.pruebatecnica.infraestructura.producto.ProductRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepositoryImpl productRepositoryImpl;

    @Override
    public Product createProduct(Client clientId, ProductType type, Integer balance, Boolean gmf) {
        if (clientId == null) {
            throw new IllegalArgumentException("El producto se debe asociar a un cliente");
        }

        ProductStatus status = ProductStatus.ACTIVA;

        String generatedNumber = generateAccountNumber(type);
        List<Transaction> transactions = new ArrayList<>();
        return productRepositoryImpl.save(new Product(clientId, type, status, generatedNumber, balance, gmf, transactions));
    }

    private String generateAccountNumber(ProductType type) {
        String prefix = (type == ProductType.CUENTA_DE_AHORROS) ? "53" : "33";
        return prefix + generateRandomDigits(8);
    }

    private String generateRandomDigits(int numberOfDigits) {
        // Lógica para generar una cadena de dígitos numéricos aleatorios
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfDigits; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }



    @Override
    public Product updateProduct(Long productId, ProductType type, ProductStatus status, String number, Integer balance, Boolean gmf) {
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
            if (existingProduct.getBalance() == 0) {
                productRepositoryImpl.delete(existingProduct);
                return "Cuenta eliminada";
            } else {
                return "No se puede eliminar la cuenta porque tiene un saldo diferente de $0";
            }
        }

        return "Cuenta no existente";
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