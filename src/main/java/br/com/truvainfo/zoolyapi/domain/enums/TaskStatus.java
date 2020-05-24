package br.com.truvainfo.zoolyapi.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum TaskStatus {
	
	UNCOMPLETED(1),
	COMPLETED(2);
	
	private final Integer value;
	
	public static TaskStatus getStatus(Integer value) {
		return Stream.of(TaskStatus.values())
		             .filter(taskStatus -> taskStatus.getValue().equals(value))
		             .findFirst()
		             .orElseThrow(() -> new IllegalArgumentException("The informed task status is invalid"));
	}
}
