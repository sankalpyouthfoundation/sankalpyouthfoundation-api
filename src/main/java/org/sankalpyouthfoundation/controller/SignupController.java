package org.sankalpyouthfoundation.controller;

import org.sankalpyouthfoundation.model.LoginRequest;
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
            data.setPassword("null");
            return ResponseEntity.status(HttpStatus.OK).body(data);
        }else {
            Message error = new Message("Error", "User Already Exist!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PutMapping("/api/signup")
    public ResponseEntity<Object> updateSignupData(@RequestBody Signup signup){
        this.signupService.save(signup);
        Message message = new Message("Success","Update Successfully");
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("/api/signup")
    public ResponseEntity<List<Signup>> getAllSignupData(){
        List<Signup> data = this.signupService.findAll();
        for(Signup signup : data){
            signup.setPassword(null);
        }
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

    @PostMapping("/api/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest){
        Optional<Signup> result = this.signupService.findById(loginRequest.getEmail());
        if(!result.isEmpty() && result.get().getEmail().equals(loginRequest.getEmail())){
            if(result.get().getPassword().equals(loginRequest.getPassword())){
                Message message = new Message("Success", "Login Successful!");
                result.get().setPassword(null);
                return ResponseEntity.status(HttpStatus.OK).body(result.get());
            }else{
                Message message = new Message("Unauthorized", "Wrong credentials. Please enter correct details.");
                return ResponseEntity.status(HttpStatus.OK).body(message);
            }
        }else {
            Message message = new Message("No Account exist with this mail", "Please register yourself!");
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }

}
