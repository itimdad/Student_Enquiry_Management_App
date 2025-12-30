package com.imdad.runner;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.imdad.entity.CourseEntity;
import com.imdad.entity.EnquiryStatusEntity;
import com.imdad.repository.CourseRepo;
import com.imdad.repository.EnquiryStatusRepo;

@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	EnquiryStatusRepo enquiryStatusRepo;

	@Autowired
	CourseRepo courseRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {

		courseRepo.deleteAll();
		enquiryStatusRepo.deleteAll();
		
		CourseEntity course1 = new CourseEntity();
		course1.setCourseName("Java");
		
		CourseEntity course2 = new CourseEntity();
		course2.setCourseName("DevOps");
		
		CourseEntity course3 = new CourseEntity();
		course3.setCourseName("Angular");
		
		List<CourseEntity> courses = Arrays.asList(course1, course2, course3);
		
		EnquiryStatusEntity status1 = new EnquiryStatusEntity();
		status1.setStatusName("LOST");
		
		EnquiryStatusEntity status2 = new EnquiryStatusEntity();
		status2.setStatusName("ENROLLED");
		
		EnquiryStatusEntity status3 = new EnquiryStatusEntity();
		status3.setStatusName("NEW");
		
		
		List<EnquiryStatusEntity> statuses = Arrays.asList(status1, status2, status3);

		courseRepo.saveAll(courses);
		enquiryStatusRepo.saveAll(statuses);		
	}

}
