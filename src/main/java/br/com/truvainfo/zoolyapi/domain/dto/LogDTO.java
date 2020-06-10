package br.com.truvainfo.zoolyapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Getter
@Setter
public class LogDTO implements Serializable {
	
	private Integer id;
	
	private String icon;
	
	private String userName;
	
	private String message;
	
	@JsonFormat(shape = STRING, pattern = "yyyy-MM-dd HH:mm")
	private Date creationDate;
	
}
