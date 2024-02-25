package org.example.pruebatecnica.controller;

import org.example.pruebatecnica.aplicacion.client.ClientServiceImpl;
import org.example.pruebatecnica.dominio.client.Client;
import org.example.pruebatecnica.presentacion.controller.ClientController;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultMatcher;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientServiceImpl clientServiceImpl;

    @Test
    public void testGetClientById_clientFound() throws Exception {
        // Prepara el mock del servicio
        Long clientId = 1L;
        Client expectedClient = new Client();
        expectedClient.setId(clientId);
        Mockito.when(clientServiceImpl.getClientById(clientId)).thenReturn(expectedClient);

        // Realiza la petición GET simulada
        mockMvc.perform(get("/" + clientId))
                .andExpect(status().isOk()) // Corregido a isOk()
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON)) // Ajusta según el tipo de contenido esperado
                .andExpect((ResultMatcher) jsonPath("$.id").value(clientId)) // Verifica el valor del campo "id" en el JSON
                .andReturn();
    }

//    @Test
//    public void testGetClientById_clientNotFound() throws Exception {
//        Long clientId = 100L;
//        String expectedMessage = "Cliente no encontrado";
//        Mockito.when(clientServiceImpl.getClientById(clientId)).thenThrow(new IllegalArgumentException(expectedMessage));
//
//        MvcResult result = mockMvc.perform(get("/" + clientId))
//                .andExpect(status().isBadRequest())
//                .andReturn();
//
//        String actualMessage = (String) result.getResponse().getEntity();
//        assertEquals(expectedMessage, actualMessage);
//    }
}

