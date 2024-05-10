package org.sankalpyouthfoundation.service;

import org.sankalpyouthfoundation.model.Signup;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface SignupService extends MongoRepository<Signup, String> {
}
