package br.com.truvainfo.zoolyapi.domain;

import br.com.truvainfo.zoolyapi.enums.TaskStatus;
import br.com.truvainfo.zoolyapi.repository.AnimalRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "ZOOLY", name = "TASK")
public class Task implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CD_TASK")
	private Integer code;
	
	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE")
	private Date date;
	
	@Column(name = "STATUS")
	private TaskStatus taskStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_ANIMAL")
	private Animal animal;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_USER")
	private User responsibleUser;
	
}
