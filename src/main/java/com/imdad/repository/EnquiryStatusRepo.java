package com.imdad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.imdad.entity.EnquiryStatusEntity;

@Repository
public interface EnquiryStatusRepo extends JpaRepository<EnquiryStatusEntity, Integer>{

	@Query("SELECT statusName from EnquiryStatusEntity")
	public List<String> findStatuses();
}
