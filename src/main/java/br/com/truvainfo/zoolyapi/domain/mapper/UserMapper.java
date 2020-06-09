package br.com.truvainfo.zoolyapi.domain.mapper;

import br.com.truvainfo.zoolyapi.domain.User;
import br.com.truvainfo.zoolyapi.domain.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { TaskMapper.class })
public interface UserMapper extends AbstractMapper<User, UserDTO> {
	
	@Mapping(target = "role", ignore = true)
	User toEntity(UserDTO dto);
	
	@Mapping(source = "role", target = "role")
    UserDTO toDto(User entity);
}
