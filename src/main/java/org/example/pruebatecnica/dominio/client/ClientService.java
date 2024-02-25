package org.example.pruebatecnica.dominio.client;

import java.util.List;

public interface ClientService {

    Client createClient( Client client );

    Client updateClient(Long clientId, Client client );

    void deleteClient(Long clientId);

    List<Client> getAllClients();

    Client getClientById(Long clientId);
}
