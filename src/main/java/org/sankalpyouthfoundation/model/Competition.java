package org.sankalpyouthfoundation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Competition {
    @Id
    private String id;
    private String title;
    private String name;
    private String rank;
}
