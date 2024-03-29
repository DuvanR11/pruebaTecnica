package org.example.pruebatecnica.dominio.client;

import org.example.pruebatecnica.infraestructura.client.ClientEntity;

import java.util.List;

public interface ClientRepository {
    ClientEntity save(ClientEntity client);

    Client findById(Long clientId);

    List<Client> findAll();

    void delete(Client client);

}
