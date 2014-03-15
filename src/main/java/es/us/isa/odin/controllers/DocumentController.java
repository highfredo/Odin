package es.us.isa.odin.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;

import es.us.isa.odin.controllers.common.AbstractController;
import es.us.isa.odin.domain.Document;
import es.us.isa.odin.rest.base.JsonModelAndView;
import es.us.isa.odin.services.DocumentService;


@Controller
@RequestMapping("/document/{entityClass}")
public class DocumentController extends AbstractController {
	
	@Autowired
	private DocumentService documentService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/create", method=RequestMethod.POST)
	public ModelAndView create(@PathVariable String entityClass, @RequestBody Document<Map<String, Object>> document) { 
		ModelAndView result = new JsonModelAndView();
		Document<Object> created = documentService.save(document);
		result.addObject(created);
		
		return result;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/edit")
	public ModelAndView edit(@PathVariable String entityClass, @RequestBody Document<Map<String, Object>> document) {
		ModelAndView result = new JsonModelAndView();

		Document<Map<String, Object>> toUpdateDoc = documentService.findOne(document.getId());
		toUpdateDoc.setVersion(document.getVersion());
		
		// Merge entity
		Map<String, Object> toUpdateEntity = toUpdateDoc.getEntity();
		toUpdateEntity.putAll(document.getEntity());
		toUpdateDoc.setEntity(toUpdateEntity);
		
		// Merge extra data
		Map<String, Object> toUpdateExtra = toUpdateDoc.getExtraData();
		toUpdateExtra.putAll(document.getExtraData());
		toUpdateDoc.setExtraData(toUpdateExtra);
		
		Document<Object> edited = documentService.save(toUpdateDoc);
		result.addObject(edited);
		
		return result;
	}
	
	@RequestMapping(value = "/delete")
	public ModelAndView delete(@PathVariable String entityClass, @RequestBody List<String> ids) {
		ModelAndView result = new JsonModelAndView();
		for(String id : ids)
			documentService.delete(id);
		
		result.addObject("result", "Deleted.");
		
		return result;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/get")
	public ModelAndView get(@RequestBody List<String> ids) {
		ModelAndView result = new JsonModelAndView();
		List<Document<Object>> docs = Lists.newArrayList(documentService.findAll(ids));
		result.addObject(docs);
		
		return result;
	}
	
}
