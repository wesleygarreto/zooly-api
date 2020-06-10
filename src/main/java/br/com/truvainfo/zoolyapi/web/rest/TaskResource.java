package br.com.truvainfo.zoolyapi.web.rest;

import br.com.truvainfo.zoolyapi.domain.dto.TaskDTO;
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
	public ResponseEntity<List<TaskDTO>> findAllTasks() {
		return ResponseEntity.ok(taskService.findAllTasks());
	}
	
	@GetMapping(value = "/user/{userId}", produces = "application/json")
	public ResponseEntity<List<TaskDTO>> findUserTasks(@PathVariable final Integer userId) {
		return ResponseEntity.ok(taskService.findUserTasks(userId));
	}
	
	@GetMapping(value = "/animal/{animalId}", produces = "application/json")
	public ResponseEntity<List<TaskDTO>> findAnimalTasks(@PathVariable final Integer animalId) {
		return ResponseEntity.ok(taskService.findAnimalTasks(animalId));
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<TaskDTO> saveTask(@RequestBody final TaskDTO taskDto) {
		
		taskService.saveTask(taskDto, Boolean.FALSE);
		return ResponseEntity.status(CREATED).build();
	}
	
	@PutMapping(consumes = "application/json")
	public ResponseEntity<TaskDTO> updateTask(@RequestBody final TaskDTO taskDto) {
		
		taskService.saveTask(taskDto, Boolean.TRUE);
		return ResponseEntity.status(NO_CONTENT).build();
	}
	
	@DeleteMapping("/{taskId}")
	public ResponseEntity<TaskDTO> deleteTask(@PathVariable final Integer taskId) {
		
		taskService.deleteTask(taskId);
		return ResponseEntity.status(NO_CONTENT).build();
	}
}
