package es.us.isa.odin.repositories.base;

import java.io.Serializable;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

public class CustomRepositoryFactoryBean<T extends MongoRepository<S, ID>, S, ID extends Serializable> extends MongoRepositoryFactoryBean<T, S, ID> {

	protected RepositoryFactorySupport getFactoryInstance(MongoOperations operations) {
		return new CustomMongoRepositoryFactory(operations);
	}

}