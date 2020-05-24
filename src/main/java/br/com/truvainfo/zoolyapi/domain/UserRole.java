package br.com.truvainfo.zoolyapi.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "ZOOLY", name = "USER_ROLE")
public class UserRole {
	
	public static final String ADMIN = "ADMIN";
	public static final String MANAGER = "MANAGER";
	public static final String DOCTOR = "DOCTOR";
	public static final String OPERATOR = "OPERATOR";

	@Id
	@Column(name = "CD_ROLE")
	private Integer id;
	@Column(name = "DESC_ROLE", unique = true)
	private String description;

}
