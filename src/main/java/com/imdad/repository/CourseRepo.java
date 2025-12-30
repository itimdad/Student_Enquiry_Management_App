package com.imdad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.imdad.entity.CourseEntity;

@Repository
public interface CourseRepo extends JpaRepository<CourseEntity, Integer>{

	@Query("SELECT courseName from CourseEntity")
	public List<String> findCourseName();
}
