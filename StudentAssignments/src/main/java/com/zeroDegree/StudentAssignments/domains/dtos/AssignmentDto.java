package com.zeroDegree.StudentAssignments.domains.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssignmentDto {

    private Long id;

    private String name;

    private String className;

    private StudentDto studentDto;

}
