package br.com.truvainfo.zoolyapi.domain;

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
@Table(schema = "ZOOLY", name = "LOG")
public class Log implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CD_LOG")
	private Integer id;
	
	@Column(name = "ICON")
	private String icon;
	
	@Column(name = "DESCRIPTION")
	private String message;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_USER")
	private User user;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
}
