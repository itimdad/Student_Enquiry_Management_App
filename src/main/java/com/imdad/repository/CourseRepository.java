package com.imdad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imdad.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{

}
