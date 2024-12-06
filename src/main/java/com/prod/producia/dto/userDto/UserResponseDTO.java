package com.prod.producia.dto.userDto;

import com.prod.producia.entity.enums.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {

    private Long id;
    private Role role;
    private Boolean active;
}
