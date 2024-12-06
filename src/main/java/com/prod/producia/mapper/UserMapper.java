package com.prod.producia.mapper;

import com.prod.producia.dto.userDto.UserRequestDTO;
import com.prod.producia.dto.userDto.UserResponseDTO;
import com.prod.producia.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequestDTO userRequestDTO);


    UserResponseDTO toResponse(User user);
}


