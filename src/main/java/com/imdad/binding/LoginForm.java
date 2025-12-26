package com.imdad.binding;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class LoginForm {
	
	private String userEmail;
	private String userPassword;

}
