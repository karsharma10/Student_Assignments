package com.zeroDegree.StudentAssignments.mapperTests;

import com.zeroDegree.StudentAssignments.Utils.TestData;
import com.zeroDegree.StudentAssignments.domains.dtos.StudentDto;
import com.zeroDegree.StudentAssignments.domains.entities.StudentEntity;
import com.zeroDegree.StudentAssignments.mappers.Mapper;
import com.zeroDegree.StudentAssignments.mappers.implementations.StudentMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class StudentMapperIntegrationTests {

    private Mapper<StudentEntity, StudentDto> studentMapper;

    @Autowired
    public StudentMapperIntegrationTests(Mapper<StudentEntity, StudentDto> studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Test
    public void studentMapperCorrectlyMapsFromDtoToEntity(){
        StudentEntity checkStudentEntityA = TestData.createStudentEntityA();
        StudentDto testStudentDtoA = TestData.createStudentDtoA();

        StudentEntity testStudentEntity = studentMapper.mapFrom(testStudentDtoA);

        assertThat(testStudentEntity).isEqualTo(checkStudentEntityA);

    }

    @Test
    public void studentMapperCorrectlyMapsFromEntityToDto(){
        StudentEntity StudentEntityA = TestData.createStudentEntityA();
        StudentDto studentDtoA = TestData.createStudentDtoA();

        StudentDto testStudentDtoA = studentMapper.mapTo(StudentEntityA);

        assertThat(testStudentDtoA).isEqualTo(studentDtoA);
    }


}
