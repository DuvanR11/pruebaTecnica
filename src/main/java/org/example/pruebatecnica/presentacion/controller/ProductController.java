package org.example.pruebatecnica.presentacion.controller;

import org.example.pruebatecnica.aplicacion.product.ProductServiceImpl;
import org.example.pruebatecnica.dominio.product.Product;
import org.example.pruebatecnica.presentacion.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

    @Autowired
    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @PostMapping
    public Product createProduct(@RequestBody ProductDTO productDTO) {
       return productServiceImpl.createProduct(productDTO.getClient(), productDTO.getType(), productDTO.getStatus(), productDTO.getNumber(), productDTO.getBalance(), productDTO.getGmf());
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable Long productId, @RequestBody ProductDTO productDTO) {
       return productServiceImpl.updateProduct(productId, productDTO.getType(), productDTO.getStatus(), productDTO.getNumber(), productDTO.getBalance(), productDTO.getGmf());
    }
//
    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        return productServiceImpl.deleteProduct(productId);
    }
//
    @GetMapping
    public List<Product> getAllProductsByClientId(@RequestParam Long clientId) {
        return productServiceImpl.getAllProductsByClientId(clientId);
    }
//
    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        System.out.println("get----------");
        return productServiceImpl.getProductById(productId);
    }
}
