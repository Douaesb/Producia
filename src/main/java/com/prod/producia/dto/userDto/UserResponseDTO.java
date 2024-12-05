package com.prod.producia.dto.userDto;

import com.prod.producia.dto.roleDto.RoleEmbeddedDTO;
import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String username;
    private Boolean active;
    private RoleEmbeddedDTO role;
}
