package com.zeroDegree.StudentAssignments.controllers;

import com.zeroDegree.StudentAssignments.domains.dtos.StudentDto;
import com.zeroDegree.StudentAssignments.domains.entities.StudentEntity;
import com.zeroDegree.StudentAssignments.mappers.Mapper;
import com.zeroDegree.StudentAssignments.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class StudentController {

    private Mapper<StudentEntity, StudentDto> studentMapper;
    private StudentService studentService;

    public StudentController(Mapper<StudentEntity, StudentDto> studentMapper, StudentService studentService){
        this.studentMapper = studentMapper;
        this.studentService = studentService;
    }


    @PostMapping(path = "/students")
    public ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto studentDto){
        StudentEntity convertedStudent = studentMapper.mapFrom(studentDto);

        StudentEntity returnedStudent = studentService.saveStudent(convertedStudent);

        StudentDto returnStudent = studentMapper.mapTo(returnedStudent);

        return new ResponseEntity<>(returnStudent, HttpStatus.CREATED);
    }

    @GetMapping(path = "/students")
    public List<StudentDto> getAllStudents(){
        List<StudentEntity> allStudentsEntity = studentService.getAllStudents();
        List<StudentDto> returnStudents = allStudentsEntity.stream().map(studentMapper::mapTo).collect(Collectors.toList());

        return returnStudents;
    }

    @PostMapping(path = "/students/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto){
        Optional<StudentEntity> findStudentEntity = studentService.findStudentById(id);

        if(findStudentEntity.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            StudentEntity convertedStudent = studentMapper.mapFrom(studentDto);
            StudentEntity returnStudentEntity = studentService.updateStudent(id, convertedStudent);
            return new ResponseEntity<>(studentMapper.mapTo(returnStudentEntity), HttpStatus.OK);
        }

    }

    @DeleteMapping(path = "/students/{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id){
        if(studentService.findStudentById(id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            studentService.deleteStudent(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
