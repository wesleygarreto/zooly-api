package br.com.truvainfo.zoolyapi.domain.mapper;

import br.com.truvainfo.zoolyapi.domain.Biometry;
import br.com.truvainfo.zoolyapi.domain.dto.BiometryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { AnimalMapper.class })
public interface BiometryMapper extends AbstractMapper<Biometry, BiometryDto> {
	
	@Mapping(source = "animalId", target = "animal.id")
	@Mapping(source = "userId", target = "user.id")
	Biometry toEntity(BiometryDto dto);
}
