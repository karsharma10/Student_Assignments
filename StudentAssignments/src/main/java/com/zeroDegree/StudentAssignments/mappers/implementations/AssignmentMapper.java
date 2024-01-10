package com.zeroDegree.StudentAssignments.mappers.implementations;

import com.zeroDegree.StudentAssignments.domains.dtos.AssignmentDto;
import com.zeroDegree.StudentAssignments.domains.entities.AssignmentEntity;
import com.zeroDegree.StudentAssignments.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AssignmentMapper implements Mapper<AssignmentEntity, AssignmentDto> {

    private ModelMapper modelMapper;

    public AssignmentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AssignmentDto mapTo(AssignmentEntity assignmentEntity) {
        return modelMapper.map(assignmentEntity, AssignmentDto.class);
    }

    @Override
    public AssignmentEntity mapFrom(AssignmentDto assignmentDto) {
        return modelMapper.map(assignmentDto, AssignmentEntity.class);
    }
}
