package es.us.isa.odin.controllers.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

@Controller
@RequestMapping("/error")
public class ErrorController extends AbstractController {

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	protected ModelAndView erorPage403() {
		throw new AccessDeniedException("");
	}

	@RequestMapping(value = "/404", method = RequestMethod.GET)
	protected ModelAndView erorPage404(HttpServletRequest request) throws NoSuchRequestHandlingMethodException {
		throw new NoSuchRequestHandlingMethodException(request);
	}

}