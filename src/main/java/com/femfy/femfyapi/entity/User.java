package com.femfy.femfyapi.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity

@Table(name="data_user")
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
    @OneToOne
    @JoinColumn(name = "type_user_id")
    private TypeUser typeUser;

	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private Boolean isSuscriptor;
	@javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
	private Date birthdate;
	private String phone;
	@Column(name="mailAddress",unique = true, nullable = false)
	private String email;
	private String emotion;
	private String localidad;
	private String avatar;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CalendarEvent> calendarEvents;

	public User() {
		
	}
}
