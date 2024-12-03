package com.prod.producia.mapper;

import com.prod.producia.dto.roleDto.RoleEmbeddedDTO;
import com.prod.producia.dto.roleDto.RoleRequestDTO;
import com.prod.producia.dto.roleDto.RoleResponseDTO;
import com.prod.producia.entity.Role;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toEntity(RoleRequestDTO dto);

    RoleResponseDTO toResponseDTO(Role role);

    RoleEmbeddedDTO toEmbeddedDTO(Role role);
}
