package br.com.truvainfo.zoolyapi.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserRoleDTO implements Serializable {

	private Integer id;
	private String description;
	
}
