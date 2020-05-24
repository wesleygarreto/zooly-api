package br.com.truvainfo.zoolyapi.service;

import br.com.truvainfo.zoolyapi.domain.dto.BiometryDto;
import br.com.truvainfo.zoolyapi.domain.mapper.BiometryMapper;
import br.com.truvainfo.zoolyapi.repository.BiometryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BiometryService {
	
	private final BiometryRepository biometryRepository;
	private final BiometryMapper biometryMapper;
	
	public List<BiometryDto> findAnimalBiometrics(final Integer animalCode) {
		return biometryMapper.toDtoList(biometryRepository.findBiometricsByAnimalId(animalCode));
	}
	
	public void saveBiometry(final BiometryDto biometryDto) {
		biometryRepository.save(biometryMapper.toEntity(biometryDto));
	}
	
	public void deleteBiometry(final Integer biometryCode) {
		biometryRepository.delete(biometryRepository.findById(biometryCode)
		                                            .orElseThrow(() -> new IllegalArgumentException(
				                                            "The Biometry of id " + biometryCode + " not exists")));
	}
	
}
