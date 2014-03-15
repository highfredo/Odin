package es.us.isa.odin.controllers;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.us.isa.odin.controllers.common.AbstractController;
import es.us.isa.odin.domain.Document;
import es.us.isa.odin.domain.entity.FooEntity;
import es.us.isa.odin.services.DocumentService;
import es.us.isa.odin.services.FooService;


@Controller
@RequestMapping("/welcome")
public class IndexController extends AbstractController {

	@Autowired
	private FooService fooService;
	@Autowired
	private DocumentService documentService;

	@RequestMapping(value = "/index2")
	public ModelAndView index2() {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("q", "hey");
								
		return modelAndView;
	}
	
	@RequestMapping(value = "/index")
	public ModelAndView index(@RequestParam(defaultValue = "hola foo") String foo, @RequestParam(defaultValue = "hola bar") String bar) {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("q", foo);
		
		FooEntity fooEntity = new FooEntity();
		fooEntity.setFoo(foo);
		fooEntity.setBar(bar);
		Document<FooEntity> doc = new Document<>();
		doc.setEntity(fooEntity);
		
		fooService.save(doc);
		ObjectMapper m = new ObjectMapper();
		Map<String,Object> props = m.convertValue(fooEntity, Map.class);
		//MyBean anotherBean = m.convertValue(props, MyBean.class);
		//documentService.save(doc);
						
		return modelAndView;
	}
	
	@RequestMapping(value = "/index/{id}")
	public ModelAndView edit(@PathVariable String id, @RequestParam(defaultValue = "hola foo") String foo, @RequestParam(defaultValue = "hola bar") String bar) {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("q", foo);
		
		Document<Object> doc = documentService.findOne(id);
		
		// fooService.save(doc);
		documentService.save(doc);
						
		return modelAndView;
	}

}