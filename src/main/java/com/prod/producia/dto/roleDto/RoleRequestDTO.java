package com.prod.producia.dto.roleDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoleRequestDTO {
    @NotNull(message = "Role name cannot be null")
    @Size(min = 3, max = 20, message = "Role name must be between 3 and 20 characters")
    private String name;
}
