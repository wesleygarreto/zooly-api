package br.com.truvainfo.zoolyapi.web.rest;

import br.com.truvainfo.zoolyapi.domain.dto.BiometryDto;
import br.com.truvainfo.zoolyapi.service.BiometryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/biometry")
@RequiredArgsConstructor
public class BiometryResource {
	
	private final BiometryService biometryService;
	
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<BiometryDto>> findAnimalBiometrics(@PathVariable final Integer animalCode) {
		return ResponseEntity.ok(biometryService.findAnimalBiometrics(animalCode));
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<BiometryDto> saveBiometry(@RequestBody final BiometryDto biometryDto) {
		
		biometryService.saveBiometry(biometryDto);
		return ResponseEntity.status(CREATED).build();
	}
	
	@PutMapping(consumes = "application/json")
	public ResponseEntity<BiometryDto> updateBiometry(@RequestBody final BiometryDto biometryDto) {
		
		biometryService.saveBiometry(biometryDto);
		return ResponseEntity.status(NO_CONTENT).build();
	}
	
	@DeleteMapping("/{code}")
	public ResponseEntity<BiometryDto> deleteBiometry(@PathVariable final Integer biometryCode) {
		
		biometryService.deleteBiometry(biometryCode);
		return ResponseEntity.status(NO_CONTENT).build();
	}
}
