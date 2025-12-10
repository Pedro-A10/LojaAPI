package com.pedro_a10.LojaAPI.controller;

import com.pedro_a10.LojaAPI.dto.productdto.ProductRequestDTO;
import com.pedro_a10.LojaAPI.dto.productdto.ProductResponseDTO;
import com.pedro_a10.LojaAPI.enums.ProductType;
import com.pedro_a10.LojaAPI.exceptions.ProductNotFoundException;
import com.pedro_a10.LojaAPI.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  ProductService productService;

  @GetMapping("/{id}")
  public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id) {
    return ResponseEntity.ok(productService.findById(id));
  }

  @GetMapping("/search")
  public ResponseEntity<ProductResponseDTO> findByName(@RequestParam String name) {
    return ResponseEntity.ok(productService.findByName(name));
  }

  @GetMapping("/prices")
  public ResponseEntity<List<ProductResponseDTO>> listByPriceInCents(@RequestParam Integer price) {
    return ResponseEntity.ok(productService.findByPriceInCents(price));
  }

  @GetMapping("/types")
  public ResponseEntity<List<ProductResponseDTO>> listAllByType(@RequestParam ProductType type) {
    return ResponseEntity.ok(productService.findAllByType(type));
  }

  @PostMapping
  public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody @Valid ProductRequestDTO productRequestDTO) {
    try {
      ProductResponseDTO newProduct = productService.createProduct(productRequestDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }catch (ProductNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    try {
      productService.deleteById(id);
      return ResponseEntity.noContent().build();
    }catch (ProductNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
