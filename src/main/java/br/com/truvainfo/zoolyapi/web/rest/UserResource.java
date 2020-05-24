package br.com.truvainfo.zoolyapi.web.rest;

import br.com.truvainfo.zoolyapi.domain.dto.UserDto;
import br.com.truvainfo.zoolyapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserResource {
	
	private final UserService userService;
	
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<UserDto>> findAllUsers() {
		return ResponseEntity.ok(userService.findUsers());
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<UserDto> saveUser(@RequestBody final UserDto userDto) {
		
		userService.saveUser(userDto);
		return ResponseEntity.status(CREATED).build();
	}
	
	@PutMapping(consumes = "application/json")
	public ResponseEntity<UserDto> updateUser(@RequestBody final UserDto userDto) {
		
		userService.saveUser(userDto);
		return ResponseEntity.status(NO_CONTENT).build();
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<UserDto> deleteUser(@PathVariable final Integer userId) {
		
		userService.deleteUser(userId);
		return ResponseEntity.status(NO_CONTENT).build();
	}
}
