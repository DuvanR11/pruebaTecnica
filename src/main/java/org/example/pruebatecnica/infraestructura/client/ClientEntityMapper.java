package org.example.pruebatecnica.infraestructura.client;

import org.example.pruebatecnica.dominio.client.Client;
import org.example.pruebatecnica.infraestructura.producto.ProductEntityMapper;

import java.util.stream.Collectors;

public class ClientEntityMapper {

    public static ClientEntity convertToEntityClient(Client client) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(client.getId());
        clientEntity.setTypeIdentification(client.getTypeIdentification());
        clientEntity.setIdentificationNumber(client.getIdentificationNumber());
        clientEntity.setName(client.getName());
        clientEntity.setLastName(client.getLastName());
        clientEntity.setAge(client.getAge());
        clientEntity.setEmail(client.getEmail());
        clientEntity.setCreateDate(client.getCreateDate());
        clientEntity.setUpdateDate(client.getUpdateDate());

        if (client.getProducts() != null) {
            clientEntity.setProducts(
                    client.getProducts().stream()
                            .map(ProductEntityMapper::convertToEntityProduct)
                            .collect(Collectors.toList())
            );
            clientEntity.getProducts().forEach(productEntity -> productEntity.setClient(clientEntity));
        }

        return clientEntity;
    }

    public static Client convertToEntityDomainClient(ClientEntity clientEntity) {
        Client client = new Client();
        client.setId(clientEntity.getId());
        client.setTypeIdentification(clientEntity.getTypeIdentification());
        client.setIdentificationNumber(clientEntity.getIdentificationNumber());
        client.setName(clientEntity.getName());
        client.setLastName(clientEntity.getLastName());
        client.setAge(clientEntity.getAge());
        client.setEmail(clientEntity.getEmail());
        client.setCreateDate(clientEntity.getCreateDate());
        client.setUpdateDate(clientEntity.getUpdateDate());

        if (clientEntity.getProducts() != null) {
            client.setProducts(
                    clientEntity.getProducts().stream()
                            .map(ProductEntityMapper::convertToEntityDomainProduct)
                            .collect(Collectors.toList())
            );
        }

        return client;
    }
}

