package org.sankalpyouthfoundation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Contact {
    private LocalDateTime timestamp;
    private String firstname;
    private String lastname;
    private String email;
    private String message;
    private String phone;
    private String city;
}
