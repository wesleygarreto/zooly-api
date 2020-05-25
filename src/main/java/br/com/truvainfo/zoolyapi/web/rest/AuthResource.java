package br.com.truvainfo.zoolyapi.web.rest;

import br.com.truvainfo.zoolyapi.domain.dto.AuthenticationRequestDTO;
import br.com.truvainfo.zoolyapi.domain.dto.AuthenticationResponseDTO;
import br.com.truvainfo.zoolyapi.security.MyUserDetailsService;
import br.com.truvainfo.zoolyapi.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.truvainfo.zoolyapi.util.GeneralUtil.getMessage;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
public class AuthResource {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping(produces = "application/json")
	public ResponseEntity<AuthenticationResponseDTO> createAunthenticationToken(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDTO.getEmail(), authenticationRequestDTO.getPassword()));
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(getMessage("msg.error.authentication.01"));
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequestDTO.getEmail());

		final String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponseDTO(jwt));
	}

}
