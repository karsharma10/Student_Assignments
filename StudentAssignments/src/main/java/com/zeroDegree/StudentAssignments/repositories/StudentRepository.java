package com.zeroDegree.StudentAssignments.repositories;

import com.zeroDegree.StudentAssignments.domains.entities.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
}
