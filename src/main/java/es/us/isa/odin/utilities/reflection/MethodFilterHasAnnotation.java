package es.us.isa.odin.utilities.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.springframework.util.ReflectionUtils.MethodFilter;

public class MethodFilterHasAnnotation implements MethodFilter {

	private Class<? extends Annotation> annotation;
	
	public MethodFilterHasAnnotation(Class<? extends Annotation> annotation) {
		this.annotation = annotation;
	}
	
	@Override
	public boolean matches(Method method) {
		return method.getAnnotation(annotation) == null ? false : true;
	}

}
