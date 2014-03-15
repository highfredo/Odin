package es.us.isa.odin.services;

import java.util.List;

import es.us.isa.odin.domain.Document;
import es.us.isa.odin.repositories.base.IDocumentRepository;

public abstract class AbstractService<T> {

	public abstract IDocumentRepository<T> repository();
	
	public Document<T> save(Document<T> document) {
		return repository().save(document);
	}
	
	public List<Document<T>> save(Iterable<Document<T>> documents) {
		return repository().save(documents);
	}

	public Document<T> findOne(String id) {
		return repository().findOne(id);
	}

	public Iterable<Document<T>> findAll(Iterable<String> ids) {
		return repository().findAll(ids);
	}
	
	public List<Document<T>> findAll() {
		return repository().findAll();
	}
	
	public void delete(String id) {
		repository().delete(id);
	}
	
	public void delete(Document<T> document) {
		repository().delete(document);
	}
	
	public void delete(Iterable<Document<T>> documents) {
		repository().delete(documents);
	}

	public long count() {
		return repository().count();
	}

	public boolean exists(String id) {
		return repository().exists(id);
	}

}
