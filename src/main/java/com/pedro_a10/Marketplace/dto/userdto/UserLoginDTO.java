package com.pedro_a10.Marketplace.dto.userdto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginDTO {
  @NotBlank
  private String username;

  @NotBlank
  private String password;
}
