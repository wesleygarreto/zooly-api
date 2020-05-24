package br.com.truvainfo.zoolyapi.domain.mapper;

import br.com.truvainfo.zoolyapi.domain.User;
import br.com.truvainfo.zoolyapi.domain.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { TaskMapper.class })
public interface UserMapper extends AbstractMapper<User, UserDto> {
	
	@Mapping(source = "role", target = "role.desc")
	User toEntity(UserDto dto);
	
}
