package org.example.pruebatecnica.presentacion.controller;

import org.example.pruebatecnica.aplicacion.product.ProductServiceImpl;
import org.example.pruebatecnica.dominio.product.Product;
import org.example.pruebatecnica.presentacion.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.pruebatecnica.aplicacion.product.ProductMapper.convertDtoToDomainProduct;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private final ProductServiceImpl productServiceImpl;

    @Autowired
    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody ProductDTO productDTO) {
        try {
            Product product = convertDtoToDomainProduct(productDTO);
            Product createdProduct = productServiceImpl.createProduct(product);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable Long productId, @RequestBody ProductDTO productDTO) {
        Product product = convertDtoToDomainProduct(productDTO);
        return productServiceImpl.updateProduct(productId, product);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        try {
            productServiceImpl.deleteProduct(productId);
            return new ResponseEntity<>("Producto eliminado", HttpStatus.ACCEPTED);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Product> getAllProductsByClientId(@RequestParam Long clientId) {
        return productServiceImpl.getAllProductsByClientId(clientId);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = productServiceImpl.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
