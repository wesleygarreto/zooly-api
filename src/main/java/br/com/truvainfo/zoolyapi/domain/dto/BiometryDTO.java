package br.com.truvainfo.zoolyapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.*;

@Getter
@Setter
public class BiometryDTO implements Serializable {
	
	private Integer id;
	
	private Integer animalId;
	
	private Integer userId;
	
	private String weight;
	
	private String length;
	
	private String height;
	
	private String note;

	private String prescription;

	@JsonFormat(shape = STRING, pattern = "yyyy-MM-dd HH:mm")
	private Date creationDate;
	
}
