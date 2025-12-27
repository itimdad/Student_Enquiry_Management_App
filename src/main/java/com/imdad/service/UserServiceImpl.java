package com.imdad.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imdad.binding.LoginForm;
import com.imdad.binding.SignUpForm;
import com.imdad.binding.UnlockForm;
import com.imdad.entity.UserDtlsEntity;
import com.imdad.repository.UserRepo;
import com.imdad.util.EmailUtils;
import com.imdad.util.PwdUtils;

import jakarta.mail.MessagingException;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	PwdUtils pwdUtils;
	
	@Autowired
	UserRepo repo;
	
	@Autowired
	EmailUtils emailUtils;



	@Override
	public boolean signup(SignUpForm signUpForm) {
		// TODO Auto-generated method stub
		
		//Check email is already registered or not
		UserDtlsEntity byEmail = repo.findByEmail(signUpForm.getEmail());
		
		if(byEmail != null) {
			return false;
		}
		
		//TODO: Generate OTP 
		String password = pwdUtils.generatePwd();
		
		//TODO: capture data from sign up  form to user entity		
		UserDtlsEntity entity = new UserDtlsEntity();
		
		BeanUtils.copyProperties(signUpForm, entity);
		
		entity.setPassword(password);
		
		//set account status as LOCKED;
		entity.setAccountStatus("LOCKED");
		
		//TODO: save Data
		repo.save(entity);
		
		//TODO: send email
		
		String to = signUpForm.getEmail();
		String subject = "Unlock Your Account";
		
		StringBuffer body = new StringBuffer();
		
		body.append("We are sending you the email for unlock the account");
		body.append("</br>");
		body.append("Temporary Password: " + password);
		body.append("</br>");
		
		String unlockLink =
		        "http://localhost:8080/unlock?email=" + signUpForm.getEmail();
		body.append("<a href='" + unlockLink + "'>Unlock Account</a>");
		
		   

		
		boolean isSend = false;
		
		try {
			emailUtils.sendEmail(to, subject, body.toString());
			isSend = true;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isSend;
	}

	@Override
	public boolean loginUser(LoginForm loginForm) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean unlockAccount(UnlockForm unlockForm) {
		
		//find data by password
		String tempPwd = unlockForm.getTempPwd();
		UserDtlsEntity entity = repo.findByPassword(tempPwd);
		
		//checking new password and confirm password are equals or not 
		if(!unlockForm.getNewPwd().equals(unlockForm.getConfirmPwd())) {
			return false;
		}
		
		if(entity == null) {
			return false;
		}
		entity.setPassword(unlockForm.getNewPwd());
		entity.setAccountStatus("UNLOCKED");
		
		repo.save(entity);
		
		return true;
	}

	@Override
	public boolean forgotPassword(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
