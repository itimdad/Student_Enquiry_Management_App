package com.imdad.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "AIT_USER_DTLS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String userName;
	@Column(unique = true)
	private String email;
	private Double phno;
	private Double password;
	private String accountStatus;
	
	@OneToMany(mappedBy = "user")
	private StudentEnquiry enquiry;

}
