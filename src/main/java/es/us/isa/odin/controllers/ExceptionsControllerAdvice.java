package es.us.isa.odin.controllers;


import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import es.us.isa.odin.exceptions.UnauthorizedExeption;
import es.us.isa.odin.rest.base.JsonModelAndView;


@ControllerAdvice
public class ExceptionsControllerAdvice {
	
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Throwable.class)
	protected ModelAndView panic(Throwable oops) {
		ModelAndView mv = new JsonModelAndView();
		prepareErrorResponse(mv, oops);
		return mv;
	}
	
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UnauthorizedExeption.class)
	protected ModelAndView eror401(AccessDeniedException exp) {
		ModelAndView mv = new JsonModelAndView();
		prepareErrorResponse(mv, exp);
		return mv;
	}
	
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@ExceptionHandler(AccessDeniedException.class)
	protected ModelAndView eror403(AccessDeniedException exp) {
		ModelAndView mv = new JsonModelAndView();
		prepareErrorResponse(mv, exp);
		return mv;
	}
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoSuchRequestHandlingMethodException.class)
	protected ModelAndView eror404(NoSuchRequestHandlingMethodException exp) {
		ModelAndView mv = new JsonModelAndView();
		prepareErrorResponse(mv, exp);
		return mv;
	}
	
	
	private void prepareErrorResponse(ModelAndView mv, Throwable oops) {
		mv.addObject("name", ClassUtils.getShortName(oops.getClass()));
		mv.addObject("exceptionMessage", oops.getMessage());
		mv.addObject("stackTrace", ExceptionUtils.getStackTrace(oops));
	}
	
}