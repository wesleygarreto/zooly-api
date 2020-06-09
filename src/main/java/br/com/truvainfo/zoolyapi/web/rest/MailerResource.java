package br.com.truvainfo.zoolyapi.web.rest;

import br.com.truvainfo.zoolyapi.domain.dto.MessageEmailDTO;
import br.com.truvainfo.zoolyapi.service.MailerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class MailerResource {
	
	private final MailerService mailerService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(consumes = "application/json")
	public ResponseEntity<MessageEmailDTO> sendEmail(@RequestBody MessageEmailDTO messageEmailDTO) {
		mailerService.sendEmail(messageEmailDTO);
		return ResponseEntity.status(NO_CONTENT).build();
	}

}
