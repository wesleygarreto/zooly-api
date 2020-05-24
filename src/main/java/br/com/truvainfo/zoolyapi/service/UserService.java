package br.com.truvainfo.zoolyapi.service;

import br.com.truvainfo.zoolyapi.domain.User;
import br.com.truvainfo.zoolyapi.domain.dto.UserDto;
import br.com.truvainfo.zoolyapi.domain.mapper.UserMapper;
import br.com.truvainfo.zoolyapi.repository.UserRepository;
import br.com.truvainfo.zoolyapi.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static br.com.truvainfo.zoolyapi.security.MyUserDetailsService.*;
import static java.util.Objects.*;

@Service
@RequiredArgsConstructor
public class UserService {
	
	public static final String MSG_ERROR_USER_ROLE = "msg.error.user.role";
	public static final String MSG_ERROR_USER_ID = "msg.error.user.id";
	
	private final UserRepository userRepository;
	private final UserRoleRepository userRoleRepository;
	private final UserMapper userMapper;
	
	public List<UserDto> findUsers() {
		return userMapper.toDtoList(userRepository.findAll());
	}
	
	public Optional<User> findByEmail(final String email) {
		return userRepository.findByEmail(email);
	}
	
	public UserDto findUserByEmailPassword(final String email, final String password) {
		return userMapper.toDto(userRepository.findByEmailAndPassword(email, password)
		                                      .orElseThrow(
				                                      () -> new IllegalArgumentException(MSG_ERROR_AUTHENTICATION_01)));
	}
	
	public void saveUser(final UserDto userDto) {
		
		final User user = userMapper.toEntity(userDto);
		
		user.setRole(userRoleRepository.findByDescription(userDto.getRole())
		                               .orElseThrow(() -> new IllegalArgumentException(MSG_ERROR_USER_ROLE)));
		
		if (isNull(user.getCreationDate())) {
			user.setCreationDate(new Date());
		}
		
		userRepository.save(user);
	}
	
	public void deleteUser(final Integer useriD) {
		userRepository.delete(userRepository.findById(useriD)
		                                    .orElseThrow(
				                                    () -> new IllegalArgumentException(MSG_ERROR_USER_ID + useriD)));
	}
}
