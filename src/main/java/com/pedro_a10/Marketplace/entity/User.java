package com.pedro_a10.Marketplace.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty
  @Size(min = 15, max = 100, message = "Username must be 15 to 20 characters long")
  @Pattern(regexp = "^[A-Za-zÀ-ú]+(\\s[A-Za-zÀ-ú]+)+$",
    message = "Please provide your full name (first and last name).")
  @Column(unique = true)
  private String username;

  @NotEmpty
  @Email
  @Column(unique = true)
  private String email;

  @NotBlank(message = "Password is required")
  @Size(min = 8, max = 100, message = "Password must be 8 to more characters")
  @Pattern(
    regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
    message = "The password must contain uppercase letters, lowercase letters, numbers, and special characters."
  )
  private String password;

  @NotBlank(message = "Address is required")
  @Size(min = 5, max = 200, message = "Address must be between 5 and 200 characters")
  private String address;

  @NotBlank(message = "CPF is required")
  @CPF(message = "Invalid CPF")
  private String cpf;

  private boolean isEmployee = false;
}
