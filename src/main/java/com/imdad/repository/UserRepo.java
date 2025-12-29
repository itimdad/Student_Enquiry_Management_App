package com.imdad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.imdad.entity.UserDtlsEntity;

@Repository
public interface UserRepo extends JpaRepository<UserDtlsEntity, Integer>{

	public UserDtlsEntity findByEmail(String email);
	public UserDtlsEntity findByEmailAndPassword(String email, String password);
	
}
