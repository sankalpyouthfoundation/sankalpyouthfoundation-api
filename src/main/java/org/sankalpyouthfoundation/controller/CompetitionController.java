package org.sankalpyouthfoundation.controller;

import org.sankalpyouthfoundation.model.Competition;
import org.sankalpyouthfoundation.service.CompetitionService;
import org.sankalpyouthfoundation.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    @PostMapping("/api/competitions")
    public ResponseEntity<Object> saveCompetition(@RequestBody Competition competition){
        if(null != competition && null != competition.getTitle() && null != competition.getName()){
            Competition result = this.competitionService.save(competition);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        Message message = new Message("Bad request","Missing mandatory attributes");
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("/api/competitions")
    public ResponseEntity<Object> getAllData(){
        List<Competition> result = this.competitionService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
