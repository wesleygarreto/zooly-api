package br.com.truvainfo.zoolyapi.domain.mapper;

import br.com.truvainfo.zoolyapi.domain.User;
import br.com.truvainfo.zoolyapi.domain.dto.UserWithPasswdDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { TaskMapper.class, UserRoleMapper.class })
public interface UserWithPasswdMapper extends AbstractMapper<User, UserWithPasswdDTO> {

}
