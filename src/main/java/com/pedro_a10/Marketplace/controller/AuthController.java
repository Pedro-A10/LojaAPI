package com.pedro_a10.Marketplace.controller;

import com.pedro_a10.Marketplace.dto.userdto.UserLoginDTO;
import com.pedro_a10.Marketplace.dto.userdto.UserRequestDTO;
import com.pedro_a10.Marketplace.dto.userdto.UserResponseDTO;
import com.pedro_a10.Marketplace.entity.User;
import com.pedro_a10.Marketplace.security.JwtTokenProvider;
import com.pedro_a10.Marketplace.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserService userService;
  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;

  @PostMapping("/register")
  public ResponseEntity<UserResponseDTO> registerUser(
    @RequestBody @Validated UserRequestDTO userRequestDTO) {

    UserResponseDTO responseDTO = userService.createUser(userRequestDTO);
    return ResponseEntity.ok(responseDTO);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody @Validated UserLoginDTO loginDTO) {


    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        loginDTO.getUsername(),
        loginDTO.getPassword()
      )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token = jwtTokenProvider.generateToken(loginDTO.getUsername());
    User user = userService.getUserByUsernameOrThrow(loginDTO.getUsername());

    UserResponseDTO responseDTO = new UserResponseDTO();
    responseDTO.setId(user.getId());
    responseDTO.setUsername(user.getUsername());
    responseDTO.setEmail(user.getEmail());
    responseDTO.setAddress(user.getAddress());
    responseDTO.setEmployee(user.isEmployee());


    return ResponseEntity.ok(new JwtLoginResponse(responseDTO, token));
  }

  @lombok.Data
  @lombok.AllArgsConstructor
  static class JwtLoginResponse {
    private UserResponseDTO user;
    private String token;
  }
}
