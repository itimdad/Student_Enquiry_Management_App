package com.imdad.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.imdad.binding.DashboardResponse;
import com.imdad.binding.EnquiryForm;
import com.imdad.binding.EnquirySearchCriteria;
import com.imdad.entity.StudentEnqEntity;

@Service
public class EnquiryServiceImpl implements EnquiryService{

	@Override
	public List<String> getCourseName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getEnquiryStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentEnqEntity> viewEnquiry(EnquirySearchCriteria enquirySearchCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addEnquiry(EnquiryForm enquiryForm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<DashboardResponse> dashboardData(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnquiryForm getEnquiry(Integer enqId) {
		// TODO Auto-generated method stub
		return null;
	}

}
