package org.sankalpyouthfoundation.controller;

import org.sankalpyouthfoundation.model.Signup;
import org.sankalpyouthfoundation.service.SignupService;
import org.sankalpyouthfoundation.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SignupController {

    @Autowired
    private SignupService signupService;

    @PostMapping("/api/signup")
    public ResponseEntity<Object> doSignUp(@RequestBody Signup signup){
        Optional<Signup> result = this.signupService.findById(signup.getEmail());
        if(result.isEmpty()){
            signup.setAdmin(false);
            Signup data = this.signupService.save(signup);
            Message success = new Message("Success", "Signup Successfully!");
            return ResponseEntity.status(HttpStatus.CREATED).body(success);
        }else {
            Message error = new Message("Error", "User Already Exist!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping("/api/signup/data")
    public ResponseEntity<List<Signup>> getAllSignupData(){
        List<Signup> data = this.signupService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @PatchMapping("/api/signup/{email}")
    public ResponseEntity<Object> updateAdminStatus(@PathVariable String email){
        Optional<Signup> result = this.signupService.findById(email);
        if(!result.isEmpty()){
            result.get().setAdmin(!result.get().isAdmin());
            this.signupService.save(result.get());
            Message message = new Message("Success", "Changed Successfully!");
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }else {
            Message message = new Message("Error", "Please contact admin");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }
    }
}