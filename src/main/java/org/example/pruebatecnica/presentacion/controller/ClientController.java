package org.example.pruebatecnica.presentacion.controller;

import org.example.pruebatecnica.aplicacion.client.ClientServiceImpl;
import org.example.pruebatecnica.dominio.client.Client;
import org.example.pruebatecnica.presentacion.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.pruebatecnica.aplicacion.client.ClientMapper.convertDtoToDomainClient;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientServiceImpl clientServiceImpl;

    @Autowired
    public ClientController(ClientServiceImpl clientServiceImpl) {
        this.clientServiceImpl = clientServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Object> createClient(@RequestBody ClientDTO clientDTO) {
        try {
            Client client = convertDtoToDomainClient(clientDTO);
            Client createdClient = clientServiceImpl.createClient(client);
            return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Object> updateClient(@PathVariable Long clientId, @RequestBody ClientDTO clientDTO) {
        try {
            Client client = convertDtoToDomainClient(clientDTO);
            Client updateClient = clientServiceImpl.updateClient(clientId, client);
            return new ResponseEntity<>(updateClient, HttpStatus.CREATED);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<String> deleteClient(@PathVariable Long clientId) {
        try {
            clientServiceImpl.deleteClient(clientId);
            return new ResponseEntity<>("Cliente eliminado", HttpStatus.ACCEPTED);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientServiceImpl.getAllClients();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Object> getClientById(@PathVariable Long clientId) {
        try {
            Client getClient = clientServiceImpl.getClientById(clientId);
            return new ResponseEntity<>(getClient, HttpStatus.ACCEPTED);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
