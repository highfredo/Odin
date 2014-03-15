package es.us.isa.odin.repositories.base;

import java.io.Serializable;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import es.us.isa.odin.domain.Document;

public class CustomSimpleMongoRepository<T extends Document<?>, ID extends Serializable> extends SimpleMongoRepository<T, ID> {

	public CustomSimpleMongoRepository(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {
		super(metadata, mongoOperations);
	}

}
