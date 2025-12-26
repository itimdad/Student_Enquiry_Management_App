package com.imdad.binding;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class DashboardResponse {

	Integer totalEnquiries;
	Integer enrolledCnt;
	Integer lostCnt;
}
