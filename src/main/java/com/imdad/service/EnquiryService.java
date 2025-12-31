package com.imdad.service;

import java.util.List;

import com.imdad.binding.DashboardResponse;
import com.imdad.binding.EnquiryForm;
import com.imdad.binding.EnquirySearchCriteria;
import com.imdad.entity.StudentEnqEntity;

public interface EnquiryService {
	
	public List<String> getCourseName();
	
	public List<String> getEnquiryStatus();
	
	public List<StudentEnqEntity> viewAllEnquiry();
	
	public boolean addEnquiry(EnquiryForm enquiryForm, Integer userId);
	
	public DashboardResponse dashboardData(Integer userId);
	
	public EnquiryForm getEnquiry(Integer enqId);
	
	public List<StudentEnqEntity> getFilteredEnquiries(EnquirySearchCriteria criteria);

}
