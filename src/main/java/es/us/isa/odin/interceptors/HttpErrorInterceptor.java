package es.us.isa.odin.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class HttpErrorInterceptor extends HandlerInterceptorAdapter{
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
		if(modelAndView.getModelMap().get("error") == null) {
			modelAndView.addObject("error", response.getStatus());
		}
	}
}
