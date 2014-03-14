package es.us.isa.odin.rest.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;


public class JSONMapper extends ObjectMapper {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1121241550283267314L;

	public JSONMapper() {
        SimpleModule module = new SimpleModule();
        // module.addSerializer(ObjectId.class, new ObjectIdSerializer());
        // module.addDeserializer(ObjectId.class, new ObjectIdDeserializer());
        registerModule(module);
    }

}