package es.us.isa.odin.repositories.base;

import java.io.Serializable;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.util.Assert;

import es.us.isa.odin.domain.Document;

public class CustomSimpleMongoRepository<T extends Document<?>, ID extends Serializable> extends SimpleMongoRepository<T, ID> {

	private final MongoOperations mongoOperations;
	private final MongoEntityInformation<T, ID> entityInformation;
	
	public CustomSimpleMongoRepository(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {
		super(metadata, mongoOperations);
		System.out.println("HOLA MUNDO");
		Assert.notNull(mongoOperations);
		Assert.notNull(metadata);

		this.entityInformation = metadata;
		this.mongoOperations = mongoOperations;
	}
	/*
	@Override
	public <S extends T> S save(S document) {

		Assert.notNull(document, "Entity must not be null!");
		
		editDate(document);
		mongoOperations.save(document, entityInformation.getCollectionName());
		
		return document;
	}
	
	
	private <S extends T> void editDate(final S document) {
		
		ReflectionUtils.doWithFields(document.getClass(), 
				new FieldCallback() {		
					@Override
					public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
						ReflectionUtils.makeAccessible(field);
						field.set(document, new DateTime());
					}
				}, 
				new FieldFilter() {	
					@Override
					public boolean matches(Field field) {
						return field.getAnnotation(EditedDate.class) == null ? false : true;
					}
				}
		);	
	}*/

}
