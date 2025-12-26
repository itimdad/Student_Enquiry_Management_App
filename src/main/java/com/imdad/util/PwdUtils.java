package com.imdad.util;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class PwdUtils {
	
	public String generatePwd() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();

        StringBuilder password = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        
        return password.toString();
	}

}
