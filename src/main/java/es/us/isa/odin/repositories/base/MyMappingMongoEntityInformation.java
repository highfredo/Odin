package es.us.isa.odin.repositories.base;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;
import org.springframework.data.mongodb.repository.support.MappingMongoEntityInformation;

public class MyMappingMongoEntityInformation<T, ID extends Serializable> extends MappingMongoEntityInformation<T, ID> {

	public MyMappingMongoEntityInformation(MongoPersistentEntity<T> entity) {
		super(entity);
		System.out.println("AAAAAAAAAAAAA");
	}

	public MyMappingMongoEntityInformation(MongoPersistentEntity<T> entity, String customCollectionName) {
		super(entity, customCollectionName);
		System.out.println("BBBBBBBBBBBBB");
	}

	@Override
	public String getCollectionName() {
		System.out.println("HOLA");
		return "hola";
	}
	
}
