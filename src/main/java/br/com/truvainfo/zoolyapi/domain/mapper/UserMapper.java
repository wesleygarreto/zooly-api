package br.com.truvainfo.zoolyapi.domain.mapper;

import br.com.truvainfo.zoolyapi.domain.User;
import br.com.truvainfo.zoolyapi.domain.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { TaskMapper.class })
public interface UserMapper extends AbstractMapper<User, UserDto> {
	
	@Mapping(target = "role", ignore = true)
	User toEntity(UserDto dto);
	
	@Mapping(source = "role.description", target = "role")
	UserDto toDto(User entity);
}
