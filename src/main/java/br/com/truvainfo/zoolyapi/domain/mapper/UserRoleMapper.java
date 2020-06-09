package br.com.truvainfo.zoolyapi.domain.mapper;

import br.com.truvainfo.zoolyapi.domain.User;
import br.com.truvainfo.zoolyapi.domain.UserRole;
import br.com.truvainfo.zoolyapi.domain.dto.UserDTO;
import br.com.truvainfo.zoolyapi.domain.dto.UserRoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRoleMapper extends AbstractMapper<UserRole, UserRoleDTO> {

	@Mapping(target = "userRole")
	UserRole toEntity(UserDTO dto);

	@Mapping(target = "userRole")
	UserRoleDTO toDto(User entity);
}
