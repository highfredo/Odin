package es.us.isa.odin.repositories.base;

import java.io.Serializable;

import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;
import org.springframework.data.mongodb.core.mapping.MongoPersistentProperty;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.util.Assert;

public class MyRepositoryFactoryBean<R extends MongoRepository<T, I>, T, I extends Serializable> extends MongoRepositoryFactoryBean<R, T, I> {

	protected RepositoryFactorySupport createRepositoryFactory(MongoOperations mongoOperations) {
		return new MyRepositoryFactory(mongoOperations);
	}
	
	private static class MyRepositoryFactory<T, I extends Serializable> extends MongoRepositoryFactory {

		//private EntityManager entityManager;
		
		private final MongoOperations mongoOperations;
		private final MappingContext<? extends MongoPersistentEntity<?>, MongoPersistentProperty> mappingContext;

		public MyRepositoryFactory(MongoOperations mongoOperations) {
			super(mongoOperations);
			Assert.notNull(mongoOperations);
			this.mongoOperations = mongoOperations;
			this.mappingContext = mongoOperations.getConverter().getMappingContext();
		}

		protected Object getTargetRepository(RepositoryMetadata metadata) {
			MongoEntityInformation<?, Serializable> entityInformation = getEntityInformation(metadata.getDomainType());
			return new MyMongoRepositoryImpl<>(entityInformation, mongoOperations);
		}

		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			// The RepositoryMetadata can be safely ignored, it is used by the
			// JpaRepositoryFactory
			// to check for QueryDslJpaRepository's which is out of scope.
			return MyMongoRepositoryCustom.class;
		}
	}
}