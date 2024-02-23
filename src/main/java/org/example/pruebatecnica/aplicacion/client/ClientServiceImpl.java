package org.example.pruebatecnica.aplicacion.client;

import org.example.pruebatecnica.dominio.client.Client;
import org.example.pruebatecnica.dominio.client.ClientService;
import org.example.pruebatecnica.dominio.product.Product;
import org.example.pruebatecnica.infraestructura.client.ClientRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepositoryImpl clientRepositoryImpl;

    @Override
    public Client createClient(String typeIdentification, String identificationNumber, String name, String lastName, String email ) {
        List<Product> products = new ArrayList<>();
        return clientRepositoryImpl.save(new Client(typeIdentification, identificationNumber, name, lastName, email, products ));
    }

    @Override
    public Client updateClient(Long clientId, String typeIdentification, String identificationNumber, String name, String lastName, String email ) {
        Client existingClient = clientRepositoryImpl.findById(clientId);
        if (existingClient != null) {

            existingClient.setTypeIdentification(typeIdentification);
            existingClient.setIdentificationNumber(identificationNumber);
            existingClient.setName(name);
            existingClient.setLastName(lastName);
            existingClient.setEmail(email);


            return clientRepositoryImpl.save(existingClient);
        }
        return null;
    }

    @Override
    public String deleteClient(Long clientId) {
        Client existingClient = clientRepositoryImpl.findById(clientId);
        if (existingClient != null) {
            clientRepositoryImpl.delete(existingClient);
            return "Cliente eliminado";
        }
        return "Cliente no existente";
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepositoryImpl.findAll();
    }

    @Override
    public Client getClientById(Long clientId) {
        return clientRepositoryImpl.findById(clientId);
    }
}
