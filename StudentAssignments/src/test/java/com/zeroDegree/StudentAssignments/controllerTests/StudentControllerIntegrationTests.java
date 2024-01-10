package com.zeroDegree.StudentAssignments.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeroDegree.StudentAssignments.Utils.TestData;
import com.zeroDegree.StudentAssignments.domains.dtos.StudentDto;
import com.zeroDegree.StudentAssignments.domains.entities.StudentEntity;
import com.zeroDegree.StudentAssignments.repositories.StudentRepository;
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
public class StudentControllerIntegrationTests {
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private StudentRepository studentRepository;

    @Autowired
    public StudentControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper, StudentRepository studentRepository) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.studentRepository = studentRepository;
    }

    @Test
    public void testThatCreateStudentReturnsCorrectHttpStatus() throws Exception{
        StudentDto studentDtoA = TestData.createStudentDtoA();
        String studentToJson = objectMapper.writeValueAsString(studentDtoA);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentToJson)

        )

                .andExpect(status().isCreated());
    }

    @Test
    public void testThatCreateStudentReturnsTheCorrectObject() throws Exception{
        StudentDto studentDtoA = TestData.createStudentDtoA();
        String studentToJson = objectMapper.writeValueAsString(studentDtoA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentToJson)
        )
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(studentDtoA.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(studentDtoA.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(studentDtoA.getAge()));
    }

    @Test
    public void testThatGetAllStudentsReturnsCorrectStatusAndObjects() throws Exception{

        StudentEntity studentEntityA = TestData.createStudentEntityA();
        StudentEntity studentEntityB = TestData.createStudentEntityB();


        studentRepository.save(studentEntityA);
        studentRepository.save(studentEntityB);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("[0].id").value(studentEntityA.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].name").value(studentEntityA.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].age").value(studentEntityA.getAge()))

                .andExpect(MockMvcResultMatchers.jsonPath("[1].id").value(studentEntityB.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("[1].name").value(studentEntityB.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("[1].age").value(studentEntityB.getAge()));
    }

    @Test
    public void testThatUpdateNotExistingStudentReturnsHttpStatus404NotFound() throws Exception{
        StudentDto testStudentA = TestData.createStudentDtoA();
        String testStudentAJson = objectMapper.writeValueAsString(testStudentA);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/students/10000")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testStudentAJson)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }

    @Test
    public void testThatUpdateExistingStudentReturnsHttpStatusOkAndCorrectObject() throws Exception{
        StudentEntity testStudentAEntity = TestData.createStudentEntityA();

        StudentDto testStudentA = TestData.createStudentDtoA();

        studentRepository.save(testStudentAEntity);

        testStudentA.setName("Updated");
        String testStudentAJson = objectMapper.writeValueAsString(testStudentA);


        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/students/"+testStudentA.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testStudentAJson)
                        .accept(MediaType.APPLICATION_JSON)
                       )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(testStudentA.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(testStudentA.getName()));
    }

    @Test
    public void testThatDeleteStudentReturnsCorrectStatus() throws Exception{
        StudentEntity testStudentAEntity = TestData.createStudentEntityA();
        studentRepository.save(testStudentAEntity);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/students/"+testStudentAEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/students/"+testStudentAEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }
}
