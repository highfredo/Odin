package es.us.isa.odin.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import es.us.isa.odin.domain.Document;
import es.us.isa.odin.domain.entity.FooEntity;
import es.us.isa.odin.repositories.documents.DocumentRepository;

@Repository
public interface FooRepository extends DocumentRepository<FooEntity> {

	@Query("{ version : { $gt : -1 }}")
	public List<Document<FooEntity>> test();
}