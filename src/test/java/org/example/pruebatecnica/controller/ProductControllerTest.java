package org.example.pruebatecnica.controller;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.pruebatecnica.aplicacion.product.ProductServiceImpl;
import org.example.pruebatecnica.dominio.client.Client;
import org.example.pruebatecnica.dominio.client.ClientTypeIdentification;
import org.example.pruebatecnica.dominio.product.Product;
import org.example.pruebatecnica.dominio.product.ProductStatus;
import org.example.pruebatecnica.dominio.product.ProductType;
import org.example.pruebatecnica.presentacion.controller.ProductController;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ProductServiceImpl productServiceImpl;

    Client CLIENT_1 = new Client(1L, ClientTypeIdentification.CEDULA, "1003569874", "Duvan", "Rivera", "duvan@gmail.com", 18);
    Product PRODUCT_1 = new Product(CLIENT_1, ProductType.CUENTA_CORRIENTE, ProductStatus.ACTIVA, 0, true);
    Product PRODUCT_2 = new Product(null, ProductType.CUENTA_CORRIENTE, ProductStatus.ACTIVA, 0, true);
    Product PRODUCT_3 = new Product(CLIENT_1, ProductType.CUENTA_CORRIENTE, ProductStatus.ACTIVA, 0, true);

    @Test
    public void createProduct_success() throws Exception {

        Mockito.when(productServiceImpl.createProduct(any(Product.class))).thenReturn(PRODUCT_1);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(PRODUCT_1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("ACTIVA"))
                .andExpect(jsonPath("$.balance").value(0))
                .andExpect(jsonPath("$.type").value(ProductType.CUENTA_CORRIENTE.toString()))
                .andExpect(jsonPath("$.client.id").value(CLIENT_1.getId().intValue()))
                .andExpect(jsonPath("$.client.typeIdentification").value(CLIENT_1.getTypeIdentification().toString()))
                .andExpect(jsonPath("$.client.identificationNumber").value(CLIENT_1.getIdentificationNumber()))
                .andExpect(jsonPath("$.client.name").value(CLIENT_1.getName()))
                .andExpect(jsonPath("$.client.lastName").value(CLIENT_1.getLastName()))
                .andExpect(jsonPath("$.client.age").value(CLIENT_1.getAge()))
                .andExpect(jsonPath("$.client.email").value(CLIENT_1.getEmail()));

    }

    @Test
    public void createProduct_invalidClient() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(PRODUCT_2)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("El producto se debe asociar a un cliente"));
    }

    @Test
    public void createProduct_clientNotFound() throws Exception {

        Mockito.when(productServiceImpl.createProduct(any(Product.class))).thenThrow(new IllegalArgumentException("Cliente no encontrado con ID: 999"));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(PRODUCT_3)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Cliente no encontrado con ID: 999"));
    }

    @Test
    public void updateProduct_Success() throws Exception {
        Mockito.when(productServiceImpl.updateProduct(Mockito.anyLong(), Mockito.any(Product.class)))
                .thenReturn(PRODUCT_1);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(PRODUCT_1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ACTIVA"))
                .andExpect(jsonPath("$.balance").value(0))
                .andExpect(jsonPath("$.type").value("CUENTA_CORRIENTE"));


        Mockito.verify(productServiceImpl, Mockito.times(1))
                .updateProduct(Mockito.eq(1L), Mockito.any(Product.class));
    }

    @Test
    public void updateProduct_ProductNotFound() throws Exception {
        Mockito.when(productServiceImpl.updateProduct(Mockito.anyLong(), Mockito.any(Product.class)))
                .thenThrow(new IllegalArgumentException("Producto no encontrado con ID: 3"));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/products/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(PRODUCT_3)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Producto no encontrado con ID: 3"));

    }

    @Test
    public void deleteProduct_Success() throws Exception {
        Long productId = 1L;

        Mockito.doNothing().when(productServiceImpl).deleteProduct(Mockito.eq(productId));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/products/1", productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(content().string("Producto eliminado"));

        Mockito.verify(productServiceImpl, Mockito.times(1)).deleteProduct(Mockito.eq(productId));
    }

    @Test
    public void deleteProduct_Failure() throws Exception {
        Long productId = 1L;
        String errorMessage = "No se puede eliminar el producto";

        Mockito.doThrow(new IllegalArgumentException(errorMessage)).when(productServiceImpl).deleteProduct(Mockito.eq(productId));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/products/{productId}", productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(errorMessage));

        Mockito.verify(productServiceImpl, Mockito.times(1)).deleteProduct(Mockito.eq(productId));
    }

    @Test
    public void getProductById_Success() throws Exception {
        Long productId = 2L;

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/products/{productId}", productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


}
