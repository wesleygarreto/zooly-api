package br.com.truvainfo.zoolyapi.web.rest;

import br.com.truvainfo.zoolyapi.domain.dto.BiometryDTO;
import br.com.truvainfo.zoolyapi.service.BiometryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/biometry")
@RequiredArgsConstructor
public class BiometryResource {
	
	private final BiometryService biometryService;
	
	@GetMapping(value = "/{animalId}", produces = "application/json")
	public ResponseEntity<List<BiometryDTO>> findAnimalBiometrics(@PathVariable final Integer animalId) {
		return ResponseEntity.ok(biometryService.findAnimalBiometrics(animalId));
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<BiometryDTO> saveBiometry(@RequestBody final BiometryDTO biometryDto) {
		
		biometryService.saveBiometry(biometryDto, Boolean.FALSE);
		return ResponseEntity.status(CREATED).build();
	}
	
	@PutMapping(consumes = "application/json")
	public ResponseEntity<BiometryDTO> updateBiometry(@RequestBody final BiometryDTO biometryDto) {
		
		biometryService.saveBiometry(biometryDto, Boolean.TRUE);
		return ResponseEntity.status(NO_CONTENT).build();
	}
	
	@DeleteMapping("/{biometryId}")
	public ResponseEntity<BiometryDTO> deleteBiometry(@PathVariable final Integer biometryId) {
		
		biometryService.deleteBiometry(biometryId);
		return ResponseEntity.status(NO_CONTENT).build();
	}
	
	@GetMapping("/report/{animalId}")
	public void generatePdfReport(@PathVariable final Integer animalId, final HttpServletResponse response) {
		biometryService.generatePdfReport(animalId, response);
	}
}
