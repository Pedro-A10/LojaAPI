package com.pedro_a10.LojaAPI.service;

import com.pedro_a10.LojaAPI.dto.productdto.ProductRequestDTO;
import com.pedro_a10.LojaAPI.dto.productdto.ProductResponseDTO;
import com.pedro_a10.LojaAPI.entity.Product;
import com.pedro_a10.LojaAPI.enums.ProductType;
import com.pedro_a10.LojaAPI.exceptions.ProductInvalidQuantityException;
import com.pedro_a10.LojaAPI.exceptions.ProductNotFoundException;
import com.pedro_a10.LojaAPI.mapper.ProductMapper;
import com.pedro_a10.LojaAPI.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

  @Autowired
  ProductRepository productRepository;

  @Autowired
  ProductMapper productMapper;

  public ProductResponseDTO findById(Long id) {
    Product product = productRepository.findById(id)
      .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    return productMapper.toResponseDTO(product);
  }

  public ProductResponseDTO findByName(String name) {
    Product productName = productRepository.findByName(name)
      .orElseThrow(() -> new ProductNotFoundException("Product not found with name: " + name));
    return productMapper.toResponseDTO(productName);
  }

  public List<ProductResponseDTO> findByPriceInCents(Integer priceInCents) {
    return productMapper.toResponseDTOList(productRepository.findByPriceInCents(priceInCents));
  }

  public List<ProductResponseDTO> findAllByType(ProductType type) {
    return productMapper.toResponseDTOList(productRepository.findAllByType(type));
  }

  public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
    if (productRequestDTO.getQuantity() <= 0){
      throw new ProductInvalidQuantityException("Is not possible create products with 0 or below quantity: " + productRequestDTO.getQuantity());
    }

    Product products = productMapper.toEntity(productRequestDTO);
    Product saveProducts = productRepository.save(products);
    return productMapper.toResponseDTO(saveProducts);
  }

  public void deleteById(Long id) {
    if (!productRepository.existsById(id)) {
      throw new ProductNotFoundException("Product not found with id: " + id);
    }
    productRepository.deleteById(id);
  }
}
