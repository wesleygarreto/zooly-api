package br.com.truvainfo.zoolyapi.service;

import br.com.truvainfo.zoolyapi.domain.User;
import br.com.truvainfo.zoolyapi.domain.dto.UserChangeDTO;
import br.com.truvainfo.zoolyapi.domain.dto.UserDTO;
import br.com.truvainfo.zoolyapi.domain.mapper.UserMapper;
import br.com.truvainfo.zoolyapi.repository.UserRepository;
import br.com.truvainfo.zoolyapi.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static br.com.truvainfo.zoolyapi.security.MyUserDetailsService.MSG_ERROR_AUTHENTICATION_01;
import static br.com.truvainfo.zoolyapi.util.GeneralUtil.*;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class UserService {
	
	public static final String MSG_ERROR_USER_ROLE = "msg.error.user.role";
	public static final String MSG_ERROR_USER_ID = "msg.error.user.id";
	
	private final UserRepository userRepository;
	private final UserRoleRepository userRoleRepository;
	private final UserMapper userMapper;
	
	public List<UserDTO> findUsers() {
		return userMapper.toDtoList(userRepository.findAll());
	}
	
	public Optional<User> findByEmail(final String email) {
		return userRepository.findByEmail(email);
	}
	
	public UserDTO findUserByEmailPassword(final String email, final String password) {
		return userMapper.toDto(userRepository.findByEmailAndPassword(email, password)
		                                      .orElseThrow(
				                                      () -> new IllegalArgumentException(getMessage(MSG_ERROR_AUTHENTICATION_01))));
	}
	
	public void saveUser(final UserDTO userDto) {
		final String hashUser = generateHash();
		final User user = userMapper.toEntity(userDto);
		
		user.setUserRole(userRoleRepository.findByDescription(userDto.getUserRole().getDescription())
		                               .orElseThrow(() -> new IllegalArgumentException(getMessage(MSG_ERROR_USER_ROLE))));

		
		if (isNull(user.getCreationDate())) {
			user.setCreationDate(new Date());
		}

		user.setHash(hashUser);
		
		userRepository.save(user);
	}

	private String generateHash() {
		return getMd5(getRandomWord());
	}

	public void deleteUser(final Integer userId) {
		userRepository.delete(userRepository.findById(userId)
		                                    .orElseThrow(
				                                    () -> new IllegalArgumentException(getMessage(MSG_ERROR_USER_ID) + userId)));
	}

	private User verifyHashAndUser(String username, String hashUser) throws Exception {
		return userRepository.findByEmailAndHash(username, hashUser)
				.orElseThrow(() -> new Exception(getMessage("msg.error.authentication.02")));
	}

	public boolean changePassword(UserChangeDTO userChangeDTO) throws Exception {
		User user = verifyHashAndUser(userChangeDTO.getEmail(), userChangeDTO.getHash());
		user.setPassword(userChangeDTO.getPassword());
		user.setHash(generateHash());
		userRepository.saveAndFlush(user);
		return Boolean.TRUE;
	}

}
