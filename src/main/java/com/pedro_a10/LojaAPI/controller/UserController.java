package com.pedro_a10.LojaAPI.controller;

import com.pedro_a10.LojaAPI.entity.User;
import com.pedro_a10.LojaAPI.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping("/{id}")
  public Optional<User> findById(@PathVariable Long id) {
    return userService.findById(id);
  }

  @GetMapping("/cpf")
  public Optional<User> findByCpf(@RequestParam String cpf) {
    return userService.findByCpf(cpf);
  }

  @GetMapping("/exists")
  public ResponseEntity<Boolean> existsByEmail(@RequestParam String email) {
    boolean exists = userService.existsByEmail(email);
    return ResponseEntity.ok(exists);
  }

  @GetMapping("/clients")
  public List<User> getAllClients() {
    return userService.getAllClients();
  }

  @GetMapping("/employees")
  public List<User> getAllEmployees() {
    return userService.getAllEmployees();
  }

  @PostMapping
  public User createUser (@RequestBody @Valid User user) {
    return userService.createUser(user);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    userService.deleteById(id);
  }
}
