package com.zeroDegree.StudentAssignments.mappers.implementations;

import com.zeroDegree.StudentAssignments.domains.dtos.StudentDto;
import com.zeroDegree.StudentAssignments.domains.entities.StudentEntity;
import com.zeroDegree.StudentAssignments.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper implements Mapper<StudentEntity, StudentDto> {

    private ModelMapper modelMapper;

    public StudentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public StudentDto mapTo(StudentEntity studentEntity) {
        return modelMapper.map(studentEntity, StudentDto.class);
    }

    @Override
    public StudentEntity mapFrom(StudentDto studentDto) {
        return modelMapper.map(studentDto, StudentEntity.class);
    }
}
