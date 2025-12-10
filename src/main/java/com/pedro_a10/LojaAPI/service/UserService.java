package com.pedro_a10.LojaAPI.service;

import com.pedro_a10.LojaAPI.dto.userdto.UserRequestDTO;
import com.pedro_a10.LojaAPI.exceptions.EmailAlreadyExistsException;
import com.pedro_a10.LojaAPI.exceptions.UserCpfNotFoundException;
import com.pedro_a10.LojaAPI.exceptions.UserNotFoundException;
import com.pedro_a10.LojaAPI.dto.userdto.UserResponseDTO;
import com.pedro_a10.LojaAPI.entity.User;
import com.pedro_a10.LojaAPI.mapper.UserMapper;
import com.pedro_a10.LojaAPI.repository.UserRepository;
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

  /*
  off until config package is create
  @Autowired
  private PasswordEncoder passwordEncoder;
  */

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

  public UserResponseDTO createUser(UserRequestDTO userRequestDTO){
    if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
      throw new EmailAlreadyExistsException("Email already exists: " + userRequestDTO.getEmail());
    }

    User users = userMapper.toEntity(userRequestDTO);
    //users.setPassword(passwordEncoder.encode(users.getPassword()));
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
