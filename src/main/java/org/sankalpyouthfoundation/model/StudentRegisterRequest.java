package org.sankalpyouthfoundation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRegisterRequest {
    private LocalDateTime timestamp;
    private String firstname;
    private String lastname;
    private String dateofBirth;
    private String fatherName;
    private String contact;
    private String schoolName;
    private String studentClass;
    private String Address;
    private Competition competition;
}
