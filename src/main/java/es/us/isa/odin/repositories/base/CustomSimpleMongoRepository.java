package es.us.isa.odin.repositories.base;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.util.Assert;

import es.us.isa.odin.domain.Document;

public class CustomSimpleMongoRepository<T extends Document<?>, ID extends Serializable> extends SimpleMongoRepository<T, ID> {

	public CustomSimpleMongoRepository(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {
		super(metadata, mongoOperations);
	}


	public <S extends T> S save(S entity, String collection) {

		Assert.notNull(entity, "Entity must not be null!");

		getMongoOperations().save(entity, collection);
		return entity;
	}

	public <S extends T> List<S> save(Iterable<S> entities, String collection) {

		Assert.notNull(entities, "The given Iterable of entities not be null!");

		List<S> result = new ArrayList<S>();

		for (S entity : entities) {
			save(entity, collection);
			result.add(entity);
		}

		return result;
	}


	public void delete(ID id, String collection) {
		Assert.notNull(id, "The given id must not be null!");
		getMongoOperations().remove(getIdQuery(id), getEntityInformation().getJavaType(), collection);
	}

	public void delete(T entity, String collection) {
		Assert.notNull(entity, "The given entity must not be null!");
		delete(getEntityInformation().getId(entity), collection);
	}

	public void delete(Iterable<? extends T> entities, String collection) {

		Assert.notNull(entities, "The given Iterable of entities not be null!");

		for (T entity : entities) {
			delete(entity, collection);
		}
	}

	public boolean exists(ID id, String collection) {
		Assert.notNull(id, "The given id must not be null!");
		return getMongoOperations().exists(getIdQuery(id), getEntityInformation().getJavaType(), collection);
	}
	
	public long count(String collection) {
		return getMongoOperations().getCollection(collection).count();
	}
	
	public void deleteAll(String collection) {
		getMongoOperations().remove(new Query(), collection);
	}

	public T findOne(ID id, String collection) {
		Assert.notNull(id, "The given id must not be null!");
		return getMongoOperations().findById(id, getEntityInformation().getJavaType(), collection);
	}
	
	public List<T> findAll(String collection) {
		return findAll(new Query(), collection);
	}

	public Iterable<T> findAll(Iterable<ID> ids, String collection) {

		Set<ID> parameters = new HashSet<ID>();
		for (ID id : ids) {
			parameters.add(id);
		}

		return findAll(new Query(new Criteria(getEntityInformation().getIdAttribute()).in(parameters)), collection);
	}

	public Page<T> findAll(final Pageable pageable, String collection) {

		Long count = count(collection);
		List<T> list = findAll(new Query().with(pageable), collection);

		return new PageImpl<T>(list, pageable, count);
	}

	public List<T> findAll(Sort sort, String collection) {
		return findAll(new Query().with(sort), collection);
	}

	private List<T> findAll(Query query, String collection) {

		if (query == null) {
			return Collections.emptyList();
		}

		return getMongoOperations().find(query, getEntityInformation().getJavaType(), collection);
	}

	
	private Query getIdQuery(Object id) {
		return new Query(getIdCriteria(id));
	}

	private Criteria getIdCriteria(Object id) {
		return where(getEntityInformation().getIdAttribute()).is(id);
	}

}
