package org.sankalpyouthfoundation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Competition {
    private String title;
    private String name;
    private String rank;
}
