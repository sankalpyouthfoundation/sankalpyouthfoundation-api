package org.sankalpyouthfoundation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ForestGreenArmy {
    private LocalDateTime timestamp;
    private String name;
    private String fatherName;
    private String age;
    private String village;
    private String city;
    private String district;
    private String state;
    @Id
    private String contact;
}
