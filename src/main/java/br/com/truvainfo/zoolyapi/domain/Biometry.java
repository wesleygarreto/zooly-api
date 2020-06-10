package br.com.truvainfo.zoolyapi.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "ZOOLY", name = "BIOMETRY")
public class Biometry implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CD_BIOMETRY")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_ANIMAL")
	private Animal animal;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_USER")
	private User user;
	
	@Column(name = "WEIGHT")
	private String weight;
	
	@Column(name = "LENGTH")
	private String length;
	
	@Column(name = "HEIGHT")
	private String height;
	
	@Column(name = "NOTE")
	private String note;

	@Column(name = "PRESCRIPTION")
	private String prescription;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
}
