package org.example.pruebatecnica.presentacion.controller;

import org.example.pruebatecnica.aplicacion.client.ClientServiceImpl;
import org.example.pruebatecnica.dominio.client.Client;
import org.example.pruebatecnica.presentacion.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            Client createdClient = clientServiceImpl.createClient(clientDTO);
            return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{clientId}")
    public Client updateClient(@PathVariable Long clientId, @RequestBody ClientDTO clientDTO) {
        return clientServiceImpl.updateClient(clientId, clientDTO);
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
    public Client getClientById(@PathVariable Long clientId) {
        return clientServiceImpl.getClientById(clientId);
    }
}
