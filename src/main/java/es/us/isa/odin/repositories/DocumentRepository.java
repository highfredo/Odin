package es.us.isa.odin.repositories;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import es.us.isa.odin.domain.Document;
import es.us.isa.odin.repositories.base.MyMongoRepositoryCustom;

@Repository
public interface DocumentRepository extends /*MongoRepository<Document<?>, ObjectId>*/ MyMongoRepositoryCustom<Document<?>, ObjectId> {

}