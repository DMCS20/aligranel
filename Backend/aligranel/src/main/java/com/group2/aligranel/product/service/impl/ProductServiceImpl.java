package com.group2.aligranel.product.service.impl;

import com.group2.aligranel.product.dto.ProductRequestDTO;
import com.group2.aligranel.product.dto.ProductResponseDTO;
import com.group2.aligranel.product.mapper.ProductMapper;
import com.group2.aligranel.product.model.Product;
import com.group2.aligranel.product.repository.ProductRepository;
import com.group2.aligranel.product.service.ProductService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;
    @Override
    public ProductResponseDTO getProductById(ObjectId id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            //TODO: NotFoundException
        }

        return productMapper.toProductResponseDTO(productOptional.get());
    }

    @Override
    public List<ProductResponseDTO> getProductsByCategory(String category) {
        List<Product> products = productRepository.findByCategoriesContainingIgnoreCase(category);
        return productMapper.toProductResponseDTOList(products);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.toProductResponseDTOList(products);
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequest) {
        Product product = productMapper.toProduct(productRequest);
        productRepository.insert(product);
        return productMapper.toProductResponseDTO(product);
    }

    @Override
    public void updateProduct(ProductRequestDTO productRequest, ObjectId id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            //TODO: NotFoundException
        }

        productMapper.updateProductFromDTO(productRequest, productOptional.get());
        productRepository.save(productOptional.get());
    }

    @Override
    public void deleteProduct(ObjectId id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            //TODO: NotFoundException
        }

        productRepository.delete(productOptional.get());
    }
}
