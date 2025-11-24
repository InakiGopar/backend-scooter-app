package ar.edu.unicen.tripservice.application.repositories;

import ar.edu.unicen.tripservice.domain.documents.Fee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeRepository extends MongoRepository<Fee, String> {
}
