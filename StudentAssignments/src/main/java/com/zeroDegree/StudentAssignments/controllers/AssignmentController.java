package com.zeroDegree.StudentAssignments.controllers;

import com.zeroDegree.StudentAssignments.domains.dtos.AssignmentDto;
import com.zeroDegree.StudentAssignments.domains.entities.AssignmentEntity;
import com.zeroDegree.StudentAssignments.mappers.Mapper;
import com.zeroDegree.StudentAssignments.services.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AssignmentController {

    private AssignmentService assignmentService;
    private Mapper<AssignmentEntity, AssignmentDto> assignmentMapper;

    public AssignmentController(AssignmentService assignmentService, Mapper<AssignmentEntity, AssignmentDto> assignmentMapper) {
        this.assignmentService = assignmentService;
        this.assignmentMapper = assignmentMapper;
    }

    @PostMapping(path = "/assignments")
    public ResponseEntity<AssignmentDto> addAssignment(@RequestBody AssignmentDto assignmentDto){
        AssignmentEntity convertedAssignment = assignmentMapper.mapFrom(assignmentDto);

        AssignmentEntity assignmentEntity = assignmentService.addAssignment(convertedAssignment);

        AssignmentDto returnAssignmentDto = assignmentMapper.mapTo(assignmentEntity);

        return new ResponseEntity<>(returnAssignmentDto, HttpStatus.CREATED);
    }

    @GetMapping(path = "/assignments")
    public List<AssignmentDto> getAllAssignments(){
        List<AssignmentEntity> allAssignmentsReturned = assignmentService.getAllAssignments();
        List<AssignmentDto> allAssignmentsConverted = allAssignmentsReturned.stream().map(assignmentMapper::mapTo).collect(Collectors.toList());

        return allAssignmentsConverted;
    }

    @PostMapping(path = "/assignments/{id}")
    public ResponseEntity<AssignmentDto> updateAssignment(@PathVariable Long id, @RequestBody AssignmentDto assignmentDto){
        Optional<AssignmentEntity> assignmentById = assignmentService.findAssignmentById(id);

        if(assignmentById.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            AssignmentEntity convertedAssignment = assignmentMapper.mapFrom(assignmentDto);
            AssignmentEntity updatedAssignmentEntity = assignmentService.updateAssignment(id, convertedAssignment);

            return new ResponseEntity<>(assignmentMapper.mapTo(updatedAssignmentEntity), HttpStatus.OK);
        }
    }
    @DeleteMapping(path = "/assignments/{id}")
    public ResponseEntity deleteAssignment(@PathVariable Long id){
        if(assignmentService.findAssignmentById(id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            assignmentService.deleteAssignment(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
