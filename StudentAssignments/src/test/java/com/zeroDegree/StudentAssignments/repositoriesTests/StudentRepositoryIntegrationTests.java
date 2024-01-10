package com.zeroDegree.StudentAssignments.repositoriesTests;

import com.zeroDegree.StudentAssignments.Utils.TestData;
import com.zeroDegree.StudentAssignments.domains.entities.StudentEntity;
import com.zeroDegree.StudentAssignments.repositories.StudentRepository;
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
public class StudentRepositoryIntegrationTests {

    private StudentRepository studentRepository;

    @Autowired
    public StudentRepositoryIntegrationTests(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Test
    public void testThatStudentRepositoryCorrectlyAddsAndFetchesStudent(){
        StudentEntity testStudentA = TestData.createStudentEntityA(); //create

        studentRepository.save(testStudentA); //save

        Optional<StudentEntity> fetchStudentA = studentRepository.findById(testStudentA.getId()); //find

        assertThat(fetchStudentA).isPresent(); //make sure is present
        assertThat(fetchStudentA.get()).isEqualTo(testStudentA); //check if equal to what is created
    }

    @Test
    public void testThatStudentRepositoryCorrectlyAddsAndUpdatesStudent(){
        StudentEntity testStudentA = TestData.createStudentEntityA();

        studentRepository.save(testStudentA);

        testStudentA.setName("Updated");

        studentRepository.save(testStudentA);

        Optional<StudentEntity> fetchStudentA = studentRepository.findById(testStudentA.getId());

        assertThat(fetchStudentA).isPresent();
        assertThat(fetchStudentA.get().getName()).isEqualTo("Updated");

    }

    @Test
    public void testThatStudentRepositoryCorrectAddsAndDeletesStudent(){
        StudentEntity testStudentA = TestData.createStudentEntityA();

        studentRepository.save(testStudentA);

        studentRepository.deleteById(testStudentA.getId());

        Optional<StudentEntity> findStudent = studentRepository.findById(testStudentA.getId());

        assertThat(findStudent).isNotPresent();
    }

}
