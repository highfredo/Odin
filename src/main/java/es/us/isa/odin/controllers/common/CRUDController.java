package es.us.isa.odin.controllers.common;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

public interface CRUDController<T> {

	public ModelAndView create(T restDocument);
	public ModelAndView edit(T restDocument);
	public ModelAndView delete(String id);
	public ModelAndView get(List<String> ids);
	
}
