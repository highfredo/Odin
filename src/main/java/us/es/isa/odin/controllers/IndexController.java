package us.es.isa.odin.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import us.es.isa.odin.controllers.common.AbstractController;

@Controller
@RequestMapping("/welcome")
public class IndexController extends AbstractController {

	@Value("${msg}")
	private String msg;

	@RequestMapping(value = "/index")
	public ModelAndView index(@RequestParam(defaultValue = "hola mundo") String q) {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("q", q);
		modelAndView.addObject("msg", msg);
		return modelAndView;
	}

}