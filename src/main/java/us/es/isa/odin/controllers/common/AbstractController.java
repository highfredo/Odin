package us.es.isa.odin.controllers.common;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

public abstract class AbstractController {

	// Panic handler ----------------------------------------------------------
	@ExceptionHandler(Throwable.class)
	public ModelAndView panic(Throwable oops) {
		assert oops != null;

		ModelAndView result;

		result = new ModelAndView("error/panic");
		result.addObject("name", ClassUtils.getShortName(oops.getClass()));
		result.addObject("exceptionMessage", oops.getMessage());
		result.addObject("stackTrace", ExceptionUtils.getStackTrace(oops));

		return result;
	}
}
