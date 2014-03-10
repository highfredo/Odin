package es.us.isa.odin.utilities.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.util.ReflectionUtils.MethodCallback;

public class MethodCallbackInvoke implements MethodCallback {
	
	private Object object;
	
	public MethodCallbackInvoke(Object object) {
		this.object = object;
	}

	@Override
	public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
		try {
			method.invoke(object);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}		
	}

}
