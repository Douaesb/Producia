package com.prod.producia.service.impl;

import com.prod.producia.dto.userDto.UserResponseDTO;
import com.prod.producia.entity.User;
import com.prod.producia.entity.enums.Role;
import com.prod.producia.mapper.UserMapper;
import com.prod.producia.repository.UserRepository;
import com.prod.producia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private UserMapper userMapper;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO updateUserRole(Long id, String role) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setRole(Role.ROLE_ADMIN);
        User updatedUser = userRepository.save(user);

        return userMapper.toResponse(updatedUser);
    }

}