package com.group2.aligranel.product.controller;

import com.group2.aligranel.product.dto.ProductRequestDTO;
import com.group2.aligranel.product.dto.ProductResponseDTO;
import com.group2.aligranel.product.service.ProductService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Transactional(readOnly = true)
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable ObjectId id){
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/products/categories/{category}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByCategory(@PathVariable String category){
        return new ResponseEntity<>(productService.getProductsByCategory(category), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/products")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequest){
        return new ResponseEntity<>(productService.createProduct(productRequest), HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/products/{id}")
    public ResponseEntity updateProduct(@RequestBody ProductRequestDTO productRequest, @PathVariable ObjectId id){
        productService.updateProduct(productRequest, id);
        return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable ObjectId id){
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product with ID" + id + " has been deleted successfully", HttpStatus.OK);
    }
}
