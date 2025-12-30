package com.imdad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imdad.entity.StudentEnqEntity;

@Repository
public interface StudentDtlsRepo extends JpaRepository<StudentEnqEntity, Integer>{

	List<StudentEnqEntity> findByUser_UserId(Integer userId);

}
