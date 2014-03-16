package es.us.isa.odin.repositories.events;

import java.lang.reflect.Field;

import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

import es.us.isa.odin.annotations.EditedDate;
import es.us.isa.odin.utilities.reflection.FieldFilterHasAnnotation;

public class EditedDateMongoEventListener extends AbstractMongoEventListener<Object> {
	
	@Override
	public void onBeforeConvert(final Object source) {
		ReflectionUtils.doWithFields(source.getClass(), 
				new FieldCallback() {				
					@Override
					public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
						ReflectionUtils.makeAccessible(field);
						field.set(source, new DateTime());		
					}
				}, 
				new FieldFilterHasAnnotation(EditedDate.class)
		);
	}

}