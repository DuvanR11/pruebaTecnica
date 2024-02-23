package org.example.pruebatecnica.dominio.client;

import java.util.List;

public interface ClientService {

    Client createClient(String typeIdentification, String identificationNumber, String name, String lastName, String email );

    Client updateClient(Long clientId, String typeIdentification, String identificationNumber, String name, String lastName, String email );

    String deleteClient(Long clientId);

    List<Client> getAllClients();

    Client getClientById(Long clientId);
}
