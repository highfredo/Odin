package us.es.isa.odin.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/welcome")
public class IndexController {

	@Value("${msg}")
	private String msg;

	@RequestMapping(value = "/index")
	public ModelAndView index(@RequestParam(defaultValue = "hola") String q) {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("q", q);
		modelAndView.addObject("msg", msg);
		return modelAndView;
	}

}