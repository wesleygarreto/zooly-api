package br.com.truvainfo.zoolyapi.domain.mapper;

import br.com.truvainfo.zoolyapi.domain.UserRole;
import br.com.truvainfo.zoolyapi.domain.dto.UserRoleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRoleMapper extends AbstractMapper<UserRole, UserRoleDTO> {

}
