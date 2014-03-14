package es.us.isa.odin.rest.base;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;



public class JsonModelAndView extends ModelAndView {

	public JsonModelAndView() {
		super();
		MappingJackson2JsonView mapping = new MappingJackson2JsonView();
		mapping.setObjectMapper(new JSONMapper());
		super.setView(mapping);
	}
	
}
