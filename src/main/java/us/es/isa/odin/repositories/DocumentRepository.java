package us.es.isa.odin.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import us.es.isa.odin.domain.Document;

@Repository
public interface DocumentRepository extends MongoRepository<Document<?>, ObjectId> {

}