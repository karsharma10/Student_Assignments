package com.zeroDegree.StudentAssignments.services.implementations;

import com.zeroDegree.StudentAssignments.domains.entities.AssignmentEntity;
import com.zeroDegree.StudentAssignments.repositories.AssignmentRepository;
import com.zeroDegree.StudentAssignments.services.AssignmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AssignmentServiceImpl implements AssignmentService {
    private AssignmentRepository assignmentRepository;

    public AssignmentServiceImpl(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public AssignmentEntity addAssignment(AssignmentEntity assignmentEntity) {
        return assignmentRepository.save(assignmentEntity);
    }

    @Override
    public List<AssignmentEntity> getAllAssignments() {
        Iterable<AssignmentEntity> allAssignments = assignmentRepository.findAll();
        return StreamSupport.stream(allAssignments.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public AssignmentEntity updateAssignment(Long id, AssignmentEntity assignmentEntity) {
        assignmentEntity.setId(id);
        return assignmentRepository.save(assignmentEntity);
    }

    @Override
    public Optional<AssignmentEntity> findAssignmentById(Long id) {
        return assignmentRepository.findById(id);
    }

    @Override
    public void deleteAssignment(Long id) {
        assignmentRepository.deleteById(id);
    }

}
