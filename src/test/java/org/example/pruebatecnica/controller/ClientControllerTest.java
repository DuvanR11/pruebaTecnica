package org.example.pruebatecnica.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.pruebatecnica.aplicacion.client.ClientServiceImpl;
import org.example.pruebatecnica.dominio.client.Client;
import org.example.pruebatecnica.dominio.client.ClientTypeIdentification;
import org.example.pruebatecnica.presentacion.controller.ClientController;
import org.example.pruebatecnica.presentacion.dto.ClientDTO;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebMvcTest(ClientController.class)
public class ClientControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ClientServiceImpl clientServiceImpl;

    Client CLIENT_1 = new Client(1L, ClientTypeIdentification.CEDULA, "1003569874", "Duvan", "Rivera", "duvan@gmail.com", 18);
    Client CLIENT_2 = new Client(2L, ClientTypeIdentification.CEDULA, "1074669874", "Andres", "Montealegre", "andres@gmail.com", 20);
    Client CLIENT_3 = new Client(3L, ClientTypeIdentification.CEDULA, "1756569874", "Maria", "Gomez", "maria@gmail.com", 25);
    Client CLIENT_4 = new Client(4L, ClientTypeIdentification.CEDULA, "1756569874", "M", "G", "maria@gmail.com", 25);
    Client CLIENT_5 = new Client(5L, ClientTypeIdentification.CEDULA, "1003569874", "Duvan", "Rivera", "duvangmail.com", 18);

    @Test
    public void createClient_success() throws Exception {

        Mockito.when(clientServiceImpl.createClient(any(Client.class))).thenReturn(CLIENT_1);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CLIENT_1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))  // Adjust the JSON path based on your actual response structure
                .andExpect(jsonPath("$.name", is("Duvan")))
                .andExpect(jsonPath("$.lastName", is("Rivera")))
                .andExpect(jsonPath("$.email", is("duvan@gmail.com")))
                .andExpect(jsonPath("$.age", is(18)));
    }

    @Test
    public void createClient_validateNameAndLastanem() throws Exception {

        Mockito.when(clientServiceImpl.createClient(any(Client.class)))
                .thenThrow(new IllegalArgumentException("La longitud del nombre y apellido debe ser al menos de 2 caracteres"));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CLIENT_4)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("La longitud del nombre y apellido debe ser al menos de 2 caracteres"));
    }

    @Test
    public void createClient_validateEmail() throws Exception {

        Mockito.when(clientServiceImpl.createClient(any(Client.class)))
                .thenThrow(new IllegalArgumentException("Formato de correo electrónico no válido"));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CLIENT_5)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Formato de correo electrónico no válido"));
    }

    @Test
    public void getAllClients_success() throws Exception {
        List<Client> clients = Arrays.asList(CLIENT_1, CLIENT_2, CLIENT_3);

        Mockito.when(clientServiceImpl.getAllClients()).thenReturn(clients);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/clients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is("Duvan")))
                .andExpect(jsonPath("$[1].name", is("Andres")))
                .andExpect(jsonPath("$[2].name", is("Maria")));
    }

    @Test
    public void getAllClients_emptyList() throws Exception {
        List<Client> clients = Collections.emptyList();

        Mockito.when(clientServiceImpl.getAllClients()).thenReturn(clients);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/clients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void getAllClients_error() throws Exception {
        Mockito.when(clientServiceImpl.getAllClients()).thenThrow(new RuntimeException("Error fetching clients"));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/clients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


}



