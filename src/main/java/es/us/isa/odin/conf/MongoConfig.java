package es.us.isa.odin.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

@Configuration
public class MongoConfig {

	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		UserCredentials credentials = new UserCredentials("odin", "odin");
		return new SimpleMongoDbFactory(new MongoClient("ds057538.mongolab.com", 57538), "odindb", credentials);
	}
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}

}
