package br.com.truvainfo.zoolyapi.service;

import br.com.truvainfo.zoolyapi.domain.dto.LogDTO;
import br.com.truvainfo.zoolyapi.domain.mapper.LogMapper;
import br.com.truvainfo.zoolyapi.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {

    private LogRepository logRepository;

    private LogMapper logMapper;

    public List<LogDTO> findAllLogs() {
        return logMapper.toDtoList(logRepository.findAll());
    }

    public List<LogDTO> findLogsByUser(Integer userId) {
        return logMapper.toDtoList(logRepository.findAllByUserId(userId));
    }

}
