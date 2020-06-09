package br.com.truvainfo.zoolyapi.web.rest;

import br.com.truvainfo.zoolyapi.domain.dto.UserDTO;
import br.com.truvainfo.zoolyapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserResource {
	
	private final UserService userService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<UserDTO>> findAllUsers() {
		return ResponseEntity.ok(userService.findUsers());
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<UserDTO> saveUser(@RequestBody final UserDTO userDto) {
		
		userService.saveUser(userDto);
		return ResponseEntity.status(CREATED).build();
	}
	
	@PutMapping(consumes = "application/json")
	public ResponseEntity<UserDTO> updateUser(@RequestBody final UserDTO userDto) {
		
		userService.saveUser(userDto);
		return ResponseEntity.status(NO_CONTENT).build();
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<UserDTO> deleteUser(@PathVariable final Integer userId) {
		
		userService.deleteUser(userId);
		return ResponseEntity.status(NO_CONTENT).build();
	}
}
