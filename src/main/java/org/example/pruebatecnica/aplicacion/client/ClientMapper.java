package org.example.pruebatecnica.aplicacion.client;



import org.example.pruebatecnica.dominio.client.Client;
import org.example.pruebatecnica.presentacion.dto.ClientDTO;


public class ClientMapper {


    public static Client convertDtoToDomainClient(ClientDTO clientDTO) {

            return new Client(
                    clientDTO.getId(),
                    clientDTO.getTypeIdentification(),
                    clientDTO.getIdentificationNumber(),
                    clientDTO.getName(),
                    clientDTO.getLastName(),
                    clientDTO.getEmail(),
                    clientDTO.getAge()
            );
    }


}
