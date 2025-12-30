package com.imdad.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imdad.binding.DashboardResponse;
import com.imdad.binding.EnquiryForm;
import com.imdad.binding.EnquirySearchCriteria;
import com.imdad.entity.StudentEnqEntity;
import com.imdad.entity.UserDtlsEntity;
import com.imdad.repository.CourseRepo;
import com.imdad.repository.EnquiryStatusRepo;
import com.imdad.repository.StudentDtlsRepo;
import com.imdad.repository.UserRepo;

@Service
public class EnquiryServiceImpl implements EnquiryService{


	@Autowired
	StudentDtlsRepo studentDtlsRepo;
	
	@Autowired
	CourseRepo courseRepo;
	
	@Autowired
	EnquiryStatusRepo enquiryStatusRepo;
	
	@Autowired
	UserRepo userRepo;

	
	@Override
	public List<String> getCourseName() {
		// TODO Auto-generated method stub
		
		List<String> courseName = courseRepo.findCourseName();
		
		return courseName;
	}

	@Override
	public List<String> getEnquiryStatus() {
		// TODO Auto-generated method stub
		
		List<String> statuses = enquiryStatusRepo.findStatuses();
		return statuses;
	}

	@Override
	public List<StudentEnqEntity> viewEnquiry(EnquirySearchCriteria enquirySearchCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addEnquiry(EnquiryForm enquiryForm, Integer userId) {
		// TODO Auto-generated method stub
		
		UserDtlsEntity user = userRepo.findById(userId).get();
		
		StudentEnqEntity entity = new StudentEnqEntity();
		
		BeanUtils.copyProperties(enquiryForm, entity);
		entity.setUser(user);
		
		studentDtlsRepo.save(entity);
		
		return true;
	}

	@Override
	public DashboardResponse dashboardData(Integer userId) {
		// TODO Auto-generated method stub
		
		//get user data by taking data from session
		//and have to send to the data to the dashboard
		
		List<StudentEnqEntity> enquiries = studentDtlsRepo.findByUser_UserId(userId);
		
		
		long lostEnquiry = enquiries.stream().filter(e -> "LOST".equalsIgnoreCase(e.getEnquiryStatus())).count();
		
		long enrolledEnquiry = enquiries.stream().filter(e -> "ENROLLED".equalsIgnoreCase(e.getEnquiryStatus())).count();
		
		DashboardResponse response = new DashboardResponse();
		
		response.setTotalEnquiries(enquiries.size());
		response.setEnrolledCnt(enrolledEnquiry);
		response.setLostCnt(lostEnquiry);
		

		return response;
	}

	@Override
	public EnquiryForm getEnquiry(Integer enqId) {
		// TODO Auto-generated method stub
		return null;
	}

}
