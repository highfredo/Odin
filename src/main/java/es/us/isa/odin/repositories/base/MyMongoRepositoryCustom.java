package es.us.isa.odin.repositories.base;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MyMongoRepositoryCustom <T, ID extends Serializable> extends MongoRepository<T, ID> {
	//<S extends T> S sharedCustomMethod(S entity);
}
