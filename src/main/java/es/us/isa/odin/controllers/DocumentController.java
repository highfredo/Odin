package es.us.isa.odin.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.us.isa.odin.controllers.common.AbstractController;
import es.us.isa.odin.domain.Document;
import es.us.isa.odin.rest.base.JsonModelAndView;
import es.us.isa.odin.services.DocumentService;


@Controller
@RequestMapping("/document/{entityClass}")
public class DocumentController extends AbstractController {
	
	@Autowired
	private DocumentService documentService;

	@RequestMapping(value = "/create", method=RequestMethod.POST)
	public ModelAndView create(@PathVariable String entityClass, @RequestBody Map<String, Object> entity) { 
		ModelAndView result = new JsonModelAndView();
		entity.put("entityClass", entityClass);
		Document<Object> document = new Document<>((Object) entity);
		Document<Object> created = documentService.save(document);
		result.addObject(created);
		
		return result;
	}
/*
	@Override
	@RequestMapping(value = "/edit/{id}/{version}")
	public ModelAndView edit(Object entity) {
		ModelAndView result = new JsonModelAndView();
		Document<Object> edited = documentService.save(document);
		result.addObject(edited);
		
		return result;
	}

	@Override
	@RequestMapping(value = "/delete")
	public ModelAndView delete(String id) {
		ModelAndView result = new JsonModelAndView();
		documentService.delete(id);
		result.addObject(id + " deleted.");
		
		return result;
	}

	@Override
	@RequestMapping(value = "/get")
	public ModelAndView get(List<String> ids) {
		ModelAndView result = new JsonModelAndView();
		List<Document<Object>> docs = Lists.newArrayList(documentService.findAll(ids));
		result.addObject(docs);
		
		return result;
	}
	*/
}
