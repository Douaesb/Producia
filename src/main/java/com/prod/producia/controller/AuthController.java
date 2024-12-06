package com.prod.producia.controller;

import com.prod.producia.dto.userDto.UserRequestDTO;
import com.prod.producia.mapper.UserMapper;
import com.prod.producia.entity.User;
import com.prod.producia.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequestDTO userDTO) {
        if (userService.existsByUsername(userDTO.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Username is already taken.");
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = userMapper.toEntity(userDTO);
        user.setActive(true);

        userService.saveUser(user);
        log.info("User registered successfully with username: {}", userDTO.getUsername());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User registered successfully.");
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Validated UserRequestDTO loginRequest, HttpServletRequest request) {
        try {
            request.getSession(false);
            request.getSession(true);

            var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            log.info("User logged in successfully: {}", request.getSession().getAttribute("SPRING_SECURITY_CONTEXT"));
            return ResponseEntity.ok("User logged in successfully.");
        } catch (Exception e) {
            log.error("Login failed for user: {}. Error: {}", loginRequest.getUsername(), e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }



    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        SecurityContextHolder.clearContext();

        log.info("User logged out successfully.");
        return ResponseEntity.ok("User logged out successfully.");
    }

}