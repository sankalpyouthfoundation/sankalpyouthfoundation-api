package org.sankalpyouthfoundation.controller;

import org.sankalpyouthfoundation.model.Competition;
import org.sankalpyouthfoundation.service.CompetitionService;
import org.sankalpyouthfoundation.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    @PostMapping("/api/competitions")
    public ResponseEntity<Object> saveCompetition(@RequestBody Competition competition){
        if(null != competition && null != competition.getTitle() && null != competition.getName()){
            competition.setId(competition.getTitle().replace(" ", "")+competition.getName().replace(" ", ""));
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

    @DeleteMapping("/api/competitions")
    public ResponseEntity<Object> deleteById(@RequestBody Competition competition){
        Optional<Competition> data  = this.competitionService.findById(competition.getId());
        if(data.isPresent()){
            this.competitionService.deleteById(competition.getId());
            Message message = new Message("Success", "Successfully deleted!");
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        else {
            Message message = new Message("error", "No record found");
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }
}
