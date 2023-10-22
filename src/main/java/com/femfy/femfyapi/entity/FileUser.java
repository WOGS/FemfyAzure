package com.femfy.femfyapi.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity

@Table(name="fileuser")
public class FileUser {
	
	public FileUser() {
		
	}
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private Long idUser;
	private String fileName;
	private String fileExt;
	private Date studyDate;
	private String description;

    @ManyToOne
    @JoinColumn(name = "tipo_estudios_id")
    private TypeStudy typeStudy;

}