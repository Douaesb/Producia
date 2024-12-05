package com.prod.producia.controller;

import com.prod.producia.dto.userDto.UserRequestDTO;
import com.prod.producia.dto.userDto.UserResponseDTO;
import com.prod.producia.entity.User;
import com.prod.producia.mapper.UserMapper;
import com.prod.producia.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private UserService userService;
    private UserMapper userMapper;
    private AuthenticationManager authenticationManager;
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        if (userService.existsByUsername(userRequestDTO.getUsername())) {
            return ResponseEntity.badRequest().body(null);
        }

        String encodedPassword = passwordEncoder.encode(userRequestDTO.getPassword());

        User user = userMapper.toEntity(userRequestDTO);
        user.setPassword(encodedPassword);

        User savedUser = userService.save(user);

        UserResponseDTO responseDTO = userMapper.toResponseDTO(savedUser);

        return ResponseEntity.ok(responseDTO);
    }

    // Login user
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody @Valid UserRequestDTO userRequestDTO, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userRequestDTO.getUsername(), userRequestDTO.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        UserResponseDTO responseDTO = userMapper.toResponseDTO(user);

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok().build();
    }
}
