package org.example.pruebatecnica.infraestructura.producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.pruebatecnica.dominio.client.Client;
import org.example.pruebatecnica.dominio.product.Product;
import org.example.pruebatecnica.dominio.product.ProductRepository;
import org.example.pruebatecnica.infraestructura.client.ClientEntity;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private ProductEntity convertToEntity(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setType(product.getType());
        productEntity.setStatus(product.getStatus());
        productEntity.setNumber(product.getNumber());
        productEntity.setBalance(product.getBalance());
        productEntity.setGmf(product.getGmf());

        ClientEntity clientEntity = convertToClientEntity(product.getClient());
        productEntity.setClient(clientEntity);

        return productEntity;
    }

    private ClientEntity convertToClientEntity(Client client) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(client.getId());
        clientEntity.setTypeIdentification(client.getTypeIdentification());
        clientEntity.setIdentificationNumber(client.getIdentificationNumber());
        clientEntity.setName(client.getName());
        clientEntity.setLastName(client.getLastName());
        clientEntity.setEmail(client.getEmail());
        clientEntity.setCreateDate(client.getCreateDate());
        clientEntity.setUpdateDate(client.getUpdateDate());

        // Puedes establecer los productos asociados si es necesario

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

        // Puedes establecer los productos asociados si es necesario

        return client;
    }

    private Product convertToProduct(ProductEntity productEntity) {
        Product product = new Product();

        product.setId(productEntity.getId());
        product.setType(productEntity.getType());
        product.setStatus(productEntity.getStatus());
        product.setNumber(productEntity.getNumber());
        product.setBalance(productEntity.getBalance());
        product.setGmf(productEntity.getGmf());
        product.setCreateDate(productEntity.getCreateDate());
        product.setUpdateDate(productEntity.getUpdateDate());
        Client client = convertToClient(productEntity.getClient());
        product.setClient(client);
        return product;
    }

    @Override
    @Transactional
    public Product save(Product product) {
        ProductEntity productEntity = convertToEntity(product);
        System.out.println(product);
        if (product.getId() == null) {
            System.out.println("Creando---");
            Date currentDate = new Date();
            productEntity.setCreateDate(currentDate);
            productEntity.setUpdateDate(currentDate);

            entityManager.persist(productEntity);

            product.setId(productEntity.getId());
        } else {
            System.out.println("Actualizando ---");
            productEntity.setUpdateDate(new Date());
            entityManager.merge(productEntity);
        }

        return product;
    }

    @Override
    public Product findById(Long productId) {
        ProductEntity productEntity = entityManager.find(ProductEntity.class, productId);

        if (productEntity != null) {
            System.out.println("trayendo----------");
            return convertToProduct(productEntity);
        } else {
            return null;
        }
    }
    @Override
    public List<Product> findAllByClientId(Long clientId) {
        System.out.println("búsqueda: " + clientId);
        return entityManager.createQuery(
                        "SELECT p FROM ProductEntity p WHERE p.client.id = :clientId", Product.class)
                .setParameter("clientId", clientId)
                .getResultList();
    }


    @Override
    @Transactional
    public void delete(Product product) {
        ProductEntity productEntity = entityManager.find(ProductEntity.class, product.getId());
        entityManager.remove(productEntity);
    }
}