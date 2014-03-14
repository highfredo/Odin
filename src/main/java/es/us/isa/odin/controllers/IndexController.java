package es.us.isa.odin.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

	@RequestMapping(value = "/index")
	public ModelAndView index(@RequestParam(defaultValue = "hola mundo") String q) {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("q", q);
		//modelAndView.addObject("msg", msg);
		return modelAndView;
	}
	
	@RequestMapping(value = "/index2")
	@ResponseBody
	public String index2(@RequestParam(defaultValue = "hola mundo") String q) {
		return q;
	}
	
	@RequestMapping(value = "/save")
	public ModelAndView save(@RequestParam(defaultValue = "hola foo") String foo, @RequestParam(defaultValue = "hola bar") String bar) {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("q", foo);
		
		FooEntity fooEntity = new FooEntity();
		fooEntity.setFoo(foo);
		fooEntity.setBar(bar);
		Document<Object> doc = new Document<>();
		doc.setEntity(fooEntity);
		
		// fooService.save(doc);
		documentService.save(doc);
		
				
		return modelAndView;
	}

}