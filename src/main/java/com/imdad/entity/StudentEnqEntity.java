package com.imdad.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;
@Entity
@Data
@ToString(exclude = "user")
@Table(name = "AIT_STUDENT_ENQUIRIES")
public class StudentEnqEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer enquiryId;

    private String studentName;
    private String studentPhno;
    private String classMode;
    private String courseName;
    private String enquiryStatus;

    @CreationTimestamp
    private LocalDate createdDate;

    @UpdateTimestamp
    private LocalDate updatedDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserDtlsEntity user;
}
