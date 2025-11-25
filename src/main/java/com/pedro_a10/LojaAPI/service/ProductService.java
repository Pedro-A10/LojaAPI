package com.pedro_a10.LojaAPI.service;

import com.pedro_a10.LojaAPI.entity.Product;
import com.pedro_a10.LojaAPI.enums.ProductType;
import com.pedro_a10.LojaAPI.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

  @Autowired
  ProductRepository productRepository;

  public Optional<Product> findById(Long id) {
    return productRepository.findById(id);
  }

  public Optional<Product> findByName(String name) {
    return productRepository.findByName(name);
  }

  public List<Product> ListAllByName(String name) {
    return productRepository.ListAllByName(name);
  }

  public List<Product> ListAllByType(ProductType type) {
    return productRepository.ListAllByType(type);
  }

  public Product createProduct(Product product) {
    return productRepository.save(product);
  }

  public void deleteById(Long id) {
    productRepository.deleteById(id);
  }
}
