package com.zeroDegree.StudentAssignments.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeroDegree.StudentAssignments.Utils.TestData;
import com.zeroDegree.StudentAssignments.domains.dtos.AssignmentDto;
import com.zeroDegree.StudentAssignments.domains.dtos.StudentDto;
import com.zeroDegree.StudentAssignments.domains.entities.AssignmentEntity;
import com.zeroDegree.StudentAssignments.domains.entities.StudentEntity;
import com.zeroDegree.StudentAssignments.repositories.AssignmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AssignmentControllerIntegrationTests {

    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    private AssignmentRepository assignmentRepository;

    @Autowired
    public AssignmentControllerIntegrationTests(ObjectMapper objectMapper, MockMvc mockMvc, AssignmentRepository assignmentRepository) {
        this.objectMapper = objectMapper;
        this.mockMvc = mockMvc;
        this.assignmentRepository = assignmentRepository;
    }

    @Test
    public void createAssignmentReturnsCorrectHttpStatusWhenCreated() throws Exception{
        AssignmentDto testAssignmentA = TestData.createAssignmentDtoA(null);
        String testAssignmentAJson = objectMapper.writeValueAsString(testAssignmentA);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/assignments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testAssignmentAJson)
        ).andExpect(status().isCreated());
    }

    @Test
    public void createAssignmentReturnsCorrectHttpStatusAndObjectWhenCreated() throws Exception{
        StudentDto testStudentA = TestData.createStudentDtoA();
        AssignmentDto testAssignmentA = TestData.createAssignmentDtoA(testStudentA);
        String testAssignmentAJson = objectMapper.writeValueAsString(testAssignmentA);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/assignments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testAssignmentAJson)
        )
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(testAssignmentA.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(testAssignmentA.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.className").value(testAssignmentA.getClassName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.studentDto").value(testAssignmentA.getStudentDto()));
    }

    @Test
    public void getAssignmentsReturnsCorrectHttpStatusAndObjectsWhenCalled() throws Exception{
        StudentEntity testStudentA = TestData.createStudentEntityA();
        AssignmentEntity testAssignmentA = TestData.createAssignmentEntityA(testStudentA);

        assignmentRepository.save(testAssignmentA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/assignments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)

        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(testAssignmentA.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(testAssignmentA.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].className").value(testAssignmentA.getClassName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].studentDto").value(testAssignmentA.getStudentEntity()));

    }

    @Test
    public void updateAssignmentReturnsCorrectHTTPStatus404WhenNotFound() throws Exception {
        AssignmentDto testAssignmentA = TestData.createAssignmentDtoA(null);
        String testAssignmentJsonA = objectMapper.writeValueAsString(testAssignmentA);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/assignments/1000")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testAssignmentJsonA)
                        .accept(MediaType.APPLICATION_JSON)

                       )
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateAssignmentReturnsCorrectHttpStatus204AndCorrectObjectReturned() throws Exception{
        AssignmentEntity testAssignmentAEntity = TestData.createAssignmentEntityA(null);
        AssignmentDto testAssignmentA = TestData.createAssignmentDtoA(null);

        assignmentRepository.save(testAssignmentAEntity);

        testAssignmentA.setName("Updated");
        String testAssignmentJsonA = objectMapper.writeValueAsString(testAssignmentA);


        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/assignments/"+testAssignmentA.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testAssignmentJsonA)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(testAssignmentA.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated"));
    }

    @Test
    public void deleteAssignmentCorrectlyWorks() throws Exception{
        AssignmentEntity testAssignmentAEntity = TestData.createAssignmentEntityA(null);
        assignmentRepository.save(testAssignmentAEntity);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/assignments/"+testAssignmentAEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/assignments/"+testAssignmentAEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());

    }
}
