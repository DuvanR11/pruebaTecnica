package org.example.pruebatecnica.infraestructura.producto;

import org.example.pruebatecnica.dominio.product.Product;


public class ProductEntityMapper {

    public static ProductEntity convertToEntityProduct(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setType(product.getType());
        productEntity.setStatus(product.getStatus());
        productEntity.setNumber(product.getNumber());
        productEntity.setBalance(product.getBalance());
        productEntity.setGmf(product.getGmf());
        productEntity.setCreateDate(product.getCreateDate());
        productEntity.setUpdateDate(product.getUpdateDate());

//        if (client.getProducts() != null) {
//            clientEntity.setProducts(
//                    client.getProducts().stream()
//                            .map(ProductEntityMapper::convertToEntity)
//                            .collect(Collectors.toList())
//            );
//            clientEntity.getProducts().forEach(productEntity -> productEntity.setClient(clientEntity));
//        }

        return productEntity;
    }

    public static Product convertToEntityDomainProduct(ProductEntity productEntity) {
        Product product = new Product();

        product.setId(productEntity.getId());
        product.setType(productEntity.getType());
        product.setStatus(productEntity.getStatus());
        product.setNumber(productEntity.getNumber());
        product.setBalance(productEntity.getBalance());
        product.setGmf(productEntity.getGmf());
        product.setCreateDate(productEntity.getCreateDate());
        product.setUpdateDate(productEntity.getUpdateDate());

//        if (clientEntity.getProducts() != null) {
//            client.setProducts(
//                    clientEntity.getProducts().stream()
//                            .map(ProductEntityMapper::convertToDomain)
//                            .collect(Collectors.toList())
//            );
//        }
        return product;
    }
}
