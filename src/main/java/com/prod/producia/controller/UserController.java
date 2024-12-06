package com.prod.producia.controller;


import com.prod.producia.dto.userDto.UserResponseDTO;
import com.prod.producia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> listUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/admin/users/{id}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> updateRoles(
            @PathVariable Long id,
            @RequestParam String role) {
        UserResponseDTO updatedUser = userService.updateUserRole(id, role);
        return ResponseEntity.ok(updatedUser);
    }
}
