package com.zeroDegree.StudentAssignments.repositories;

import com.zeroDegree.StudentAssignments.domains.entities.AssignmentEntity;
import org.springframework.data.repository.CrudRepository;

public interface AssignmentRepository extends CrudRepository<AssignmentEntity, Long> {
}
