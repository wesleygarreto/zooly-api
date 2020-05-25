package br.com.truvainfo.zoolyapi.web.rest;

import br.com.truvainfo.zoolyapi.domain.dto.AnimalDto;
import br.com.truvainfo.zoolyapi.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/animal")
@RequiredArgsConstructor
public class AnimalResource {
	
	private final AnimalService animalService;
	
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<AnimalDto>> findAllAnimals() {
		return ResponseEntity.ok(animalService.findAnimals());
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<AnimalDto> saveAnimal(@RequestBody final AnimalDto animalDto) {
		
		animalService.saveAnimal(animalDto);
		return ResponseEntity.status(CREATED).build();
	}
	
	@PutMapping(consumes = "application/json")
	public ResponseEntity<AnimalDto> updateAnimal(@RequestBody final AnimalDto animalDto) {
		
		animalService.saveAnimal(animalDto);
		return ResponseEntity.status(NO_CONTENT).build();
	}
	
	@DeleteMapping("/{animalId}")
	public ResponseEntity<AnimalDto> deleteAnimal(@PathVariable final Integer animalId) {
		
		animalService.deleteAnimal(animalId);
		return ResponseEntity.status(NO_CONTENT).build();
	}
}
