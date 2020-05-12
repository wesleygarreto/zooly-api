package br.com.truvainfo.zoolyapi.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum UserProfile {
	
	ADMIN(1),
	MANAGER(2),
	DOCTOR(3),
	OPERATOR(4);
	
	private final Integer value;
	
	public static UserProfile getProfile(Integer value) {
		return Stream.of(UserProfile.values())
		             .filter(userProfile -> userProfile.getValue().equals(value))
		             .findFirst()
		             .orElseThrow(() -> new IllegalArgumentException("The informed profile is invalid"));
	}
}
