package br.com.truvainfo.zoolyapi.web.rest;

import br.com.truvainfo.zoolyapi.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LogResource {
	
	private final LogService logService;

}
