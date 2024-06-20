package org.sankalpyouthfoundation.controller;

import org.sankalpyouthfoundation.model.ForestGreenArmy;
import org.sankalpyouthfoundation.service.ForestGreenArmyService;
import org.sankalpyouthfoundation.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class ForestGreenArmyController {

    @Autowired
    private ForestGreenArmyService forestGreenArmyService;

    @PostMapping("/api/forestgreenarmy")
    public ResponseEntity<Object> saveForestGreenArmy(@RequestBody ForestGreenArmy forestGreenArmy){
        Optional<ForestGreenArmy> result = this.forestGreenArmyService.findById(forestGreenArmy.getContact());
        if(result.isEmpty()){
            LocalDateTime localDateTime = LocalDateTime.now();
            forestGreenArmy.setTimestamp(localDateTime);
            this.forestGreenArmyService.save(forestGreenArmy);
            Message message = new Message("Success", "Successfully Registered!!");
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        else{
            Message message = new Message("Fail", "User Already Exist!!");
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }

    @GetMapping("/data/forestgreenarmy")
    public ResponseEntity<List<ForestGreenArmy>> getAllData(){
        List<ForestGreenArmy> data = this.forestGreenArmyService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }
}
