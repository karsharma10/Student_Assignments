package com.zeroDegree.StudentAssignments.services;

import com.zeroDegree.StudentAssignments.domains.entities.AssignmentEntity;

import java.util.List;
import java.util.Optional;

public interface AssignmentService {

    AssignmentEntity addAssignment(AssignmentEntity assignmentEntity);

    List<AssignmentEntity> getAllAssignments();

    AssignmentEntity updateAssignment(Long id, AssignmentEntity assignmentEntity);

    Optional<AssignmentEntity> findAssignmentById(Long id);

    void deleteAssignment(Long id);
}
