package com.imdad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import com.imdad.binding.LoginForm;
import com.imdad.binding.SignUpForm;
import com.imdad.binding.UnlockForm;
import com.imdad.service.UserService;

@Controller
public class UserController {


	@Autowired
	UserService service;

	
	@GetMapping("/signup")
	public String signupPage(Model model) {
		
		model.addAttribute("user", new SignUpForm());
		return "signup";
	}
	
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
	
	@GetMapping("/unlock")
	public String unlockPage(@RequestParam String email, Model model) {
		
		UnlockForm unlockForm = new UnlockForm();
		unlockForm.setEmail(email);
		model.addAttribute("unlockForm", unlockForm);
		return "unlock";
	}
	
	@PostMapping("/unlock")
	public String unlockUserAccount(@ModelAttribute("unlockForm") UnlockForm form, Model model) {
		
		if(form.getNewPwd().equals(form.getConfirmPwd())) {
			
			boolean unlocked = service.unlockAccount(form);
	
			if(unlocked) {
				model.addAttribute("unlocked", "Your Account is unlocked");
			} else {
				model.addAttribute("errMsg", "Your temporary password is incorrect check again");
			}
		} else {
			model.addAttribute("errMsg", "Your password not matched");
		}
		
		
		return "unlock";
	}

	@GetMapping("/login")
	public String loginPage(Model model) {
		
		model.addAttribute("loginForm", new LoginForm());

		return "login";
	}
	
	@PostMapping("/login")
	public String loginUser(@ModelAttribute("loginForm") LoginForm form, Model model) {
	
		String status = service.loginUser(form);
		
		if(status != "success") {
			model.addAttribute("errMsg", status);
			
			return "login";
		}
		
		return "redirect:/dashboard";
	}
	
	
	@GetMapping("/forgot")
	public String forgotPage() {
		return "forgot";
	}
	
	@GetMapping("/forgotPassword")
	public String forgotPassword(@RequestParam String email, Model model) {
		
    String status = service.forgotPassword(email);
		
		if(status.equals("success")) {
			model.addAttribute("success", "Your password sent check your email");
		} 
		else {
			model.addAttribute("errMsg", status);
		}
		
		return "forgot";
	}
	
}
