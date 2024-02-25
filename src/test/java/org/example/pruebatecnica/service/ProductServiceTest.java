package org.example.pruebatecnica.service;

import org.example.pruebatecnica.aplicacion.product.ProductServiceImpl;
import org.example.pruebatecnica.dominio.client.Client;
import org.example.pruebatecnica.dominio.client.ClientTypeIdentification;
import org.example.pruebatecnica.dominio.product.Product;
import org.example.pruebatecnica.dominio.product.ProductStatus;
import org.example.pruebatecnica.dominio.product.ProductType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
public class ProductServiceTest {

    @MockBean
    ProductServiceImpl productServiceImpl;

    Client CLIENT_1 = new Client(1L, ClientTypeIdentification.CEDULA, "1003569874", "Duvan", "Rivera", "duvan@gmail.com", 18);
    Product PRODUCT_1 = new Product(CLIENT_1, ProductType.CUENTA_CORRIENTE, ProductStatus.ACTIVA, 0, true);

    @Test
    public void updateProduct_Success() throws Exception {
        Mockito.when(productServiceImpl.updateProduct(Mockito.anyLong(), Mockito.any(Product.class)))
                .thenReturn(PRODUCT_1);

        Product result = productServiceImpl.updateProduct(1L,PRODUCT_1);

        Mockito.verify(productServiceImpl, Mockito.times(1))
                .updateProduct(Mockito.eq(1L), Mockito.any(Product.class));
    }

    @Test
    public void deleteProduct_Success() throws Exception {
        Long productId = 1L;

        productServiceImpl.deleteProduct(productId);

        Mockito.verify(productServiceImpl, Mockito.times(1)).deleteProduct(Mockito.eq(productId));
    }

    @Test
    public void deleteProduct_Failure() throws Exception {
        Long productId = 1L;

        try {
            productServiceImpl.deleteProduct(productId);
        } catch (IllegalArgumentException e) {
            Mockito.verify(productServiceImpl, Mockito.times(1)).deleteProduct(Mockito.eq(productId));

        }
    }

    @Test
    public void getProductById_Success() throws Exception {
        Long productId = 1L;

        Mockito.when(productServiceImpl.getProductById(Mockito.eq(productId)))
                .thenReturn(PRODUCT_1);

        Product result = productServiceImpl.getProductById(productId);

        assertEquals(PRODUCT_1, result);

        Mockito.verify(productServiceImpl, Mockito.times(1)).getProductById(Mockito.eq(productId));
    }

    @Test
    public void getProductById_NotFound() throws Exception {
        Long productId = 1L;

        Mockito.when(productServiceImpl.getProductById(Mockito.eq(productId)))
                .thenReturn(null);

        Product result = productServiceImpl.getProductById(productId);

        assertNull(result);

        Mockito.verify(productServiceImpl, Mockito.times(1)).getProductById(Mockito.eq(productId));
    }

}

