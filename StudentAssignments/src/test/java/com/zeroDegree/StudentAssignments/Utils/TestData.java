package com.zeroDegree.StudentAssignments.Utils;

import com.zeroDegree.StudentAssignments.domains.dtos.AssignmentDto;
import com.zeroDegree.StudentAssignments.domains.dtos.StudentDto;
import com.zeroDegree.StudentAssignments.domains.entities.AssignmentEntity;
import com.zeroDegree.StudentAssignments.domains.entities.StudentEntity;

public class TestData {

    public static StudentEntity createStudentEntityA(){
        return StudentEntity.builder()
                .id(1L)
                .name("Test Student A")
                .age(23)
                .build();
    }
    public static StudentEntity createStudentEntityB(){
        return StudentEntity.builder()
                .id(2L)
                .name("Test Student B")
                .age(33)
                .build();
    }

    public static StudentDto createStudentDtoA(){
        return StudentDto.builder()
                .id(1L)
                .name("Test Student A")
                .age(23)
                .build();
    }

    public static StudentDto createStudentDtoB(){
        return StudentDto.builder()
                .id(2L)
                .name("Test Student B")
                .age(33)
                .build();
    }

    public static AssignmentEntity createAssignmentEntityA(StudentEntity studentEntity){
        return AssignmentEntity.builder()
                .id(1L)
                .name("Test Assignment")
                .className("Test Class")
                .studentEntity(studentEntity)
                .build();
    }

    public static AssignmentDto createAssignmentDtoA(StudentDto studentDto){
        return AssignmentDto.builder()
                .id(1L)
                .name("Test Assignment")
                .className("Test Class")
                .studentDto(studentDto)
                .build();
    }


}
