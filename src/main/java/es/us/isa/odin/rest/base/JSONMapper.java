package es.us.isa.odin.rest.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;


public class JSONMapper extends ObjectMapper {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1121241550283267314L;

	public JSONMapper() {
        SimpleModule module = new SimpleModule();
        registerModule(module);
        registerModule(new JodaModule());
        registerModule(new GuavaModule());
    }

}