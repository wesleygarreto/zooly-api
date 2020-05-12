package br.com.truvainfo.zoolyapi.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum TaskStatus {
	
	COMPLETED(1),
	UNCOMPLETED(2);
	
	private final Integer value;
	
	public static TaskStatus getStatus(Integer value) {
		return Stream.of(TaskStatus.values())
		             .filter(taskStatus -> taskStatus.getValue().equals(value))
		             .findFirst()
		             .orElseThrow(() -> new IllegalArgumentException("The informed task status is invalid"));
	}
}
