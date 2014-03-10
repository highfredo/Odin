package es.us.isa.odin.repositories.documents;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import es.us.isa.odin.domain.Document;

@NoRepositoryBean
public interface DocumentRepository<T> extends MongoRepository<Document<T>, ObjectId> {

}