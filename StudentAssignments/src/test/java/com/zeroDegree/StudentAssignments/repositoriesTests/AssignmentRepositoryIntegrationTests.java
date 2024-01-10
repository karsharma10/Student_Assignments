package com.zeroDegree.StudentAssignments.repositoriesTests;

import com.zeroDegree.StudentAssignments.Utils.TestData;
import com.zeroDegree.StudentAssignments.domains.entities.AssignmentEntity;
import com.zeroDegree.StudentAssignments.repositories.AssignmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AssignmentRepositoryIntegrationTests {

    private AssignmentRepository assignmentRepository;

    @Autowired
    public AssignmentRepositoryIntegrationTests(AssignmentRepository assignmentRepository){
        this.assignmentRepository = assignmentRepository;
    }

    @Test
    public void assignmentRepositoryCorrectlyCreatesAndFetchesAssignment(){
        AssignmentEntity testAssignmentA = TestData.createAssignmentEntityA(null);

        assignmentRepository.save(testAssignmentA);

        Optional<AssignmentEntity> findTestA = assignmentRepository.findById(testAssignmentA.getId());

        assertThat(findTestA).isPresent();
        assertThat(findTestA.get()).isEqualTo(testAssignmentA);
    }

    @Test
    public void assignmentRepositoryCanBeCreatedAndUpdated(){
        AssignmentEntity testAssignmentA = TestData.createAssignmentEntityA(null);

        assignmentRepository.save(testAssignmentA);

        testAssignmentA.setName("Updated");

        assignmentRepository.save(testAssignmentA);

        Optional<AssignmentEntity> findTestA = assignmentRepository.findById(testAssignmentA.getId());

        assertThat(findTestA).isPresent();
        assertThat(findTestA.get().getName()).isEqualTo("Updated");
    }

    @Test
    public void assignmentRepositoryCanBeCreatedAndDeleted(){
        AssignmentEntity testAssignmentA = TestData.createAssignmentEntityA(null);

        assignmentRepository.save(testAssignmentA);

        assignmentRepository.deleteById(testAssignmentA.getId());

        Optional<AssignmentEntity> findById = assignmentRepository.findById(testAssignmentA.getId());

        assertThat(findById).isNotPresent();

    }


}
