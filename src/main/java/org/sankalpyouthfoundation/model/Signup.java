package org.sankalpyouthfoundation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Signup {
    private String name;
    private String contact;
    @Id
    private String email;
    private String password;
    private boolean isAdmin;
}
