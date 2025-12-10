package com.pedro_a10.LojaAPI.controller;

import com.pedro_a10.LojaAPI.dto.userdto.UserRequestDTO;
import com.pedro_a10.LojaAPI.dto.userdto.UserResponseDTO;
import com.pedro_a10.LojaAPI.exceptions.UserNotFoundException;
import com.pedro_a10.LojaAPI.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
    return ResponseEntity.ok(userService.findById(id));
  }

  @GetMapping("/cpf")
  public ResponseEntity<UserResponseDTO> findByCpf(@RequestParam String cpf) {
    return ResponseEntity.ok(userService.findByCpf(cpf));
  }

  @GetMapping("/clients")
  public ResponseEntity<List<UserResponseDTO>> getAllClients() {
    return ResponseEntity.ok(userService.getAllClients());
  }

  @GetMapping("/employees")
  public ResponseEntity<List<UserResponseDTO>> getAllEmployees() {
    return ResponseEntity.ok(userService.getAllEmployees());
  }

  @PostMapping
  public ResponseEntity<UserResponseDTO> createUser (@RequestBody @Valid UserRequestDTO userRequestDTO) {
    UserResponseDTO newUser = userService.createUser(userRequestDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    userService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
