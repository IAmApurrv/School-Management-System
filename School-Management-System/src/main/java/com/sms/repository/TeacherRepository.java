package com.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sms.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
