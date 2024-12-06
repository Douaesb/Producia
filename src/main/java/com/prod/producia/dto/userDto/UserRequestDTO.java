package com.prod.producia.dto.userDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {
    @NotNull(message = "Login cannot be null")
    @Size(min = 3, max = 50, message = "Login must be between 3 and 50 characters")
    private String username;

    @NotNull(message = "Password cannot be null")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    private String role;

}
