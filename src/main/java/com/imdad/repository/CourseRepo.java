package com.imdad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imdad.entity.CourseEntity;

@Repository
public interface CourseRepo extends JpaRepository<CourseEntity, Integer>{

}
