package org.sankalpyouthfoundation.controller;

import org.sankalpyouthfoundation.model.Contact;
import org.sankalpyouthfoundation.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/api/contact")
    public ResponseEntity<Contact> saveRecord(@RequestBody Contact contact){
        LocalDateTime localDateTime = LocalDateTime.now();
        contact.setTimestamp(localDateTime);
        Contact result = this.contactService.save(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
