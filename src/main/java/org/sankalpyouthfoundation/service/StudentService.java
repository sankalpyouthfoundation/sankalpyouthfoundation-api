package org.sankalpyouthfoundation.service;

import org.sankalpyouthfoundation.model.StudentResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface StudentService extends MongoRepository<StudentResponse, LocalDateTime> {
    //public List<StudentResponse> findByContact(String contact);

    public List<StudentResponse> findByContactAndFirstname(String contact, String firstname);
}
