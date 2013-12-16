package es.us.isa.odin.controllers.common;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Transactional
@RequestMapping("/error")
public class ErrorController extends AbstractController {

	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	protected ModelAndView eror403() {
		return new ModelAndView("error/403");
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@RequestMapping(value = "/404", method = RequestMethod.GET)
	protected ModelAndView eror404() {
		return new ModelAndView("error/404");
	}

}