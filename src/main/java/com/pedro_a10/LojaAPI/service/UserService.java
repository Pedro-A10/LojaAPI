package com.pedro_a10.LojaAPI.service;

import com.pedro_a10.LojaAPI.entity.User;
import com.pedro_a10.LojaAPI.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  public Optional<User> findById(Long id) {
    return userRepository.findById(id);
  }

  public Optional<User> findByCpf(String cpf) {
    return userRepository.findByCpf(cpf);
  }

  public boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  public List<User> getAllClients() {
    return userRepository.listUserClient();
  }

  public List<User> getAllEmployees() {
    return userRepository.listUserEmployee();
  }

  public User createUser(@Valid User user){
    return userRepository.save(user);
  }

  public void deleteById(Long id) {
    userRepository.deleteById(id);
  }
}
