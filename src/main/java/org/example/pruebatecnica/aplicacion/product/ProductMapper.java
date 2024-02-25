package org.example.pruebatecnica.aplicacion.product;

import org.example.pruebatecnica.dominio.client.Client;
import org.example.pruebatecnica.dominio.product.Product;
import org.example.pruebatecnica.presentacion.dto.ProductDTO;


import static org.example.pruebatecnica.aplicacion.client.ClientMapper.convertDtoToDomainClient;

public class ProductMapper {
    public static Product convertDtoToDomainProduct(ProductDTO productDTO) {

        Client client = convertDtoToDomainClient(productDTO.getClient());

        return new Product(
                client,
                productDTO.getType(),
                productDTO.getStatus(),
                productDTO.getNumber(),
                productDTO.getBalance(),
                productDTO.getGmf()
                //transactions
        );
    }
}
