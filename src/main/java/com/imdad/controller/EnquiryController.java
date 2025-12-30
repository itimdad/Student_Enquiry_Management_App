package com.imdad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.imdad.binding.DashboardResponse;
import com.imdad.binding.EnquiryForm;
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
		
		model.addAttribute("dashboard", dashboardData);
		System.out.println(dashboardData);
		
		return "dashboard";
	}
	
	@GetMapping("/enquiry")
	public String addEnquiryPage(Model model) {
		
		List<String> courseName = enquiryService.getCourseName();
		List<String> enquiryStatus = enquiryService.getEnquiryStatus();
		model.addAttribute("courseName", courseName);
		
		model.addAttribute("statuses", enquiryStatus);
		
		model.addAttribute("enquiryForm", new EnquiryForm());
		System.out.println(courseName);
		return "add-enquiry";
	}
	
	@PostMapping("/enquiry")
	public String addEnquiries(@ModelAttribute("enquiryForm")  EnquiryForm form ,Model model) {
		 
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
	
	@GetMapping("/enquiries")
	public String viewEnquiriesPage() {
		return "view-enquiries";
	}
}
