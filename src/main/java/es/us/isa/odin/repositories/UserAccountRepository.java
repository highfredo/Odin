package es.us.isa.odin.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import es.us.isa.odin.domain.Document;
import es.us.isa.odin.security.UserAccount;

@Repository
public interface UserAccountRepository extends MongoRepository<Document<UserAccount>, String> { //IDocumentRepository<UserAccount>{

	@Query("{ entity.username : ?0}")
	public Document<UserAccount> findByUsername(String username);
}
