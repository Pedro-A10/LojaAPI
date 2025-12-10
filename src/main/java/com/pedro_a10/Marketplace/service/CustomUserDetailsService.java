package com.pedro_a10.Marketplace.service;

import com.pedro_a10.Marketplace.entity.User;
import com.pedro_a10.Marketplace.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // search user in db
    User user = userRepository.findByUsername(username)
      .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

    // roles
    List<String> roles = new ArrayList<>();
    roles.add("CLIENT"); // ALL USER IS CLIENTS
    if (user.isEmployee()) {
      roles.add("EMPLOYEE"); // Employee is Client
    }

    // create UserDetails for Spring Security
    return org.springframework.security.core.userdetails.User.builder()
      .username(user.getUsername())
      .password(user.getPassword())
      .roles(roles.toArray(new String[0]))
      .build();
  }
}
