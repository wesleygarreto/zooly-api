package br.com.truvainfo.zoolyapi.service;

import br.com.truvainfo.zoolyapi.domain.dto.TaskDto;
import br.com.truvainfo.zoolyapi.domain.mapper.TaskMapper;
import br.com.truvainfo.zoolyapi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.truvainfo.zoolyapi.domain.enums.TaskStatus.*;
import static java.util.Objects.*;

@Service
@RequiredArgsConstructor
public class TaskService {
	
	private final TaskRepository taskRepository;
	private final TaskMapper taskMapper;
	
	public List<TaskDto> findAllTasks() {
		return taskMapper.toDtoList(taskRepository.findAll());
	}
	
	public List<TaskDto> findUserTasks(final Integer userCode) {
		return taskMapper.toDtoList(taskRepository.findAllByResponsibleUserId(userCode));
	}
	
	public List<TaskDto> findAnimalTasks(final Integer animalCode) {
		return taskMapper.toDtoList(taskRepository.findAllByResponsibleUserId(animalCode));
	}
	
	public void saveTask(final TaskDto taskDto) {
		
		if (isNull(taskDto.getTaskStatus())) {
			taskDto.setTaskStatus(UNCOMPLETED);
		}
		
		taskRepository.save(taskMapper.toEntity(taskDto));
	}
	
	public void deleteTask(final Integer taskCode) {
		taskRepository.delete(taskRepository.findById(taskCode)
		                                    .orElseThrow(() -> new IllegalArgumentException(
				                                    "The Task of id " + taskCode + " not exists")));
	}
	
}
