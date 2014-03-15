package es.us.isa.odin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.us.isa.odin.domain.entity.FooEntity;
import es.us.isa.odin.repositories.FooRepository;
import es.us.isa.odin.repositories.base.IDocumentRepository;

@Service
public class FooService extends AbstractService<FooEntity> {

	@Autowired
	private FooRepository fooRepository;
	
	@Override
	public IDocumentRepository<FooEntity> repository() {
		return fooRepository;
	}

}
