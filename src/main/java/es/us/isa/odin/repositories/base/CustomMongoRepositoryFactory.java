package es.us.isa.odin.repositories.base;



import static org.springframework.data.querydsl.QueryDslUtils.QUERY_DSL_PRESENT;

import java.io.Serializable;

import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mapping.model.MappingException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;
import org.springframework.data.mongodb.core.mapping.MongoPersistentProperty;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MappingMongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.mongodb.repository.support.QueryDslMongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.stereotype.Repository;

import com.google.common.base.Strings;

public class CustomMongoRepositoryFactory extends MongoRepositoryFactory {

	private MongoOperations mongoOperations;
	private final MappingContext<? extends MongoPersistentEntity<?>, MongoPersistentProperty> mappingContext;


	public CustomMongoRepositoryFactory(MongoOperations mongoOperations) {
		super(mongoOperations);
		this.mongoOperations = mongoOperations;
		this.mappingContext = mongoOperations.getConverter().getMappingContext();
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Object getTargetRepository(RepositoryMetadata metadata) {

		Class<?> repositoryInterface = metadata.getRepositoryInterface();
		String collectionName = metadata.getRepositoryInterface().getAnnotation(Repository.class).value();
		MongoEntityInformation<?, Serializable> entityInformation = getEntityInformation(metadata.getDomainType(), collectionName);

		if (isQueryDslRepository(repositoryInterface)) {
			return new QueryDslMongoRepository(entityInformation, mongoOperations);
		} else {
			return new CustomSimpleMongoRepository(entityInformation, mongoOperations);
		}
	}
	
	private static boolean isQueryDslRepository(Class<?> repositoryInterface) {
		return QUERY_DSL_PRESENT && QueryDslPredicateExecutor.class.isAssignableFrom(repositoryInterface);
	}

	@SuppressWarnings("unchecked")
	public <T, ID extends Serializable> MongoEntityInformation<T, ID> getEntityInformation(Class<T> domainClass, String collentionName) {

		MongoPersistentEntity<?> entity = mappingContext.getPersistentEntity(domainClass);

		if (entity == null) {
			throw new MappingException(String.format("Could not lookup mapping metadata for domain class %s!",
					domainClass.getName()));
		}

		return Strings.isNullOrEmpty(collentionName) ?
			new MappingMongoEntityInformation<T, ID>((MongoPersistentEntity<T>) entity) :
			new MappingMongoEntityInformation<T, ID>((MongoPersistentEntity<T>) entity, collentionName);
	}
}