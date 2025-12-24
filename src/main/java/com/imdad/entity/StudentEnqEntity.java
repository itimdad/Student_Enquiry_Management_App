package com.imdad.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "AIT_STUDENT_ENQUIRIES")
public class StudentEnqEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enquiryId;
	private String studentName;
	private Double phno;
	private String classMode;
	private String courseName;
	private String enquiryStatus;
	private LocalDate createdDate;
	private LocalDate updatedDate;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private UserDtlsEntity user;
}
