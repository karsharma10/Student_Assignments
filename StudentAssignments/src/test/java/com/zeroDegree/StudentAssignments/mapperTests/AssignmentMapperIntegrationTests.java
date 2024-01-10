package com.zeroDegree.StudentAssignments.mapperTests;

import com.zeroDegree.StudentAssignments.Utils.TestData;
import com.zeroDegree.StudentAssignments.domains.dtos.AssignmentDto;
import com.zeroDegree.StudentAssignments.domains.entities.AssignmentEntity;
import com.zeroDegree.StudentAssignments.mappers.Mapper;
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
public class AssignmentMapperIntegrationTests {

    private Mapper<AssignmentEntity, AssignmentDto> assignmentMapper;

    @Autowired
    public AssignmentMapperIntegrationTests(Mapper<AssignmentEntity, AssignmentDto> assignmentMapper){
        this.assignmentMapper = assignmentMapper;
    }

    @Test
    public void testThatAssignmentMapperCorrectlyMapsDtoToEntity(){
        AssignmentDto testAssignmentDtoA = TestData.createAssignmentDtoA(null);

        AssignmentEntity convertedAssignment = assignmentMapper.mapFrom(testAssignmentDtoA);

        AssignmentEntity checkAssingmentEntity = TestData.createAssignmentEntityA(null);

        assertThat(convertedAssignment).isEqualTo(checkAssingmentEntity);
    }

    @Test
    public void testThatAssignmentMapperCorrectlyMapsEntityToDto(){
        AssignmentDto testAssignmentCheck = TestData.createAssignmentDtoA(null);
        AssignmentEntity testAssignmentDtoEntity = TestData.createAssignmentEntityA(null);

        AssignmentDto convertedFromEntityAssignment = assignmentMapper.mapTo(testAssignmentDtoEntity);

        assertThat(testAssignmentCheck).isEqualTo(convertedFromEntityAssignment);

    }
}
