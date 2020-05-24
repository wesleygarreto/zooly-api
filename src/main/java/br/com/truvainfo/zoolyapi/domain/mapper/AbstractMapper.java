package br.com.truvainfo.zoolyapi.domain.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;

import java.util.List;

public interface AbstractMapper<T, D> {
	
	T toEntity(D dto);
	
	@InheritInverseConfiguration
	D toDto(T entity);
	
	@IterableMapping(qualifiedByName = "toDto")
	List<D> toDtoList(List<T> entities);
	
	@IterableMapping(qualifiedByName = "toEntity")
	List<T> toEntityList(List<D> dtos);
	
}
