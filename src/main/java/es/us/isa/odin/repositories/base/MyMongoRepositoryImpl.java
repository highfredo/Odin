package es.us.isa.odin.repositories.base;

import java.io.Serializable;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.util.Assert;


public class MyMongoRepositoryImpl<T, ID extends Serializable> extends SimpleMongoRepository<T, ID> implements MyMongoRepositoryCustom<T, ID> {

	private final MongoOperations mongoOperations;
	private final MongoEntityInformation<T, ID> entityInformation;
	
	//public MyMongoRepositoryCustom() {
	//	super(null, null);
	//	mongoOperations = null;
	//	entityInformation = null;
	//}
	
	public MyMongoRepositoryImpl(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {
		super(metadata, mongoOperations);
		System.out.println("ASDASDASDASD");
		this.mongoOperations = mongoOperations;
		this.entityInformation = metadata;
	}


	@Override
	public <S extends T> S save(S entity) {
		Assert.notNull(entity, "Entity must not be null!");
		
		//CustomMongoCollection anCollection = entity.getClass().getAnnotation(CustomMongoCollection.class);
		//String collection = anCollection == null ? entityInformation.getCollectionName() : anCollection.value();
		System.out.println("Hola");
		mongoOperations.save(entity, "hola");
		
		return entity;
	}

/*
	@Override
	public <S extends T> S sharedCustomMethod(S entity) {
		System.out.println("Hola");
		mongoOperations.save(entity, "hola");
		return entity;
	}
*/
	

}
