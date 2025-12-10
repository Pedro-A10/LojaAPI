package com.pedro_a10.Marketplace.service;

import com.pedro_a10.Marketplace.dto.userdto.UserRequestDTO;
import com.pedro_a10.Marketplace.exceptions.EmailAlreadyExistsException;
import com.pedro_a10.Marketplace.exceptions.UserCpfNotFoundException;
import com.pedro_a10.Marketplace.exceptions.UserNotFoundException;
import com.pedro_a10.Marketplace.dto.userdto.UserResponseDTO;
import com.pedro_a10.Marketplace.entity.User;
import com.pedro_a10.Marketplace.mapper.UserMapper;
import com.pedro_a10.Marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  UserMapper userMapper;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public UserResponseDTO findById(Long id) {
    User user = userRepository.findById(id)
      .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    return userMapper.toResponseDTO(user);
  }

  public UserResponseDTO findByCpf(String cpf) {
    User user = userRepository.findByCpf(cpf)
      .orElseThrow(() -> new UserCpfNotFoundException("User not found with cpf: " + cpf));
    return userMapper.toResponseDTO(user);
  }

  public List<UserResponseDTO> getAllClients() {
    return userMapper.toResponseDTOList(userRepository.findUserClient());
  }

  public List<UserResponseDTO> getAllEmployees() {
    return userMapper.toResponseDTOList(userRepository.findUserEmployee());
  }

  public User getUserByUsernameOrThrow(String username) {
    return userRepository.findByUsername(username)
      .orElseThrow(() -> new UserNotFoundException("User not found: " + username));
  }

  public UserResponseDTO createUser(UserRequestDTO userRequestDTO){
    if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
      throw new EmailAlreadyExistsException("Email already exists: " + userRequestDTO.getEmail());
    }

    User users = userMapper.toEntity(userRequestDTO);
    users.setPassword(passwordEncoder.encode(users.getPassword()));
    User saveUser = userRepository.save(users);
    return userMapper.toResponseDTO(saveUser);
  }

  public void deleteById(Long id) {
    if (!userRepository.existsById(id)) {
      throw new UserNotFoundException("User not found with id: " + id);
    }
    userRepository.deleteById(id);
  }
}
