package com.imdad.binding;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class UnlockForm {
	
	private String email;
	private String tempPwd;
	private String newPwd;
	private String confirmPwd;

}
