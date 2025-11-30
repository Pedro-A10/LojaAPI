package com.pedro_a10.LojaAPI.dto.productdto;

import com.pedro_a10.LojaAPI.enums.ProductType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductRequestDTO {

  @NotEmpty
  private String name;

  @NotNull
  @Min(1)
  private Integer quantity;

  @NotNull
  @Min(0)
  private Integer priceInCents;

  private byte[] image;

  @NotNull
  private ProductType type;
}
