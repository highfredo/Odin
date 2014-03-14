package es.us.isa.odin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.us.isa.odin.repositories.DocumentRepository;
import es.us.isa.odin.repositories.documents.IDocumentRepository;

@Service
public class DocumentService extends AbstractService<Object> {

	@Autowired
	private DocumentRepository fooRepository;
	
	@Override
	public IDocumentRepository<Object> repository() {
		return fooRepository;
	}

	// TODO: implementar XX(XX, String collection)
}
