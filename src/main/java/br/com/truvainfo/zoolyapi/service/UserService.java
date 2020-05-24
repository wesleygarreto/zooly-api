package br.com.truvainfo.zoolyapi.service;

import br.com.truvainfo.zoolyapi.domain.User;
import br.com.truvainfo.zoolyapi.domain.dto.UserDto;
import br.com.truvainfo.zoolyapi.domain.mapper.UserMapper;
import br.com.truvainfo.zoolyapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
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
				                                      () -> new IllegalArgumentException("Invalid email/password")));
	}
	
	public void saveUser(final UserDto userDto) {
		userRepository.save(userMapper.toEntity(userDto));
	}
	
	public void deleteUser(final Integer userCode) {
		userRepository.delete(userRepository.findById(userCode)
		                                    .orElseThrow(() -> new IllegalArgumentException(
				                                    "The User of id " + userCode + " not exists")));
	}
}
