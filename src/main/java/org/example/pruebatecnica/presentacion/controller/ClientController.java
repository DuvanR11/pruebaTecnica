package org.example.pruebatecnica.presentacion.controller;

import org.example.pruebatecnica.aplicacion.client.ClientServiceImpl;
import org.example.pruebatecnica.dominio.client.Client;
import org.example.pruebatecnica.presentacion.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Client createClient(@RequestBody ClientDTO clientDTO) {
        return clientServiceImpl.createClient(clientDTO.getTypeIdentification(), clientDTO.getIdentificationNumber(), clientDTO.getName(), clientDTO.getLastName(), clientDTO.getEmail() );
    }

    @PutMapping("/{clientId}")
    public Client updateClient(@PathVariable Long clientId, @RequestBody ClientDTO clientDTO) {
        return clientServiceImpl.updateClient(clientId, clientDTO.getTypeIdentification(), clientDTO.getIdentificationNumber(), clientDTO.getName(), clientDTO.getLastName(), clientDTO.getEmail() );
    }

    @DeleteMapping("/{clientId}")
    public String deleteClient(@PathVariable Long clientId) {
        System.out.println("Eliminar");
        return clientServiceImpl.deleteClient(clientId);
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
