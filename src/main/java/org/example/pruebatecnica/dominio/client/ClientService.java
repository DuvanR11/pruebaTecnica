package org.example.pruebatecnica.dominio.client;

import org.example.pruebatecnica.presentacion.dto.ClientDTO;

import java.util.List;

public interface ClientService {

    Client createClient( ClientDTO client );

    Client updateClient(Long clientId, ClientDTO client );

    void deleteClient(Long clientId);

    List<Client> getAllClients();

    Client getClientById(Long clientId);
}
