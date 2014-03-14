package es.us.isa.odin.repositories.documents;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import es.us.isa.odin.domain.Document;

@NoRepositoryBean
public interface IDocumentRepository<T> extends MongoRepository<Document<T>, String> {

}