package br.com.truvainfo.zoolyapi.domain;

import br.com.truvainfo.zoolyapi.enums.UserProfile;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "ZOOLY", name = "USER")
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CD_USER")
	private Integer code;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "NOTE")
	private String note;
	
	@Column(name = "PROFILE")
	private UserProfile profile;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
	@OneToMany(mappedBy = "responsibleUser")
	private List<Task> tasks;
	
}
