package org.sankalpyouthfoundation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("/")
    public String healthCheck(){
        return "Sankalp Youth foundation application is up and running on v1.13";
    }
}
