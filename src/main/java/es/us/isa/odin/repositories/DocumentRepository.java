package es.us.isa.odin.repositories;

import org.springframework.stereotype.Repository;

import es.us.isa.odin.repositories.documents.IDocumentRepository;

@Repository
public interface DocumentRepository extends IDocumentRepository<Object> {

}
