package org.sankalpyouthfoundation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("Students")
public class StudentResponse {
    @JsonIgnore
    private LocalDateTime timestamp;
    @Id
    private String studentRecordId;
    private String firstname;
    private String lastname;
    private String dateofBirth;
    private String fatherName;
    private String contact;
    private String schoolName;
    private String studentClass;
    private String Address;
    private List<Competition> competitions;
}
