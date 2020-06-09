package br.com.truvainfo.zoolyapi.web.rest;

import br.com.truvainfo.zoolyapi.domain.dto.UserDTO;
import br.com.truvainfo.zoolyapi.service.MailerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class MailerResource {
	
	private final MailerService mailerService;

	@PostMapping("/password")
	public ResponseEntity<Boolean> sendResetPasswdEmail(@RequestBody UserDTO userDTO) throws Exception {
		return ResponseEntity.ok(mailerService.sendResetPasswdEmail(userDTO.getEmail()));
	}

}
