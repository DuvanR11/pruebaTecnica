package org.example.pruebatecnica.infraestructura.client;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.pruebatecnica.dominio.client.Client;
import org.example.pruebatecnica.dominio.client.ClientRepository;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private ClientEntity convertToEntity(Client client) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(client.getId());
        clientEntity.setTypeIdentification(client.getTypeIdentification());
        clientEntity.setIdentificationNumber(client.getIdentificationNumber());
        clientEntity.setName(client.getName());
        clientEntity.setLastName(client.getLastName());
        clientEntity.setEmail(client.getEmail());
        clientEntity.setCreateDate(client.getCreateDate());
        clientEntity.setUpdateDate(client.getUpdateDate());

        return clientEntity;
    }

    private Client convertToClient(ClientEntity clientEntity) {
        Client client = new Client();
        client.setId(clientEntity.getId());
        client.setTypeIdentification(clientEntity.getTypeIdentification());
        client.setIdentificationNumber(clientEntity.getIdentificationNumber());
        client.setName(clientEntity.getName());
        client.setLastName(clientEntity.getLastName());
        client.setEmail(clientEntity.getEmail());
        client.setCreateDate(clientEntity.getCreateDate());
        client.setUpdateDate(clientEntity.getUpdateDate());
        return client;
    }

    @Override
    @Transactional
    public Client save(Client client) {

        ClientEntity clientEntity = convertToEntity(client);

        if (client.getId() == null) {
            Date currentDate = new Date();
            clientEntity.setCreateDate(currentDate);
            clientEntity.setUpdateDate(currentDate);

            entityManager.persist(clientEntity);

            client.setId(clientEntity.getId());
        } else {
            clientEntity.setUpdateDate(new Date());
            entityManager.merge(clientEntity);
        }

        return client;
    }


    @Override
    public Client findById(Long clientId) {
        ClientEntity clientEntity = entityManager.find(ClientEntity.class, clientId);

        if (clientEntity != null) {
            return convertToClient(clientEntity);
        } else {
            return null;
        }
    }

    @Override
    public List<Client> findAll() {
        return entityManager.createQuery("SELECT c FROM ClientEntity c", Client.class).getResultList();
    }

    @Override
    @Transactional
    public void delete(Client client) {
        ClientEntity clientEntity = entityManager.find(ClientEntity.class, client.getId());
        entityManager.remove(clientEntity);
    }
}
