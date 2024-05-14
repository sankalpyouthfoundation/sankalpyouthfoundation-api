package org.sankalpyouthfoundation.service;

import org.sankalpyouthfoundation.model.Competition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface CompetitionService extends MongoRepository<Competition, String> {
}
