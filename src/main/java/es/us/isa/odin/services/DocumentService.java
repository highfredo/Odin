package es.us.isa.odin.services;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import es.us.isa.odin.domain.Document;
import es.us.isa.odin.repositories.DocumentRepository;
import es.us.isa.odin.repositories.base.IDocumentRepository;

@Service
@SuppressWarnings("rawtypes")
public class DocumentService extends AbstractService {

	@Autowired
	private DocumentRepository fooRepository;
	@Autowired
	private MongoOperations mongoOperations;
	
	@Override
	public IDocumentRepository<Object> repository() {
		return fooRepository;
	}

	public Document save(Document entity, String collection) {
		Assert.notNull(entity, "Entity must not be null!");

		mongoOperations.save(entity, collection);
		return entity;
	}

	public void delete(String id, String collection) {
		Assert.notNull(id, "The given id must not be null!");
		mongoOperations.remove(getIdQuery(id), Document.class, collection);
	}

	public boolean exists(String id, String collection) {
		Assert.notNull(id, "The given id must not be null!");
		return mongoOperations.exists(getIdQuery(id), Document.class, collection);
	}
	
	public long count(String collection) {
		return mongoOperations.getCollection(collection).count();
	}

	public Document findOne(String id, String collection) {
		Assert.notNull(id, "The given id must not be null!");
		return mongoOperations.findById(id, Document.class, collection);
	}
	
	public List<Document> findAll(String collection) {
		return findAll(new Query(), collection);
	}

	public Iterable<Document> findAll(Iterable<String> ids, String collection) {

		Set<String> parameters = new HashSet<String>();
		for (String id : ids) {
			parameters.add(id);
		}

		return findAll(new Query(new Criteria("id").in(parameters)), collection);
	}

	public Page<Document> findAll(final Pageable pageable, String collection) {

		Long count = count(collection);
		List<Document> list = findAll(new Query().with(pageable), collection);

		return new PageImpl<Document>(list, pageable, count);
	}

	public List<Document> findAll(Sort sort, String collection) {
		return findAll(new Query().with(sort), collection);
	}

	private List<Document> findAll(Query query, String collection) {

		if (query == null) {
			return Collections.emptyList();
		}

		return mongoOperations.find(query, Document.class, collection);
	}

	
	private Query getIdQuery(Object id) {
		return new Query(getIdCriteria(id));
	}

	private Criteria getIdCriteria(Object id) {
		return where("id").is(id);
	}

}
