package com.group2.aligranel.product.service;

import com.group2.aligranel.product.dto.ProductRequestDTO;
import com.group2.aligranel.product.dto.ProductResponseDTO;
import org.bson.types.ObjectId;

import java.util.List;

public interface ProductService {
    ProductResponseDTO getProductById(ObjectId id);
    List<ProductResponseDTO> getProductsByCategory(String category);
    List<ProductResponseDTO> getAllProducts();
    ProductResponseDTO createProduct(ProductRequestDTO productRequest);
    void updateProduct(ProductRequestDTO productRequest, ObjectId id);
    void deleteProduct(ObjectId id);
}
