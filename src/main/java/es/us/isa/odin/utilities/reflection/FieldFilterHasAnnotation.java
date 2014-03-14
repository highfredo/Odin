package es.us.isa.odin.utilities.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.springframework.util.ReflectionUtils.FieldFilter;

public class FieldFilterHasAnnotation implements FieldFilter {

	private Class<? extends Annotation> annotation;
	
	public FieldFilterHasAnnotation(Class<? extends Annotation> annotation) {
		this.annotation = annotation;
	}
	
	@Override
	public boolean matches(Field field) {
		return field.isAnnotationPresent(annotation);
	}

}
