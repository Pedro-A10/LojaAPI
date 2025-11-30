package com.pedro_a10.LojaAPI.dto.productdto;

import com.pedro_a10.LojaAPI.enums.ProductType;

import lombok.Data;

@Data
public class ProductResponseDTO {

  private Long id;
  private String name;
  private Integer quantity;
  private Integer priceInCents;
  private byte[] image;
  private ProductType type;
}
