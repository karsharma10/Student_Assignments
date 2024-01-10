package com.zeroDegree.StudentAssignments.services.implementations;

import com.zeroDegree.StudentAssignments.domains.entities.StudentEntity;
import com.zeroDegree.StudentAssignments.repositories.StudentRepository;
import com.zeroDegree.StudentAssignments.services.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    @Override
    public StudentEntity saveStudent(StudentEntity studentEntity) {
        return studentRepository.save(studentEntity);
    }

    @Override
    public List<StudentEntity> getAllStudents() {
        Iterable<StudentEntity> findAllStudentsIterable = studentRepository.findAll();
        List<StudentEntity> returnStudents =  StreamSupport.stream(findAllStudentsIterable.spliterator(), false).collect(Collectors.toList());
        return returnStudents;
    }

    @Override
    public Optional<StudentEntity> findStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public StudentEntity updateStudent(Long id, StudentEntity studentEntity) {
        studentEntity.setId(id);
        return studentRepository.save(studentEntity);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
