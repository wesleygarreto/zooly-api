package br.com.truvainfo.zoolyapi.service;

import br.com.truvainfo.zoolyapi.domain.Animal;
import br.com.truvainfo.zoolyapi.domain.Biometry;
import br.com.truvainfo.zoolyapi.domain.dto.BiometryDto;
import br.com.truvainfo.zoolyapi.domain.mapper.BiometryMapper;
import br.com.truvainfo.zoolyapi.repository.BiometryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.*;

@Service
@RequiredArgsConstructor
public class BiometryService {
	
	@Value("${biometrics.report.path}")
	private String BIOMETRY_REPORT_FILEPATH;
	
	@Value("${biometrics.report.file.name}")
	private String BIOMETRY_REPORT_FILENAME;
	
	public static final String MSG_ERROR_BIOMETRY_ID = "msg.error.biometry.id";
	
	private final BiometryRepository biometryRepository;
	private final BiometryMapper biometryMapper;
	private final ReportService reportService;
	private final AnimalService animalService;
	
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
	
	public void generatePdfReport(final Integer animalId, final HttpServletResponse response) {
		
		final Animal animal = animalService.findById(animalId);
		final Map<String, Object> parameters = new HashMap<>();
		
		parameters.put("cd_animal", animal.getId());
		parameters.put("name_animal", animal.getNickname());
		
		reportService.exportToPdfStream(BIOMETRY_REPORT_FILEPATH, BIOMETRY_REPORT_FILENAME, parameters, response);
	}
	
}
