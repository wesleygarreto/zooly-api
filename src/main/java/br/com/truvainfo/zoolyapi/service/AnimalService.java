package br.com.truvainfo.zoolyapi.service;

import br.com.truvainfo.zoolyapi.domain.Animal;
import br.com.truvainfo.zoolyapi.domain.dto.AnimalDto;
import br.com.truvainfo.zoolyapi.domain.mapper.AnimalMapper;
import br.com.truvainfo.zoolyapi.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static java.util.Objects.*;

@Service
@RequiredArgsConstructor
public class AnimalService {
	
	private final AnimalRepository animalRepository;
	private final AnimalMapper animalMapper;
	
	public List<AnimalDto> findAnimals() {
		return animalMapper.toDtoList(animalRepository.findAll());
	}
	
	public void saveAnimal(final AnimalDto animalDto) {
		
		final Animal animal = animalMapper.toEntity(animalDto);
		
		if (isNull(animal.getCreationDate())) {
			animal.setCreationDate(new Date());
		}
		
		animalRepository.save(animal);
	}
	
	public void deleteAnimal(final Integer animalCode) {
		animalRepository.delete(animalRepository.findById(animalCode)
		                                        .orElseThrow(() -> new IllegalArgumentException(
				                                        "The Animal of id " + animalCode + " not exists")));
	}
	
}
