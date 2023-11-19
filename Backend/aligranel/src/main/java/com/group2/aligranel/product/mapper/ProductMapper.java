package com.group2.aligranel.product.mapper;

import com.group2.aligranel.product.dto.ProductResponseDTO;
import com.group2.aligranel.product.dto.ProductRequestDTO;
import com.group2.aligranel.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponseDTO toProductResponseDTO(Product product);
    Product toProduct(ProductRequestDTO productRequestDTO);
    List<ProductResponseDTO> toProductResponseDTOList(List<Product> products);
    List<Product> toProductList(List<ProductRequestDTO> productRequestDTOList);

    void updateProductFromDTO(ProductRequestDTO productRequestDTO, @MappingTarget Product product);
}
