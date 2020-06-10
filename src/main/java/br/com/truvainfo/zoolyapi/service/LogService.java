package br.com.truvainfo.zoolyapi.service;

import br.com.truvainfo.zoolyapi.domain.Log;
import br.com.truvainfo.zoolyapi.domain.MyUserDetails;
import br.com.truvainfo.zoolyapi.domain.User;
import br.com.truvainfo.zoolyapi.domain.dto.LogDTO;
import br.com.truvainfo.zoolyapi.domain.mapper.LogMapper;
import br.com.truvainfo.zoolyapi.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static br.com.truvainfo.zoolyapi.util.GeneralUtil.getLoggedUser;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;
    private final LogMapper logMapper;

    public final static String TASK_ICON = "clipboard-text-outline";
    public final static String ANIMAL_ICON = "paw";
    public final static String BIOMETRY_ICON = "pulse";
    public final static String USER_ICON = "account-supervisor";

    public List<LogDTO> findAllLogs() {
        return logMapper.toDtoList(logRepository.findAllByOrderByCreationDateDesc());
    }

    public List<LogDTO> findLogsByUser(Integer userId) {
        return logMapper.toDtoList(logRepository.findAllByUserIdOrderByCreationDateDesc(userId));
    }

    public Log saveLog(LogDTO logDTO) {
        Log log = logMapper.toEntity(logDTO);
        MyUserDetails loggedUser = getLoggedUser();
        log.setUser(User.builder().id(loggedUser.getUser().getId()).build());
        return logRepository.saveAndFlush(log);
    }

    public LogDTO createLogDTO(String icon, String message) {
        return LogDTO.builder()
                .creationDate(new Date())
                .icon(icon)
                .message(message)
                .build();
    }
}
