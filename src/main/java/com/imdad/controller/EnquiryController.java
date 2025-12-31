package com.imdad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.imdad.binding.DashboardResponse;
import com.imdad.binding.EnquiryForm;
import com.imdad.binding.EnquirySearchCriteria;
import com.imdad.binding.UnlockForm;
import com.imdad.entity.StudentEnqEntity;
import com.imdad.service.EnquiryService;
import com.imdad.service.EnquiryServiceImpl;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {

   

	
	@Autowired
	HttpSession session;
	
	@Autowired
	EnquiryService enquiryService;

	

	@GetMapping("/dashboard")
	public String loadDashBoard(Model model) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		DashboardResponse dashboardData = enquiryService.dashboardData(userId);
		
		if(dashboardData == null) {
			
			return "redirect:/login";
		}
		
		model.addAttribute("dashboard", dashboardData);
		System.out.println(dashboardData);
		
		return "dashboard";
	}
	
	@GetMapping("/enquiry")
	public String addEnquiryPage(Model model) {
		
		init(model);
		return "add-enquiry";
	}
	
	@PostMapping("/enquiry")
	public String addEnquiries(@ModelAttribute("enquiryForm")  EnquiryForm form ,Model model) {
		
		init(model);
		 
		Integer userId = (Integer)session.getAttribute("userId");
		
		boolean status = enquiryService.addEnquiry(form, userId);
		
		if(status) {
			model.addAttribute("successMsg", "Record Saved Successfully");
		}
		else {
			model.addAttribute("errMsg", "Something wrong");
		}
		
		return "add-enquiry";
	}

	private void init(Model model) {
		//getting course name from database
		List<String> courseName = enquiryService.getCourseName();
		
		//getting enquires status from database
		List<String> enquiryStatus = enquiryService.getEnquiryStatus();
		
		//sending courses to the UI
		model.addAttribute("courseName", courseName);
		
		//sending status to the UI
		model.addAttribute("statuses", enquiryStatus);
		
		//sending binding object to the UI
		model.addAttribute("enquiryForm", new EnquiryForm());
	}
	
	@GetMapping("/enquiries")
	public String viewEnquiriesPage( Model model) {
		
		List<StudentEnqEntity> viewEnquiry = enquiryService.viewAllEnquiry();
		
		model.addAttribute("viewEnquiry", viewEnquiry);
		
		return "view-enquiries";
	}
	
	@GetMapping("filter-enquiries")
	public String getFilteredEnquiry(@RequestParam String cname, 
			@RequestParam String status,
			@RequestParam String mode , Model model) {
		
		EnquirySearchCriteria criteria = new EnquirySearchCriteria();
		criteria.setSearchClassMode(mode);
		criteria.setSearchEnqStatus(status);
		criteria.setSearchCourse(cname);
		
		System.out.println(criteria);
		
		List<StudentEnqEntity> filteredEnquiries = enquiryService.getFilteredEnquiries(criteria);
		
		model.addAttribute("filteredEnquiries", filteredEnquiries);
		
		return "filter-enquiries-page";
	}
}
