package org.example.pruebatecnica.aplicacion.product;

import org.example.pruebatecnica.dominio.product.AccountNumberService;
import org.example.pruebatecnica.dominio.product.ProductType;
import org.example.pruebatecnica.infraestructura.producto.ProductRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AccountNumberServiceImpl implements AccountNumberService {

    @Autowired
    private ProductRepositoryImpl productRepositoryImpl;

    @Override
    public String generateAccountNumber(ProductType type) {
        String prefix = (type == ProductType.CUENTA_DE_AHORROS) ? "53" : "33";
        String numberProduct;
        do {
             numberProduct = prefix + generateRandomDigits();
        } while (productRepositoryImpl.existsByNumber(numberProduct));

        return numberProduct;
    }

    private String generateRandomDigits() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
