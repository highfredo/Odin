package es.us.isa.odin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.us.isa.odin.controllers.common.AbstractController;
import es.us.isa.odin.domain.Document;


@Controller
@RequestMapping("/document/{entityClass}")
public class DocumentController extends AbstractController {
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public @ResponseBody String list(@PathVariable(value="entityClass") String entityClass) {
		return "listado";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public @ResponseBody String findOne(@PathVariable(value="entityClass") String entityClass, @PathVariable(value="id") String id) {
		return "findOne";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public @ResponseBody String delete(@PathVariable(value="entityClass") String entityClass, @PathVariable(value="id") String id) {
		return "delete";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public @ResponseBody String edit(@PathVariable(value="entityClass") String entityClass, 
			@PathVariable(value="id") String id, @ModelAttribute Document<?> entity) {
		return "edit";
	}
}
