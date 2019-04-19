package com.messagingplatform.rest.restservice.repository;

import com.messagingplatform.rest.restservice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
