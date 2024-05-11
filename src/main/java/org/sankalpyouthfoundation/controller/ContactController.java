package org.sankalpyouthfoundation.controller;

import org.sankalpyouthfoundation.model.Contact;
import org.sankalpyouthfoundation.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/api/contacts")
    public ResponseEntity<Contact> saveRecord(@RequestBody Contact contact){
        LocalDateTime localDateTime = LocalDateTime.now();
        contact.setTimestamp(localDateTime);
        Contact result = this.contactService.save(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/api/contacts")
    public ResponseEntity<List<Contact>> getAllData(){
        List<Contact> result = this.contactService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
