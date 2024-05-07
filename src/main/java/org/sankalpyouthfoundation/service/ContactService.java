package org.sankalpyouthfoundation.service;

import org.sankalpyouthfoundation.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface ContactService extends MongoRepository<Contact, Date> {
    public List<Contact> findByEmail(String email);
}
