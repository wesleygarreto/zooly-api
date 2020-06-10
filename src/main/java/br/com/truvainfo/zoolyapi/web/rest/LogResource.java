package br.com.truvainfo.zoolyapi.web.rest;

import br.com.truvainfo.zoolyapi.domain.dto.LogDTO;
import br.com.truvainfo.zoolyapi.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LogResource {
	
	private final LogService logService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<LogDTO>> findUserTasks() {
		return ResponseEntity.ok(logService.findAllLogs());
	}

	@GetMapping(value = "/{userId}", produces = "application/json")
	public ResponseEntity<List<LogDTO>> findUserTasks(@PathVariable final Integer userId) {
		return ResponseEntity.ok(logService.findLogsByUser(userId));
	}

}
