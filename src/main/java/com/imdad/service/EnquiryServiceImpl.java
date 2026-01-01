package com.imdad.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import jakarta.servlet.http.HttpSession;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	StudentDtlsRepo studentDtlsRepo;

	@Autowired
	CourseRepo courseRepo;

	@Autowired
	EnquiryStatusRepo enquiryStatusRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	HttpSession session;

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
	public List<StudentEnqEntity> viewAllEnquiry() {
		// TODO Auto-generated method stub

		List<StudentEnqEntity> all = studentDtlsRepo.findAll();

		return all;
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

		DashboardResponse response = new DashboardResponse();

		Optional<UserDtlsEntity> byId = userRepo.findById(userId);

		if (byId.isPresent()) {
			UserDtlsEntity userDtlsEntity = byId.get();

			List<StudentEnqEntity> enquiries = userDtlsEntity.getEnquiries();

			long lostEnquiry = enquiries.stream().filter(e -> "LOST".equalsIgnoreCase(e.getEnquiryStatus())).count();

			long enrolledEnquiry = enquiries.stream().filter(e -> "ENROLLED".equalsIgnoreCase(e.getEnquiryStatus()))
					.count();

			response.setTotalEnquiries(enquiries.size());
			response.setEnrolledCnt(enrolledEnquiry);
			response.setLostCnt(lostEnquiry);

			return response;
		}

		return null;
	}

	@Override
	public EnquiryForm getEnquiry(Integer enqId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentEnqEntity> getFilteredEnquiries(EnquirySearchCriteria criteria) {
		// TODO Auto-generated method stub

		Integer userId = (Integer) session.getAttribute("userId");

		List<StudentEnqEntity> enquiries = studentDtlsRepo.findByUser_UserId(userId);

		// we will use streams to find the data as per user search criteria

		if (criteria.getSearchCourse() != null && !criteria.getSearchCourse().equals("")) {

			enquiries = enquiries.stream().filter(e -> criteria.getSearchCourse().equals(e.getCourseName()))
					.collect(Collectors.toList());
		}

		if (criteria.getSearchClassMode() != null && !criteria.getSearchClassMode().equals("")) {

			enquiries = enquiries.stream().filter(e -> criteria.getSearchClassMode().equals(e.getClassMode()))
					.collect(Collectors.toList());

		}

		if (criteria.getSearchEnqStatus() != null && !criteria.getSearchEnqStatus().equals("")) {

			enquiries = enquiries.stream().filter(e -> criteria.getSearchEnqStatus().equals(e.getEnquiryStatus()))
					.collect(Collectors.toList());
		}
		
		return enquiries;
	}

	@Override
	public EnquiryForm editStudentEnquiry(Integer enquiryId) {
		// TODO Auto-generated method stub
		
		StudentEnqEntity studentEnqEntity = studentDtlsRepo.findById(enquiryId).get();
		
		EnquiryForm form = new EnquiryForm();
		
		BeanUtils.copyProperties(studentEnqEntity, form);
		
		return form;
	}

}
