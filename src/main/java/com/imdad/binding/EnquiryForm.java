package com.imdad.binding;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class EnquiryForm {

	private Integer enquiryId;
	private String studentName;
	private String studentPhno;
	private String classMode;
	private String courseName;
	private String enquiryStatus;
}
