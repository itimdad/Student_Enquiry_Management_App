package com.imdad.binding;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class EnquirySearchCriteria {
	
	private String searchCourse;
	private String searchEnqStatus;
	private String searchClassMode;

}
