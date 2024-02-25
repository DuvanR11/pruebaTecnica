package org.example.pruebatecnica.aplicacion.client;

import org.example.pruebatecnica.dominio.client.Client;
import org.example.pruebatecnica.dominio.client.ClientService;
import org.example.pruebatecnica.infraestructura.client.ClientEntity;
import org.example.pruebatecnica.infraestructura.client.ClientEntityMapper;
import org.example.pruebatecnica.infraestructura.client.ClientRepositoryImpl;
import org.example.pruebatecnica.infraestructura.producto.ProductEntity;
import org.example.pruebatecnica.infraestructura.producto.ProductRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.example.pruebatecnica.infraestructura.client.ClientEntityMapper.*;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepositoryImpl clientRepositoryImpl;

    @Autowired
    private ProductRepositoryImpl productRepositoryImpl;

    @Override
    public Client createClient(Client client) {
        client.isValidEmail(client.getEmail());
        client.validateAge(client.getAge());
        client.validateFirstNameAndLastName(client.getName(), client.getLastName());
        client.setCreateDate(new Date());
        client.setUpdateDate(new Date());
        ClientEntity clientEntity = clientRepositoryImpl.save(convertToEntityClient(client));
        return convertToEntityDomainClient(clientEntity);
    }

    @Override
    public Client updateClient(Long clientId, Client client) {
        return clientRepositoryImpl.findById(clientId)
                .map(existingClient -> {
                    existingClient.setTypeIdentification(client.getTypeIdentification());
                    existingClient.setIdentificationNumber(client.getIdentificationNumber());
                    existingClient.setName(client.getName());
                    existingClient.setLastName(client.getLastName());
                    existingClient.setEmail(client.getEmail());
                    existingClient.setAge(client.getAge());
                    existingClient.setUpdateDate(new Date());

                    return convertToEntityDomainClient(clientRepositoryImpl.save(existingClient));
                })
                .orElse(null);
    }


    @Override
    public void deleteClient(Long clientId) {
        Optional<ClientEntity> existingClientOptional = clientRepositoryImpl.findById(clientId);

        if (existingClientOptional.isEmpty()) {
            throw new IllegalArgumentException("Cliente no encontrado con ID: " + clientId);
        }

        ClientEntity existingClient = existingClientOptional.get();

        List<ProductEntity> products = productRepositoryImpl.findAllByClientId(existingClient.getId());
        if (!products.isEmpty()) {
            throw new IllegalArgumentException("No se puede eliminar el cliente porque tiene productos asociados");
        }
        clientRepositoryImpl.delete(existingClient);
    }


    @Override
    public List<Client> getAllClients() {
        List<ClientEntity> clientEntities = clientRepositoryImpl.findAll();
        return clientEntities.stream()
                .map(ClientEntityMapper::convertToEntityDomainClient)
                .collect(Collectors.toList());
    }


    @Override
    public Client getClientById(Long clientId) {
        Optional<ClientEntity> existingClientOptional = clientRepositoryImpl.findById(clientId);

        if (existingClientOptional.isEmpty()) {
            throw new IllegalArgumentException("Cliente no encontrado con ID: " + clientId);
        }

        ClientEntity existingClient = existingClientOptional.get();
        return convertToEntityDomainClient(existingClient);
    }


}
