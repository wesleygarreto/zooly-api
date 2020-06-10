package br.com.truvainfo.zoolyapi.service;

import br.com.truvainfo.zoolyapi.domain.User;
import br.com.truvainfo.zoolyapi.domain.dto.UserChangeDTO;
import br.com.truvainfo.zoolyapi.domain.dto.UserDTO;
import br.com.truvainfo.zoolyapi.domain.dto.UserWithPasswdDTO;
import br.com.truvainfo.zoolyapi.domain.mapper.UserMapper;
import br.com.truvainfo.zoolyapi.domain.mapper.UserWithPasswdMapper;
import br.com.truvainfo.zoolyapi.repository.UserRepository;
import br.com.truvainfo.zoolyapi.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static br.com.truvainfo.zoolyapi.security.MyUserDetailsService.MSG_ERROR_AUTHENTICATION_01;
import static br.com.truvainfo.zoolyapi.service.LogService.TASK_ICON;
import static br.com.truvainfo.zoolyapi.service.LogService.USER_ICON;
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
	private final UserWithPasswdMapper userWithPasswdMapper;
	private final LogService logService;
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

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

	public void saveUser(final UserWithPasswdDTO userDto) {
		verifyEmailAlreadyExists(userDto);
		final String hashUser = generateHash();
		final User user = userWithPasswdMapper.toEntity(userDto);

		user.setUserRole(userRoleRepository.findById(userDto.getUserRole().getId())
		                               .orElseThrow(() -> new IllegalArgumentException(getMessage(MSG_ERROR_USER_ROLE))));

		if (isNull(user.getCreationDate())) {
			user.setCreationDate(new Date());
		}

		user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		user.setHash(hashUser);
		user.setActive(Boolean.TRUE);

		userRepository.save(user);
		logService.saveLog(logService.createLogDTO(USER_ICON, "inseriu um usuário às"));
	}

	private void verifyEmailAlreadyExists(UserWithPasswdDTO userDto) {
		if (userRepository.countByEmail(userDto.getEmail()) > 0)
			throw new IllegalArgumentException(getMessage("msg.error.user.email.exists"));
	}

	public void updateUser(final UserWithPasswdDTO userDto) {
		final User user = userRepository.findById(userDto.getId())
				.orElseThrow(() -> new IllegalArgumentException(getMessage(MSG_ERROR_USER_ID)));

		user.setUserRole(userRoleRepository.findById(userDto.getUserRole().getId())
				.orElseThrow(() -> new IllegalArgumentException(getMessage(MSG_ERROR_USER_ROLE))));

		user.setName(userDto.getName());
		user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		user.setEmail(user.getEmail());

		userRepository.save(user);
		logService.saveLog(logService.createLogDTO(USER_ICON, "atualizou um usuário às"));
	}

	private String generateHash() {
		return getMd5(getRandomWord());
	}

	public void deleteUser(final Integer userId) {
		userRepository.delete(userRepository.findById(userId)
		                                    .orElseThrow(
				                                    () -> new IllegalArgumentException(getMessage(MSG_ERROR_USER_ID) + userId)));
		logService.saveLog(logService.createLogDTO(USER_ICON, "removeu um usuário às"));
	}

	private User verifyHashAndUser(String username, String hashUser) throws Exception {
		return userRepository.findByEmailAndHash(username, hashUser)
				.orElseThrow(() -> new Exception(getMessage("msg.error.authentication.02")));
	}

	public boolean changePassword(UserChangeDTO userChangeDTO) throws Exception {
		User user = verifyHashAndUser(userChangeDTO.getEmail(), userChangeDTO.getHash());
		user.setPassword(bCryptPasswordEncoder.encode(userChangeDTO.getPassword()));
		user.setHash(generateHash());
		userRepository.saveAndFlush(user);
		return Boolean.TRUE;
	}

}
