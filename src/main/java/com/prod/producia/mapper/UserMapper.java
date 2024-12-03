package com.prod.producia.mapper;

import com.prod.producia.dto.userDto.UserEmbeddedDTO;
import com.prod.producia.dto.userDto.UserRequestDTO;
import com.prod.producia.dto.userDto.UserResponseDTO;
import com.prod.producia.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {

    User toEntity(UserRequestDTO dto);

    UserResponseDTO toResponseDTO(User user);

    UserEmbeddedDTO toEmbeddedDTO(User user);
}

