package org.example.pruebatecnica.aplicacion.client;

import org.apache.coyote.BadRequestException;
import org.example.pruebatecnica.dominio.client.Client;
import org.example.pruebatecnica.dominio.client.ClientService;
import org.example.pruebatecnica.dominio.product.Product;
import org.example.pruebatecnica.infraestructura.client.ClientRepositoryImpl;
import org.example.pruebatecnica.presentacion.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepositoryImpl clientRepositoryImpl;

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    @Override
    public Client createClient(ClientDTO client) {

        if (client.getAge() < 18) {
            throw new IllegalArgumentException("EL cliente debe ser mayor de edad");
        }

        if (!isValidEmail(client.getEmail())) {
            throw new IllegalArgumentException("Formato de correo electrónico no válido");
        }

        if (client.getName().length() < 2 || client.getLastName().length() < 2) {
            throw new IllegalArgumentException("La longitud del nombre y apellido debe ser al menos de 2 caracteres");
        }

        List<Product> products = new ArrayList<>();

        Client newClient = new Client(client.getTypeIdentification(),
                client.getIdentificationNumber(),
                client.getName(),
                client.getLastName(),
                client.getEmail(),
                client.getAge(),
                products);

        return clientRepositoryImpl.save(newClient);
    }


    @Override
    public Client updateClient(Long clientId, ClientDTO client ) {
        Client existingClient = clientRepositoryImpl.findById(clientId);
        if (existingClient != null) {

            existingClient.setTypeIdentification(client.getTypeIdentification());
            existingClient.setIdentificationNumber(client.getIdentificationNumber());
            existingClient.setName(client.getName());
            existingClient.setLastName(client.getLastName());
            existingClient.setEmail(client.getEmail());
            existingClient.setAge(client.getAge());

            return clientRepositoryImpl.save(existingClient);
        }
        return null;
    }

    @Override
    public void deleteClient(Long clientId) {
        Client existingClient = clientRepositoryImpl.findById(clientId);
        if (existingClient == null) {
            throw new IllegalArgumentException("Cliente no encontrado con ID: " + clientId);
        }

//        if (existingClient.getProducts()) {
//            throw new IllegalStateException("No se puede eliminar el cliente porque tiene productos asociados");
//        }

        clientRepositoryImpl.delete(existingClient);
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
