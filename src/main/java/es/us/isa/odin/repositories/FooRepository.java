package es.us.isa.odin.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import es.us.isa.odin.annotations.CustomMongoCollection;
import es.us.isa.odin.domain.Document;
import es.us.isa.odin.domain.entity.FooEntity;
import es.us.isa.odin.repositories.documents.IDocumentRepository;

@Repository
@CustomMongoCollection("repositorioMolon")
public interface FooRepository extends IDocumentRepository<FooEntity> {

	@Query("{ version : { $gt : -1 }}")
	public List<Document<FooEntity>> test();
}