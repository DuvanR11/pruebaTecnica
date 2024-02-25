package org.example.pruebatecnica.infraestructura.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ClientRepositoryImpl extends JpaRepository<ClientEntity, Long> {

    List<ClientEntity> findAll();

}
