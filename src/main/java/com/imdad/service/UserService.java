package com.imdad.service;

import com.imdad.binding.LoginForm;
import com.imdad.binding.SignUpForm;
import com.imdad.binding.UnlockForm;

public interface UserService {

	public boolean signup(SignUpForm signUpForm);
	
	public String loginUser(LoginForm loginForm);
	
	public boolean unlockAccount(UnlockForm unlockForm);
	
	public String forgotPassword(String email);
	
}
