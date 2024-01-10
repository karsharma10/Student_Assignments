package com.zeroDegree.StudentAssignments.services;

import com.zeroDegree.StudentAssignments.domains.entities.StudentEntity;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    StudentEntity saveStudent(StudentEntity studentEntity);

    List<StudentEntity> getAllStudents();

    Optional<StudentEntity> findStudentById(Long id);

    StudentEntity updateStudent(Long id, StudentEntity studentEntity);

    void deleteStudent(Long id);
}
