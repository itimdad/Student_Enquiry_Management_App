package com.imdad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.imdad.binding.SignUpForm;
import com.imdad.binding.UnlockForm;
import com.imdad.service.UserService;

@Controller
public class UserController {


	
	@Autowired
	UserService service;
	
	@PostMapping("/signup")
	public String signup(@ModelAttribute("user") SignUpForm form, Model model) {
		
		boolean signup = service.signup(form);
		
		if(signup) {
			model.addAttribute("successMsg", "successfully registered , Check your email");
		}
		else {
			model.addAttribute("errMsg", "Your email is Already registered");
		}
		
		return "signup";
	}
	
	@PostMapping("/unlock")
	public String unlockUserAccount(@ModelAttribute("unlockForm") UnlockForm form, Model model) {
		
		boolean unlocked = service.unlockAccount(form);
		
		if(unlocked) {
			model.addAttribute("unlocked", "Your ACcount is unlockde");
		} else {
			model.addAttribute("unlocked", "Your password is mismatched");
		}
		
		return "unlock";
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/signup")
	public String signupPage(Model model) {
		
		model.addAttribute("user", new SignUpForm());
		return "signup";
	}
	
	@GetMapping("/forgot")
	public String forgotPage() {
		return "forgot";
	}
	
	@GetMapping("/unlock")
	public String unlockPage(@RequestParam String email, Model model) {
		
		model.addAttribute("userEmail", email);
		model.addAttribute("unlockForm", new UnlockForm());
		return "unlock";
	}
}
