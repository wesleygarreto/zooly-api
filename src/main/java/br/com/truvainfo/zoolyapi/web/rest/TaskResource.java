package br.com.truvainfo.zoolyapi.web.rest;

import br.com.truvainfo.zoolyapi.domain.dto.TaskDto;
import br.com.truvainfo.zoolyapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskResource {
	
	private final TaskService taskService;
	
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<TaskDto>> findAllTasks() {
		return ResponseEntity.ok(taskService.findAllTasks());
	}
	
	@GetMapping(value = "/user/{code}", produces = "application/json")
	public ResponseEntity<List<TaskDto>> findUserTasks(@PathVariable final Integer userCode) {
		return ResponseEntity.ok(taskService.findUserTasks(userCode));
	}
	
	@GetMapping(value = "/animal/{code}", produces = "application/json")
	public ResponseEntity<List<TaskDto>> findAnimalTasks(@PathVariable final Integer animalCode) {
		return ResponseEntity.ok(taskService.findAnimalTasks(animalCode));
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<TaskDto> saveTask(@RequestBody final TaskDto taskDto) {
		
		taskService.saveTask(taskDto);
		return ResponseEntity.status(CREATED).build();
	}
	
	@PutMapping(consumes = "application/json")
	public ResponseEntity<TaskDto> updateTask(@RequestBody final TaskDto taskDto) {
		
		taskService.saveTask(taskDto);
		return ResponseEntity.status(NO_CONTENT).build();
	}
	
	@DeleteMapping("/{taskId}")
	public ResponseEntity<TaskDto> deleteTask(@PathVariable final Integer taskId) {
		
		taskService.deleteTask(taskId);
		return ResponseEntity.status(NO_CONTENT).build();
	}
}
