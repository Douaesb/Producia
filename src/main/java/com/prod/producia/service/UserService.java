package com.prod.producia.service;

import com.prod.producia.dto.userDto.UserResponseDTO;
import com.prod.producia.entity.User;

import java.util.List;

public interface UserService {

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO updateUserRole(Long id, String role);

    void saveUser(User user);
    boolean existsByUsername(String username);
}
