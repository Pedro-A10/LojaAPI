package com.pedro_a10.LojaAPI.repository;

import com.pedro_a10.LojaAPI.entity.Product;
import com.pedro_a10.LojaAPI.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

  Optional<Product> findById(Long id);

  Optional<Product> findByName(String name);

  List<Product> listByPrice(Integer priceInCents);

  List<Product> ListAllByType(ProductType type);
}
