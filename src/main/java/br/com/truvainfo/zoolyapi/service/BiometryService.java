package br.com.truvainfo.zoolyapi.service;

import br.com.truvainfo.zoolyapi.domain.Biometry;
import br.com.truvainfo.zoolyapi.domain.dto.BiometryDto;
import br.com.truvainfo.zoolyapi.domain.mapper.BiometryMapper;
import br.com.truvainfo.zoolyapi.repository.BiometryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static java.util.Objects.*;

@Service
@RequiredArgsConstructor
public class BiometryService {
	
	public static final String MSG_ERROR_BIOMETRY_ID = "msg.error.biometry.id";
	
	private final BiometryRepository biometryRepository;
	private final BiometryMapper biometryMapper;
	
	public List<BiometryDto> findAnimalBiometrics(final Integer animalId) {
		return biometryMapper.toDtoList(biometryRepository.findBiometricsByAnimalId(animalId));
	}
	
	public void saveBiometry(final BiometryDto biometryDto) {
		
		final Biometry biometry = biometryMapper.toEntity(biometryDto);
		
		if (isNull(biometry.getCreationDate())) {
			biometry.setCreationDate(new Date());
		}
		
		biometryRepository.save(biometry);
	}
	
	public void deleteBiometry(final Integer biometryId) {
		biometryRepository.delete(biometryRepository.findById(biometryId)
		                                            .orElseThrow(() -> new IllegalArgumentException(
				                                            MSG_ERROR_BIOMETRY_ID + biometryId)));
	}
	
}
