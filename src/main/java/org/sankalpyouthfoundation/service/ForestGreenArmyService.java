package org.sankalpyouthfoundation.service;

import org.sankalpyouthfoundation.model.ForestGreenArmy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface ForestGreenArmyService extends MongoRepository<ForestGreenArmy, String> {
}
