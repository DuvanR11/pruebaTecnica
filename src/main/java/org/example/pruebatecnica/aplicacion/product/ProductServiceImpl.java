package org.example.pruebatecnica.aplicacion.product;

import org.example.pruebatecnica.dominio.client.Client;
import org.example.pruebatecnica.dominio.product.*;
import org.example.pruebatecnica.infraestructura.client.ClientEntity;
import org.example.pruebatecnica.infraestructura.client.ClientRepositoryImpl;
import org.example.pruebatecnica.infraestructura.producto.ProductEntity;
import org.example.pruebatecnica.infraestructura.producto.ProductEntityMapper;
import org.example.pruebatecnica.infraestructura.producto.ProductRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.example.pruebatecnica.infraestructura.client.ClientEntityMapper.convertToEntityDomainClient;
import static org.example.pruebatecnica.infraestructura.producto.ProductEntityMapper.*;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepositoryImpl productRepositoryImpl;

    @Autowired
    private ClientRepositoryImpl clientRepositoryImpl;

    @Autowired
    private AccountNumberServiceImpl accountNumberServiceImpl;

    @Override
    public Product createProduct(Product product) {

        ClientEntity clientEntity = clientRepositoryImpl.findById(product.getClient().getId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + product.getClient().getId()));

        Client client = convertToEntityDomainClient(clientEntity);

        if (client.canAddProduct(product.getType())) {

            ProductEntity productEntity = convertToEntityProduct(product);
            productEntity.setClient(clientEntity);
            productEntity.setStatus(ProductStatus.ACTIVA);
            productEntity.setCreateDate(new Date());
            productEntity.setUpdateDate(new Date());

            productEntity.setNumber(accountNumberServiceImpl.generateAccountNumber(product.getType()));
            return convertToEntityDomainProduct(productRepositoryImpl.save(productEntity));
        } else {
            throw new IllegalArgumentException("El cliente ya tiene un producto de este tipo");
        }

    }


    @Override
    public Product updateProduct(Long productId, Product product) {
        return productRepositoryImpl.findById(productId)
                .map(existingProduct -> {
                    existingProduct.setType(product.getType());
                    existingProduct.setStatus(product.getStatus() != null ? product.getStatus() : ProductStatus.ACTIVA);
                    existingProduct.setBalance(product.getBalance());
                    existingProduct.setGmf(product.getGmf());
                    existingProduct.setUpdateDate(new Date());

                    return convertToEntityDomainProduct(productRepositoryImpl.save(existingProduct));
                })
                .orElse(null);
    }

    @Override
    public void deleteProduct(Long productId) {
        Optional<ProductEntity> existingProductOptional = productRepositoryImpl.findById(productId);

        if (existingProductOptional.isEmpty()) {
            throw new IllegalArgumentException("Producto no encontrado con ID: " + productId);
        }

        ProductEntity existingProduct = existingProductOptional.get();
        if (existingProduct.getBalance() == 0) {
            productRepositoryImpl.delete(existingProduct);
        } else {
            throw new IllegalArgumentException("No se puede eliminar la cuenta porque tiene un saldo diferente de $0");
        }

    }

    @Override
    public List<Product> getAllProductsByClientId(Long clientId) {
        List<ProductEntity> products =  productRepositoryImpl.findAllByClientId(clientId);
        return products.stream()
                .map(ProductEntityMapper::convertToEntityDomainProduct)
                .collect(Collectors.toList());
    }

    @Override
    public Product getProductById(Long productId) {
        Optional<ProductEntity> existingProductOptional = productRepositoryImpl.findById(productId);

        if (existingProductOptional.isEmpty()) {
            throw new IllegalArgumentException("Producto no encontrado con ID: " + productId);
        }

        ProductEntity existingClient = existingProductOptional.get();
        return convertToEntityDomainProduct(existingClient);
    }
}