package com.imdad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imdad.entity.EnquiryStatus;

@Repository
public interface EnquiryStatusRepository extends JpaRepository<EnquiryStatus, Integer>{

}
