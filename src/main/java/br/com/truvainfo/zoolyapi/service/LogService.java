package br.com.truvainfo.zoolyapi.service;

import br.com.truvainfo.zoolyapi.domain.dto.LogDTO;
import br.com.truvainfo.zoolyapi.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {

    private LogRepository logRepository;

    public List<LogDTO> findAllLogs() {
        logRepository.findAll();
        return null;
    }

    public List<LogDTO> findLogsByUser(Integer userId) {
        logRepository.findAllByUserId(userId);
        return null;
    }

}
