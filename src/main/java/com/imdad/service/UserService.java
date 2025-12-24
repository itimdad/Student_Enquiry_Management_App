package com.imdad.service;

import com.imdad.binding.DashboardResponse;
import com.imdad.binding.LoginForm;
import com.imdad.binding.SignUpForm;
import com.imdad.binding.UnlockForm;

public interface UserService {

	public boolean registerUser(SignUpForm signUpForm);
	
	public boolean loginUser(LoginForm loginForm);
	
	public boolean unlockAccount(UnlockForm unlockForm);
	
	public boolean forgotPassword(String email);
	
	public DashboardResponse dashboardData();
}
