package br.com.truvainfo.zoolyapi.domain.mapper;

import br.com.truvainfo.zoolyapi.domain.Animal;
import br.com.truvainfo.zoolyapi.domain.dto.AnimalDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { BiometryMapper.class, TaskMapper.class })
public interface AnimalMapper extends AbstractMapper<Animal, AnimalDTO> {
	
}
