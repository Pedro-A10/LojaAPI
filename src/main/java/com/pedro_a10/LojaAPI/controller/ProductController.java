package com.pedro_a10.LojaAPI.controller;

import com.pedro_a10.LojaAPI.entity.Product;
import com.pedro_a10.LojaAPI.enums.ProductType;
import com.pedro_a10.LojaAPI.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  ProductService productService;

  @GetMapping("/{id}")
  public Optional<Product> findById(@PathVariable Long id) {
    return productService.findById(id);
  }

  @GetMapping("/search")
  public Optional<Product> findByName(@RequestParam String name) {
    return productService.findByName(name);
  }

  @GetMapping("/prices")
  public List<Product> listByPriceInCents(@RequestParam Integer price) {
    return productService.listByPriceInCents(price);
  }

  @GetMapping("/types")
  public List<Product> listAllByType(@RequestParam ProductType type) {
    return productService.ListAllByType(type);
  }

  @PostMapping
  public Product createProduct(@RequestBody @Valid Product product) {
    return productService.createProduct(product);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    productService.deleteById(id);
  }
}
