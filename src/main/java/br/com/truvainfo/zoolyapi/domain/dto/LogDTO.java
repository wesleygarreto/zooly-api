package br.com.truvainfo.zoolyapi.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LogDTO implements Serializable {
	
	private Integer id;
	private String icon;
	private String userName;
	private String message;
	private String creationDate;

}
