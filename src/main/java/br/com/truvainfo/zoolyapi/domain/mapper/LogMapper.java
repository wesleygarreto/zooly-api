package br.com.truvainfo.zoolyapi.domain.mapper;

import br.com.truvainfo.zoolyapi.domain.Log;
import br.com.truvainfo.zoolyapi.domain.dto.LogDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface LogMapper extends AbstractMapper<Log, LogDTO> {
	
	@Mapping(source = "user.name", target = "userName")
	LogDTO toDto(Log entity);
}
