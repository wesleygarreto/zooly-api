package br.com.truvainfo.zoolyapi.domain.mapper;

import br.com.truvainfo.zoolyapi.domain.Task;
import br.com.truvainfo.zoolyapi.domain.dto.TaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { AnimalMapper.class, UserMapper.class })
public interface TaskMapper extends AbstractMapper<Task, TaskDto> {
	
	@Mapping(source = "responsibleUserId", target = "responsibleUser.id")
	@Mapping(source = "animalId", target = "animal.id")
	Task toEntity(TaskDto dto);
}
